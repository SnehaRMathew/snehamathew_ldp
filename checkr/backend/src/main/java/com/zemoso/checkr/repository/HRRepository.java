package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.HR;
import java.util.List;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRRepository extends CrudRepository<HR, Long> {
    List<HR> findAll();
    // You can add custom query methods here if needed
}
