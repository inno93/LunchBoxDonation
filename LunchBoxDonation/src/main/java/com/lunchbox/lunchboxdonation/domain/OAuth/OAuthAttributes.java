package com.lunchbox.lunchboxdonation.domain.OAuth;

import com.lunchbox.lunchboxdonation.constant.Role;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Builder
@RequiredArgsConstructor
@Getter
@Slf4j
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String mobile;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
//  userNameAttributeName은 .yml에 등록되어 있는 user-name-attribute 값이다.

        if(registrationId.equals("kakao")){
            return ofKaKao(userNameAttributeName,attributes);
        }else if(registrationId.equals("naver")){
            return ofNaver(userNameAttributeName,attributes);
        }
        return ofGoogle(userNameAttributeName,attributes);

    }

    public static OAuthAttributes ofKaKao(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> KaKaoAccount = (Map<String, Object>)attributes.get(userNameAttributeName);

        return OAuthAttributes.builder()
                .name((String)((Map)KaKaoAccount.get("profile")).get("nickname"))
                .email((String)KaKaoAccount.get("email"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> NaverAccount = (Map<String,Object>)attributes.get(userNameAttributeName);

        return OAuthAttributes.builder()
                .email((String)NaverAccount.get("email"))
                .name((String)NaverAccount.get("name"))
                .mobile((String)NaverAccount.get("mobile"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();

    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .email((String)(attributes.get("email")))
                .name((String)(attributes.get("name")))
                .mobile(null)
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }



    public Member toEntity(){
        return Member.builder()
                .memberName(name)
                .memberEmail(email)
                .memberPhoneNumber(mobile)
                .memberRole(Role.MEMBER)
                .build();
    }

}
