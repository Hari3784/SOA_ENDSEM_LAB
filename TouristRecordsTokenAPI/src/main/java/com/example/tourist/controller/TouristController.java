package com.example.tourist.controller;

import com.example.tourist.entity.Tourist;
import com.example.tourist.service.TouristService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all tourist records (requires a valid bearer token)",
               security = @SecurityRequirement(name = "bearerAuth"))
    public List<Tourist> getAllTourists() {
        return service.getAllTourists();
    }
}
