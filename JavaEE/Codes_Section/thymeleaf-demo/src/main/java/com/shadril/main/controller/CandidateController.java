package com.shadril.main.controller;

import com.shadril.main.model.Candidate;
import com.shadril.main.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @RequestMapping("/")
    public String homepage() {
        return "home-page.html";
    }
    @RequestMapping("/candidate")
    public String findById(@RequestParam Integer id, Model model) {
        Candidate candidate = candidateService.findById(id);
        model.addAttribute("candidate", candidate);
        System.out.println(candidate.getName());
        return "candidate-info.html";
    }
}
