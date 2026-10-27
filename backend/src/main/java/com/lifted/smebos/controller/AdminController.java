package com.lifted.smebos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    public Map<String, String> getAdminDashboard() {
        return Map.of(
                "message", "Welcome to the SME-BOS Admin Dashboard",
                "access", "OWNER_ONLY"
        );
    }
}
