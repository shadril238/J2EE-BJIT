package com.shadril.main.service;

import com.shadril.main.model.Candidate;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CandidateService {
    List<Candidate> candidateList = new ArrayList<>();
    @PostConstruct
    public void init(){
        candidateList.add(new Candidate(101, "ABC"));
        candidateList.add(new Candidate(102, "DEF"));
        candidateList.add(new Candidate(103, "GHI"));
    }
    public Candidate findById(Integer id){
        for(Candidate candidate : candidateList){
            if(candidate.getId() == id)
                return candidate;
        }
        return null;
    }

}
