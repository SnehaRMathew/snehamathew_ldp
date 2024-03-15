package com.zemoso.checkr.model;

import com.zemoso.checkr.enums.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@Builder
public class PageSortSettings {
    private int pageNo=1;
    private int pageSize=10;
    private String sortBy;
    private Sort.Direction sortOrder;
    private String name;
    private String status;


}
