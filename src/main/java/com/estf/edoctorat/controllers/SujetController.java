package com.estf.edoctorat.controllers;

import com.estf.edoctorat.dto.Result;
import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.services.SujetService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SujetController {

    @Autowired
    private SujetService sujetService;

    @GetMapping("/get-published-subjects/")
    public Result<SujetDTO> getPublishedSubjects(
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(defaultValue = "0") int offset,
            HttpServletRequest request,
            UriComponentsBuilder uriBuilder) {

        Page<SujetDTO> page = sujetService.getPublishedSubjects(offset, limit);

        Result<SujetDTO> result = new Result<>();
        result.setResults(page.getContent());
        result.setCount((int) page.getTotalElements());

        // Build next URL if there is a next page
        if (page.hasNext()) {
            String nextUrl = uriBuilder.path(request.getRequestURI())
                    .queryParam("limit", limit)
                    .queryParam("offset", offset + limit)
                    .build()
                    .toUriString();
            result.setNext(nextUrl);
        }

        // Build previous URL if there is a previous page
        if (page.hasPrevious()) {
            String previousUrl = uriBuilder.path(request.getRequestURI())
                    .queryParam("limit", limit)
                    .queryParam("offset", Math.max(0, offset - limit))
                    .build()
                    .toUriString();
            result.setPrevious(previousUrl);
        }

        return result;
    }
}