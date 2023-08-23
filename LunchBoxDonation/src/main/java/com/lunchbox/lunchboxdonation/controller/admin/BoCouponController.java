package com.lunchbox.lunchboxdonation.controller.admin;

import com.lunchbox.lunchboxdonation.config.FileUtils;
import com.lunchbox.lunchboxdonation.domain.coupon.CouponRequestDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxSearch;
import com.lunchbox.lunchboxdonation.entity.coupon.Coupon;
import com.lunchbox.lunchboxdonation.entity.coupon.CouponSearch;
import com.lunchbox.lunchboxdonation.service.coupon.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/coupon")
public class BoCouponController {
    private final CouponService couponService;


    // 쿠폰 목록
    @RequestMapping("couponList")
    public ModelAndView couponList(@PageableDefault(size = 2, page = 0) Pageable pageable, CouponSearch couponSearch) {
        ModelAndView mv = new ModelAndView();

        Page<CouponRequestDTO> couponList = couponService.couponList(pageable, couponSearch);
        int startNum = couponList.getPageable().getPageNumber() * couponList.getPageable().getPageSize() + 1;

        mv.addObject("startNum", startNum);
        mv.addObject("couponList", couponList);

        return mv;
    }
    // 쿠폰 상세보기
    @RequestMapping("couponDetail/{id}")
    public ModelAndView couponDetail(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();

        Coupon coupon =  couponService.getCouponById(id);
        mv.addObject("coupon",coupon);
        mv.setViewName("/admin/coupon/couponDetail");

        return mv;
    }


    // 쿠폰 등록 페이지 이동
    @GetMapping("couponWrite")
    public ModelAndView couponWrite() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/admin/coupon/couponWrite");
        return mv;
    }

    //쿠폰 등록
    @PostMapping("save")
    public ModelAndView save(@ModelAttribute CouponRequestDTO couponRequestDTO){
        ModelAndView mv = new ModelAndView();
        log.info("{}",couponRequestDTO.toString());
        Long id = couponService.insert(couponRequestDTO);

        mv.setViewName("redirect:/admin/coupon/couponDetail/"+id);
        return mv;
    }



    // 쿠폰 삭제
    @GetMapping("couponDelete/{id}")
    public ModelAndView removeCoupon(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();

        couponService.deleteCouponById(id);

        mv.setViewName("redirect:/admin/coupon/couponList");
        return mv;
    }
//    쿠폰 수정
    @RequestMapping("update")
    public ModelAndView update(@ModelAttribute CouponRequestDTO couponRequestDTO) {
        ModelAndView mv = new ModelAndView();
        log.info("{}",couponRequestDTO.toString());
        Coupon coupon =  couponService.getCouponById(couponRequestDTO.getId());


        Long id = couponService.updateCoupon(couponRequestDTO);

        mv.setViewName("redirect:/admin/coupon/couponDetail/" + id);

        return mv;
    }


}
