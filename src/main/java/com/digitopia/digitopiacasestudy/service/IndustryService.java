package com.digitopia.digitopiacasestudy.service;

import com.digitopia.digitopiacasestudy.model.Industry;
import com.digitopia.digitopiacasestudy.repository.IndustryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryService {

    private final IndustryRepository industryRepository;


    public IndustryService(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    public Industry create(Industry industry) {
        if (industryCreateControl(industry)) {
            normalizedString(industry);
            this.industryRepository.save(industry);
            return industry;
        }
        throw new IllegalArgumentException("An unexpected error occurred, please try again later or contact the system administrator.");
    }

    public void normalizedString(Industry industry) {
        industry.setNormalizedIndustryName(industry.getIndustryName().toLowerCase()
                .replace('ı', 'i')
                .replace('ö', 'o')
                .replace('ü', 'u')
                .replace('ç', 'c')
                .replace('ş', 's')
                .replace('ğ', 'g')
                .replace(" ", "")
                .replaceAll("[^a-z]", "")
        );
    }

    private boolean industryCreateControl(Industry industry) {
        if (industry != null && industry.getIndustryName() != null && !industry.getIndustryName().isEmpty()) {
            Industry controlledIndustry = this.industryRepository.findByIndustryName(industry.getIndustryName())
                    .orElse(null);
            return controlledIndustry == null ? true : false;
        }
        throw new IllegalArgumentException("An unexpected error occurred, please try again later or contact the system administrator.");
    }

    public List<Industry> getAll() {
        return industryRepository.findAll();
    }
}
