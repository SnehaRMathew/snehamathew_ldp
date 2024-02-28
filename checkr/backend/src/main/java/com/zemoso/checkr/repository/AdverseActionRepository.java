package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.AdverseAction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdverseActionRepository extends CrudRepository<AdverseAction, Long> {
    List<AdverseAction> findAll();
    AdverseAction getById(Long id);
}
