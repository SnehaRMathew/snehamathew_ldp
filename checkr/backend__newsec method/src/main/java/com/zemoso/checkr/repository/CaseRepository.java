package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.Case;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CaseRepository extends CrudRepository<Case, Long> {
    List<Case> findAll();
    Case getById(Long id);
}
