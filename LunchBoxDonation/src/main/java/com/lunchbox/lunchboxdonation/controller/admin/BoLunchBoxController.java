package com.lunchbox.lunchboxdonation.controller.admin;

import com.lunchbox.lunchboxdonation.config.FileUtils;
import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxOption;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxSearch;
import com.lunchbox.lunchboxdonation.service.lunchbox.LunchBoxOptionService;
import com.lunchbox.lunchboxdonation.service.lunchbox.LunchBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/lunchbox")
public class BoLunchBoxController {
    private final LunchBoxService lunchBoxService;
    private final LunchBoxOptionService lunchBoxOptionService;
    private final FileUtils fileUtils;

//     도시락 목록
    @GetMapping("lunchboxList")
    public ModelAndView lunchBoxList(@PageableDefault(size = 10, page = 0) Pageable pageable, LunchBoxSearch lunchBoxSearch) {
        ModelAndView mv = new ModelAndView();

        Page<LunchBoxDTO> lunchBoxList = lunchBoxService.lunchBoxList(pageable, lunchBoxSearch);

        int startNum = lunchBoxList.getPageable().getPageNumber() * lunchBoxList.getPageable().getPageSize() + 1;
        mv.addObject("startNum", startNum);
        mv.addObject("lunchBoxList", lunchBoxList);
        return mv;
    }

//       등록 페이지 이동
    @GetMapping("lunchboxWrite")
    public ModelAndView lunchBoxWrite() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/admin/lunchbox/lunchboxWrite");
        return mv;
    }

    @PostMapping("save")
    public ModelAndView save(@ModelAttribute LunchBoxDTO lunchBoxDTO, @RequestParam("thumbnail") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                //파일 처리
                String filename = fileUtils.uploadFile(file);
                lunchBoxDTO.setLunchboxThumbNailingIMG(filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //DB에 도시락 정보 등록
        Long id = lunchBoxService.lunchBoxInsert(lunchBoxDTO);
        ModelAndView mv = new ModelAndView();
//        mv.setViewName("redirect:lunchboxDetail/"+id);
        mv.setViewName("redirect:lunchboxWrite");
        return mv;
    }

//        상세 보기
    @GetMapping("lunchboxDetail/{id}")
    public ModelAndView lunchBoxDetail(@PathVariable Long id)  {
        ModelAndView mv = new ModelAndView();

        LunchBox lunchBox = lunchBoxService.getLunchBoxWithOptionByLunchBoxId(id);
        mv.addObject("lunchBox",lunchBox);

        mv.setViewName("/admin/lunchbox/lunchboxDetail");

        return mv;
    }

//        삭제
    @GetMapping("lunchboxDelete/{id}")
    public ModelAndView lunchboxDelete(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        LunchBox lunchBox = lunchBoxService.getLunchBoxWithOptionByLunchBoxId(id);

        lunchBoxService.deleteLunchBoxAndOptionsByLunchBoxId(id);

        fileUtils.deleteFile(lunchBox.getLunchboxThumbNailingIMG());

        mv.setViewName("redirect:/admin/lunchbox/lunchboxList");
        return mv;

    }

    //도시락 옵션 삭제
    @GetMapping("lunchboxOptionDelete/{lunchboxId}/{optionId}")
    public ModelAndView lunchboxOptionDelete(@PathVariable("lunchboxId") Long lunchboxId, @PathVariable("optionId") Long optionId) {
        ModelAndView mv = new ModelAndView();
        lunchBoxOptionService.deleteOptionsById(optionId);

        mv.setViewName("redirect:/admin/lunchbox/lunchboxDetail/"+lunchboxId);
        return mv;

    }

//    도시락 수정
    @PostMapping("update")
    public ModelAndView update(@ModelAttribute LunchBoxDTO lunchBoxDTO, @RequestParam("thumbnail") MultipartFile file){
        ModelAndView mv = new ModelAndView();
        //이미지 파일 등록 시
        if (!file.isEmpty()) {
            try {
                String fileName = lunchBoxService.getImgName(lunchBoxDTO.getId());
                //기존 파일 삭제
                fileUtils.deleteFile(fileName);
                //파일 처리
                String filename = fileUtils.uploadFile(file);
                lunchBoxDTO.setLunchboxThumbNailingIMG(filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < lunchBoxDTO.getLunchBoxOptions().size(); i++){
            lunchBoxDTO.getLunchBoxOptions().get(i).setLunchbox(lunchBoxService.toEntity(lunchBoxDTO));
        }
        log.info("lunchBoxDTO4 : {}", lunchBoxDTO.toString());
        Long id = lunchBoxService.LunchBoxUpdate(lunchBoxDTO);


        mv.setViewName("redirect:/admin/lunchbox/lunchboxDetail/" + id);
        return mv;
    }
}
