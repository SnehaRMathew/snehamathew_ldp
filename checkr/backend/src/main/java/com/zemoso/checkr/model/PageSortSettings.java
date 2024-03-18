package com.zemoso.checkr.model;

import com.zemoso.checkr.dto.AdverseAction;
import com.zemoso.checkr.enums.AdjudicationType;
import com.zemoso.checkr.enums.CandidateStatus;
import com.zemoso.checkr.enums.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class FilterSettings {
    private int pageNo=1;
    private int pageSize=10;
    private String  name;
    private CandidateStatus status;
    private AdjudicationType adjudicationType;

    private String sortBy;
    private SortOrder sortOrder;

    public Pageable PageRequest(){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy),);
    }

}
