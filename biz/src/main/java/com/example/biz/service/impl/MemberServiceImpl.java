package com.example.biz.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.biz.service.MemberService;

@Component
class MemberServiceImpl implements MemberService {

    @Override
    public String doIt(String cmd) {
        return String.format("=== %s ====  at time: %s", cmd, LocalDateTime.now());
    }

    
}
