package com.digitopia.digitopiacasestudy.controller;

import com.digitopia.digitopiacasestudy.model.Industry;
import com.digitopia.digitopiacasestudy.service.IndustryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "industry")
public class IndustryController {

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @PostMapping
    public ResponseEntity<?> saveIndustry(@RequestBody Industry industry) {
        this.industryService.create(industry);
        return ResponseEntity.status(HttpStatus.CREATED).body(industry);
    }

    @GetMapping
    public List<Industry> getAll() {
        return industryService.getAll();
    }
}
