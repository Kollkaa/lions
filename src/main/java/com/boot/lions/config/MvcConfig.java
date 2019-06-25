package com.boot.lions.config;

import com.boot.lions.Application;
import com.boot.lions.controller.MainController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.net.URISyntaxException;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            registry.addResourceHandler("/files/**")
                    .addResourceLocations(new File(Application.class.getProtectionDomain().getCodeSource().getLocation()
                            .toURI()).getPath()+uploadPath+"/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(uploadPath);
    }

    public  void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");

    }


}
