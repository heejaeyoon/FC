package com.example.fc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EpRecruitMvcConfig implements WebMvcConfigurer {
  @Value("${getEpRecruitPath}")
  String getEpRecruitPath;
  @Value("${getEpRecruitThumbnailPath}")
  String getEpRecruitThumbnailPath;
  @Value("${getEpRecruitMainThumbnailPath}")
  String getEpRecruitMainThumbnailPath;
  @Value("${uploadEpRecruit}")
  String uploadEpRecruit;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(uploadEpRecruit).addResourceLocations(getEpRecruitMainThumbnailPath);
  }
}
