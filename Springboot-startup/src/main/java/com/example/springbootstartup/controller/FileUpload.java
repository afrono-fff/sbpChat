package com.example.springbootstartup.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUpload {

    @PostMapping("/upload")
    public String upload(String userId, MultipartFile avatar, HttpServletRequest request) throws IOException {
        System.out.println(userId);
        System.out.println(userId.getClass());
        System.out.println(avatar.getOriginalFilename());
        System.out.println(avatar.getContentType());
        String path = request.getServletContext().getRealPath("/upload/"); // 获取web服务器中的路径
        System.out.println(path);
        saveFile(avatar, path);
        return "上传成功";
    }
    public void saveFile(MultipartFile avatar, String path) throws IOException {
        File dir = new File(path);
        if(!dir.exists()){
            // 创建目录
            dir.mkdir();
        }
        File file = new File(path + avatar.getOriginalFilename());
        avatar.transferTo(file);
    }
}
