package com.gitbaby.mreview.lib;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootTest
public class ThumbnailTest {

  @Test
  @DisplayName("썸네일")
  public void ThumbnailTest() throws IOException {
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\imgsp","cat.webp"));
    BufferedImage thumbnail = Thumbnails.of(originalImage).size(200, 200).asBufferedImage();
    ImageIO.write(thumbnail, "png", new File("C:\\Users\\tj\\Desktop\\imgsp","s_cat.png"));
  }

  @Test
  @DisplayName("해상도 유지 , 이미지 포맷 webp")
  public void FormatTest() throws IOException {
    BufferedImage image = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\imgsp","s_cat.png"));
    ImageIO.write(image, "webp", new File("C:\\Users\\tj\\Desktop\\imgsp","s_cat2.webp"));
  }

  @Test
  @DisplayName("이미지 자르기")
  public void cropTest() throws IOException {
    BufferedImage image = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\imgsp","cat.webp"));
//    BufferedImage crop = Thumbnails.of(image).crop();
    ImageIO.write(image, "webp", new File("C:\\Users\\tj\\Desktop\\imgsp","s_cat2.webp"));
  }




}
