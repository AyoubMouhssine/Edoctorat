package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.Result;
import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.mappers.SujetMapper;
import com.estf.edoctorat.models.Sujet;
import com.estf.edoctorat.repositories.SujetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SujetService {

    @Autowired
    private SujetRepository sujetRepository;

    @Autowired
    private SujetMapper sujetMapper;

    public Result<SujetDTO> getSujets(int limit, int offset) {
        List<Sujet> sujets = sujetRepository.findSujetsWithPagination(limit, offset);
        List<SujetDTO> sujetDTOs = sujets.stream().map(sujetMapper::toDTO).collect(Collectors.toList());
        int total = (int) sujetRepository.count(); // Get total count
        // Specify Result<SujetDTO> explicitly
        Result<SujetDTO> result = new Result<>(sujetDTOs, total);
        return result;
    }

    public Result<SujetDTO> getSujets() {
        List<Sujet> sujets = sujetRepository.findAll();
        List<SujetDTO> sujetDTOs = sujets.stream().map(sujetMapper::toDTO).collect(Collectors.toList());
        // Specify Result<SujetDTO> explicitly
        Result<SujetDTO> result = new Result<>(sujetDTOs, sujetDTOs.size()); // Using size for total count
        return result;
    }
}
