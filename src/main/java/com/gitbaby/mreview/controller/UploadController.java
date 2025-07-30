package com.gitbaby.mreview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import java.util.Map;
import java.util.UUID;

@Controller
@Log4j2
public class UploadController {
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;

  @PostMapping("uploadAjax")
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files) {
    return ResponseEntity.ok(Arrays.stream(files).map(f -> {
      if(!f.getContentType().startsWith("image/")){
        log.warn(f.getContentType() + " is not supported");
        return ResponseEntity.badRequest().build();
      }

      String originalFilename = f.getOriginalFilename();
      String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);
      String folderPath = makeFolder();
      String uuid = UUID.randomUUID().toString();
      String saveName = folderPath + "/" + uuid + "_" + fileName;
      try {
        f.transferTo(new File(uploadPath, saveName));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return Map.of("origin", f.getOriginalFilename(), "size", f.getSize(), "uuid", uuid, "path", folderPath);
    }).toList());
  }

  @GetMapping("uploadEx")
  public void uploadEx() {}




  private String makeFolder() {
    String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    String folderPath = str.replace("//", File.separator);

    File uploadPathFolder = new File(uploadPath, folderPath);
    if (!uploadPathFolder.exists()) {
      uploadPathFolder.mkdirs();
    }
    return folderPath;
  }
}
