package com.lunchbox.lunchboxdonation.controller.admin;


import com.lunchbox.lunchboxdonation.config.FileUtils;
import com.lunchbox.lunchboxdonation.domain.bargain.BargainDTO;
import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.service.bargain.BargainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/bargain")
@RequiredArgsConstructor
@Slf4j
public class BoBargainController {

    private final BargainService bargainService;
    private final FileUtils fileUtils;

    @GetMapping("bargainList")
    public ModelAndView bargainList(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/admin/bargain/bargainList");
        return mv;

    }


    @GetMapping("bargainWrite")
    public void bargainWrite(){}

    @PostMapping("save")
    public ModelAndView save(@ModelAttribute BargainDTO bargainDTO, @RequestParam("thumbnail") MultipartFile file) {

//        if (!file.isEmpty()) {
//            try {
//                //파일 처리
//                String filename = fileUtils.uploadFile(file);
//                bargainDTO.setLunchboxThumbNailingIMG(filename);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        //DB에 도시락 정보 등록
//        Long id = bargainService.lunchBoxInsert(bargainDTO);
        ModelAndView mv = new ModelAndView();
////        mv.setViewName("redirect:lunchboxDetail/"+id);
//        mv.setViewName("redirect:lunchboxWrite");
        return mv;
    }
}
