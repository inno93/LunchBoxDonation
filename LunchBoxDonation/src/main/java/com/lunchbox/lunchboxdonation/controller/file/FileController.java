package com.lunchbox.lunchboxdonation.controller.file;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/files/*")
public class FileController {
    @PostMapping("upload")
    @ResponseBody
    public List<String> upload(@RequestParam("uploadFile")List<MultipartFile> files) throws IOException {
        String path = "C:/LunchBoxDonation";
        List<String> uuid = new ArrayList<>();

        File file = new File(path);

        for(int i = 0; i < files.size(); i++){
            uuid.add(UUID.randomUUID().toString());
            files.get(i).transferTo(new File(path, uuid.get(i) + "_" + files.get(i).getOriginalFilename()));
        }


        return uuid;
    }


}
