package com.lunchbox.lunchboxdonation.service.member;

import com.lunchbox.lunchboxdonation.domain.OAuth.OAuthAttributes;
import com.lunchbox.lunchboxdonation.domain.member.MemberDTO;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import com.lunchbox.lunchboxdonation.entity.member.MemberSearch;
import com.lunchbox.lunchboxdonation.provider.MemberDetail;
import com.lunchbox.lunchboxdonation.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession session;
    
    //  spring security에서 DBMS의 회원 정보를 가져올 때 사용될 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(username).orElseThrow(() -> new UsernameNotFoundException((username + " not found")));
        return MemberDetail.builder()
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPw())
                .memberRole(member.getMemberRole())
                .build();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        로그인 완료 후 정보를 담기 위한 준비
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        로그인 된 사용자의 정보 불러오기
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

//        어떤 기업의 OAuth를 사용했는 지의 구분(kakao, naver, google ...)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member member = saveOrUpdate(attributes);
        if(member.getId() == null){
            memberRepository.save(member);
        }else{
            member.update(attributes.getName(), attributes.getMobile(), attributes.getEmail());
        }

        session.setAttribute("member", new MemberDTO(member));
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getMemberRole().getSecurityRole())),
                attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    @Override
    public void save(MemberDTO memberDTO){
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        Member member = Member.toMember(memberDTO);
        memberRepository.save(member);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO){
         /*
            1. 회원이 입력한 아이디로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */

        Optional<Member> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (byMemberId.isPresent()){

            Member member = byMemberId.get();
            if (member.getMemberPw().equals(memberDTO.getMemberPw())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(member);
                return dto;
            } else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 아이디 가진 회원이 없다)
            return null;
        }
    }
    @Override
    public List<MemberDTO> findAll() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member: memberList) {
            memberDTOList.add(MemberDTO.toMemberDTO(member));

        }
        return memberDTOList;
    }

    @Override
    public MemberDTO findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {

            return MemberDTO.toMemberDTO(optionalMember.get());
        } else {
            return null;
        }

    }

    @Override
    public MemberDTO updateForm(String myId) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(myId);
        if (optionalMember.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMember.get());
        } else {
            return null;
        }
    }

    @Override
    public void update(MemberDTO memberDTO) {
        memberRepository.save(Member.toUpdateMember(memberDTO));
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public String idCheck(String memberId) {
        Optional<Member> byMemberId = memberRepository.findByMemberId(memberId);
        System.out.println("service");
        if (byMemberId.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다.
            System.out.println("service1");
            return null;
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            System.out.println("service2");
            return "ok";
        }
    }

    @Override
    public Page<MemberDTO> MemberList(Pageable pageable, MemberSearch memberSearch){
        return memberRepository.memberList(pageable,memberSearch);
    }

    @Override
    public Member saveOrUpdate(OAuthAttributes authAttributes) {
        Member data = memberRepository.findByEmail(authAttributes.getEmail())
                .map(member -> member.update(authAttributes.getName(), authAttributes.getMobile(), authAttributes.getEmail()))
                .orElse(authAttributes.toEntity());
        return data;
    }
}
