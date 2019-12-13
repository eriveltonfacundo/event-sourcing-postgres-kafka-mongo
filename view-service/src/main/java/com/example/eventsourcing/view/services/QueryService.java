package com.example.eventsourcing.view.services;

import com.example.eventsourcing.view.fiql.RSQLQuery;
import com.example.eventsourcing.view.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.eventsourcing.view.utils.SortUtil.toSort;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class QueryService {

    @Autowired
    private MongoTemplate template;

    public <T> PageResponse<T> filter(String filter, String sort, Integer page, Integer size, Class<T> clazz) {
        PageRequest pageRequest = PageRequest.of(page, size, toSort(sort));
        Query query = new Query().with(pageRequest);
        if (isNotBlank(filter))
            query.addCriteria(new RSQLQuery().toCriteria(filter, clazz));
        List<T> objects = template.find(query, clazz);
        long totalElements = template.count(query, clazz);
        return new PageResponse<T>(objects, Math.ceil(totalElements / size), totalElements);
    }

}
