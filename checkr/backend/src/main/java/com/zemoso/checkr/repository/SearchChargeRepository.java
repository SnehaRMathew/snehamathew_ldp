package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.SearchCharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchChargeRepository extends JpaRepository<SearchCharge,Long> {
   List<SearchCharge> findBySearchId(long id);
}
