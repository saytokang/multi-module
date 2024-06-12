package com.example.web.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biz.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok().body("wellcom home..");
    }

    @GetMapping("/cmd")
    public ResponseEntity<?> cmd() {
        var rs = memberService.doIt("hello");
        return ResponseEntity.ok().body("cmd: " + rs);
    }
    
}
