package com.lunchbox.lunchboxdonation.domain.likes;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LikesResponseDTO {
    private boolean likes;
    private String msg;
    private HttpStatus status;

    @Builder
    public LikesResponseDTO(boolean likes,String msg,HttpStatus status){
        this.likes = likes;
        this.msg = msg;
        this.status = status;
    }
    public static LikesResponseDTO of(boolean likes, String msg,HttpStatus status){
        return LikesResponseDTO.builder()
                .likes(likes)
                .msg(msg)
                .status(status)
                .build();
    }
}
