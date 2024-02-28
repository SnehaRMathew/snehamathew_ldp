package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.HR;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HRRepository extends CrudRepository<HR, Long> {
    List<HR> findAll();

}
