package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.Search;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SearchRepository extends CrudRepository<Search, Long> {
    List<Search> findAll();
    Search getById(Long id);
}
