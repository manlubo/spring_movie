package com.gitbaby.mreview.controller;

import com.gitbaby.mreview.domain.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@Log4j2
public class UploadController {
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;


  @PostMapping("uploadAjax")
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files) throws IOException{
    List<UploadResultDTO> list = new ArrayList<>();
    for(MultipartFile f : files) {
      if(!f.getContentType().startsWith("image/")){
        log.warn(f.getContentType() + " is not supported");
        return ResponseEntity.badRequest().body("잘못된 파일 형식입니다.");
      }
      // uuid, 파일명 변경작업
      String uuid = UUID.randomUUID().toString();
      String origin = f.getOriginalFilename();
      origin = origin.substring(0, origin.lastIndexOf(".")) + ".webp";

      // 경로 및 저장 파일명
      String folderPath = makeFolder();
      String saveName = folderPath + "/" + uuid + "_" + origin;
      String thumbName = folderPath + "/s_" + uuid + "_" + origin;
      BufferedImage thumbnail = Thumbnails.of(ImageIO.read(f.getInputStream())).size(200, 200).asBufferedImage();

      ImageIO.write(ImageIO.read(f.getInputStream()), "webp", new File(uploadPath, saveName));
      ImageIO.write(thumbnail, "webp", new File(uploadPath, thumbName));

//      f.transferTo(new File(uploadPath, saveName));

      list.add(UploadResultDTO.builder().origin(origin).uuid(uuid).path(folderPath).build());
    }
    return ResponseEntity.ok(list);
  }

  @GetMapping("uploadEx")
  public void uploadEx() {}

  @GetMapping("display")
  public ResponseEntity<?> display(UploadResultDTO dto) throws IOException {
    File file = new File(uploadPath + "/" + dto.getPath(), dto.getUuid() + "_" + dto.getOrigin());
    HttpHeaders headers = new HttpHeaders();
    headers.add("content-type", Files.probeContentType(file.toPath()));
    return ResponseEntity.ok().headers(headers).body(Files.readAllBytes(file.toPath()));
  }



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
