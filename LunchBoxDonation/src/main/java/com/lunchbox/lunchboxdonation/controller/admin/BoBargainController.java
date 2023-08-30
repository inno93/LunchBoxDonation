package com.lunchbox.lunchboxdonation.controller.admin;


import com.lunchbox.lunchboxdonation.config.FileUtils;
import com.lunchbox.lunchboxdonation.domain.bargain.BargainDTO;
import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.bargain.BargainFile;
import com.lunchbox.lunchboxdonation.entity.bargain.BargainSearch;
import com.lunchbox.lunchboxdonation.service.bargain.BargainService;
import com.lunchbox.lunchboxdonation.service.bargainfile.BargainFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final BargainFileService bargainFileService;
    private final FileUtils fileUtils;

    @GetMapping("bargainList")
    public ModelAndView bargainList(@PageableDefault(size = 10, page=0) Pageable pageable, BargainSearch bargainSearch){
        ModelAndView mv = new ModelAndView();

        Page<BargainDTO> bargainList = bargainService.getBargainList(pageable,bargainSearch);
        int startNum = bargainList.getPageable().getPageNumber() * bargainList.getPageable().getPageSize() + 1;
        mv.addObject("startNum", startNum);
        mv.addObject("bargainList", bargainList);

//        mv.setViewName("/admin/bargain/bargainList");
        return mv;

    }


    @GetMapping("bargainWrite")
    public void bargainWrite(){}

    @PostMapping("save")
    public ModelAndView save(@ModelAttribute BargainDTO bargainDTO, @RequestParam("thumbnail") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                //파일 처리
                String filename = fileUtils.uploadFile(file);
                bargainDTO.setBargainFile(
                        BargainFile.builder()
                        .fileId(filename)
                        .fileName(file.getOriginalFilename())
                        .filePath("")
                        .build()
                );
                log.info("bargainDTO : {}", bargainDTO.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        //DB에 도시락 정보 등록
        Long id = bargainService.bargainInsert(bargainDTO);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:bargainDetail/"+id);
        return mv;
    }
}
