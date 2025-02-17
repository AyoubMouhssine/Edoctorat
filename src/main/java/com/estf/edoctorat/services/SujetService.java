package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.mappers.SujetMapper;
import com.estf.edoctorat.models.Sujet;
import com.estf.edoctorat.repositories.SujetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SujetService {

    @Autowired
    private SujetRepository sujetRepository;

    private final SujetMapper mapper = SujetMapper.INSTANCE;

    public Page<SujetDTO> getPublishedSubjects(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset / limit, limit);
        Page<Sujet> sujetPage = sujetRepository.findByPublierTrue(pageRequest);
        return sujetPage.map(mapper::sujetToSujetDTO);
    }
}