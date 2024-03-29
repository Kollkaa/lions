package com.boot.lions.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${cssdir.path}")
    private String cssdir;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/css/**")
                .addResourceLocations("file:src/main/resources/static/css/");
    }


    public  void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");

    }
 /* swvcgyvmwkvusk
     7ba42d8b0ed1a31012a51ec00ff81ca8eb4ac854552cb7307a50ad1475d7141e
      swvcgyvmwkvusk:7ba42d8b0ed1a31012a51ec00ff81ca8eb4ac854552cb7307a50ad1475d7141e@ec2-54-247-189-1.eu-west-1.compute.amazonaws.com:5432/d2pb1n8cjlag90
    */

}
