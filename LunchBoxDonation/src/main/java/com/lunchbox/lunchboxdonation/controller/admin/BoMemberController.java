package com.lunchbox.lunchboxdonation.controller.admin;

import com.lunchbox.lunchboxdonation.domain.member.MemberDTO;
import com.lunchbox.lunchboxdonation.entity.member.MemberSearch;
import com.lunchbox.lunchboxdonation.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("admin/member")
@RequiredArgsConstructor
public class BoMemberController {
    private final MemberService memberService;


    //    회원 목록
    @GetMapping("memberList")
    public ModelAndView memberList(@PageableDefault(size = 2, page = 0) Pageable pageable, MemberSearch memberSearch){
        ModelAndView mv = new ModelAndView();

        Page<MemberDTO> memberList = memberService.MemberList(pageable,memberSearch);

        int startNum = memberList.getPageable().getPageNumber() * memberList.getPageable().getPageSize() + 1;

        mv.addObject("memberList", memberList);
        mv.addObject("startNum", startNum);
        mv.setViewName("/admin/member/memberList");
        return mv;

    }
    
    @GetMapping("memberDetail/{id}")
    public ModelAndView memberDetail(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        MemberDTO memberDTO = memberService.findById(id);

        mv.addObject("member", memberDTO);
        mv.setViewName("/admin/member/memberDetail");

        return mv;
    }

    @GetMapping("memberDelete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();

        memberService.deleteById(id);
        mv.setViewName("redirect:/admin/member/memberList");
        return mv;
    }
}
