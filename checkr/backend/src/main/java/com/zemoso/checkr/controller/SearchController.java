package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.Search;
import com.zemoso.checkr.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Search")
public class SearchController {
    private final SearchRepository SearchRepository;

    @Autowired
    public SearchController(SearchRepository SearchRepository) {
        this.SearchRepository = SearchRepository;
    }

    @GetMapping
    public List<Search> getAllHRs() {
        return SearchRepository.findAll();
    }
}
