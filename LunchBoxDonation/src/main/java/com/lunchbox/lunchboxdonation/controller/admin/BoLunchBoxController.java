package com.lunchbox.lunchboxdonation.controller.admin;

import com.lunchbox.lunchboxdonation.config.FileUtils;
import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxSearch;
import com.lunchbox.lunchboxdonation.service.lunchbox.LunchBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/lunchbox")
public class BoLunchBoxController {
    private final LunchBoxService lunchBoxService;
    private final FileUtils fileUtils;

    //    기부
    @GetMapping("donation")
    public void donation(){

    }
    // 도시락
    //목록
    @GetMapping("lunchboxList")
    public ModelAndView lunchBoxList(@PageableDefault(size = 10, page = 0) Pageable pageable, LunchBoxSearch lunchBoxSearch){
        ModelAndView mv = new ModelAndView();

        Page<LunchBoxDTO> lunchBoxList = lunchBoxService.lunchBoxList(pageable, lunchBoxSearch);


        mv.addObject("lunchBoxList", lunchBoxList);

        return mv;
    }
//    등록 페이지 이동
    @GetMapping("lunchboxWrite")
    public void lunchBoxWrite(){}

    @PostMapping("save")
    public ModelAndView save(@ModelAttribute LunchBoxDTO lunchBoxDTO, @RequestParam("thumbnail") MultipartFile file){

        if(!file.isEmpty()){
            try{
                //파일 처리
                String filename = fileUtils.uploadFile(file, "admin/lunchbox/");
                lunchBoxDTO.setLunchboxThumbNailingIMG(filename);
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        //DB에 도시락 정보 등록
        lunchBoxService.lunchBoxInsert(lunchBoxDTO);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/lunchbox/lunchboxDetail");
        return mv;
    }
//    상세 보기
    @GetMapping("lunchboxDetail")
    public void lunchBoxDetail(){
//        ModelAndView mv = new ModelAndView();
////        mv.setViewName("admin/lunchbox/lunchboxDetail");
//        mv.setViewName("admin/lunchboxDetail");
//        return mv;
    }

    // 이달의 특가
}
