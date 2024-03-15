package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SearchRepository extends JpaRepository<Search, Long> {
    List<Search> findAll();
    Search getById(Long id);
}
