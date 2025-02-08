package com.app.personal_portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SocialMediaController {

    @GetMapping("/social-media")
    public ResponseEntity<Map<String, String>> getMethodName() {
        Map<String, String> socialMedia = new HashMap<String, String>();
        socialMedia.put("instagram", "https://www.instagram.com/ciril804/?__pwa=1");
        return ResponseEntity.ok(socialMedia);
    }
    
}
