package com.lunchbox.lunchboxdonation.config;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Component
@NoArgsConstructor
public class FileUtils {

    @Value("${file.uploadDir}")
//    @Value("${spring.servlet.multipart.location}")
    private String commonPath;


    //단일 파일 저장
    //@param file : 파일정보, path : static/img 아래에 파일을 저장할 경로
    //return : DB에 저장할 파일명
    public  String uploadFile(MultipartFile file, String path){

        try {
            //파일 원래 이름
            String originFileName = file.getOriginalFilename();
            //확장자 분리
            String extension = StringUtils.getFilenameExtension(originFileName);
            //파일명 저장 시 원본 파일명이 아닌 임시 파일명으로 변환
            String saveName = UUID.randomUUID().toString() + "." + extension;

            //파일 경로 생성
            Path filePath = Paths.get(commonPath + path + saveName);
            log.info("path : " + filePath.toString());
            //파일 업로드
            File uploadFile = new File(filePath.toString());
           // Files.write(filePath, file.getBytes());
            file.transferTo(uploadFile);

            return saveName;

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;

    }
}
