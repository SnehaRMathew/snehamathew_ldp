package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.AdverseActionNotification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdverseActionNotificationRepository extends CrudRepository<AdverseActionNotification, Long> {
    List<AdverseActionNotification> findAll();
    AdverseActionNotification getById(Long id);
}
