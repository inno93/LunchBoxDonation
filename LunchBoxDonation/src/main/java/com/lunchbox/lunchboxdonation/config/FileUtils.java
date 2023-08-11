package com.lunchbox.lunchboxdonation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtils {

    //단일 파일 저장
    //@param file : 파일정보, path : 경로
    //return : DB에 저장할 파일명
    public static String uploadFile(MultipartFile file, String path){

        try {
            //파일 원래 이름
            String originFileName = file.getOriginalFilename();

            //파일 경로 생성
            Path filePath = Paths.get(path + originFileName);
            log.info("path : " + filePath.toString());
            //파일 업로드
            Files.write(filePath, file.getBytes());

            return originFileName;

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;

    }
}
