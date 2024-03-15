package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.Engage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EngageRepository extends CrudRepository<Engage, Long> {
    List<Engage> findAll();
    Engage getById(Long id);
}
