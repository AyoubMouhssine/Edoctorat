package com.estf.edoctorat.services;


import com.estf.edoctorat.models.Candidat;
import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.CandidatRepository;
import com.estf.edoctorat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatService {
    @Autowired
    CandidatRepository candidatRepository;


    public Candidat createCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }
}
