package com.boot.lions.controller;


import com.boot.lions.domain.Message;
import com.boot.lions.domain.User;
import com.boot.lions.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private  String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "app";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @GetMapping("/error")
    public String error()
    {
        return "error";
    }


    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Message message = new Message(text, tag, user);

        if (file!=null)
        {
             File uploadDir=new File(uploadPath);
             if(!uploadDir.exists())
             {
                uploadDir.mkdir();
                 System.out.println("mkdir");
             }

            System.out.println(file.getResource());
            System.out.println(uploadDir.getAbsolutePath());
            String uuidFile= "12";
            String resultFilename= uuidFile+"-"+file.getOriginalFilename();
            File image=new File(uploadDir.getAbsolutePath()+"/"+resultFilename);
            file.transferTo(image);
            message.setPath(uploadDir.getAbsolutePath());
            message.setFilename(resultFilename);

        }

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "redirect:/main";
    }
}