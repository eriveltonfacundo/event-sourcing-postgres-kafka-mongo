package com.invillia.challenge.viewservice.repositories;

import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;
import com.invillia.challenge.viewservice.controllers.PageResponse;
import com.invillia.challenge.viewservice.models.Store;
import com.invillia.challenge.viewservice.utils.SortUtil;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

@Repository
public class StoreReadRepository<T> {

    private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

    @Autowired
    private MongoTemplate template;

    @GraphQLQuery(name = "stores")
    public PageResponse<Store> findAll(@GraphQLArgument(name = "filter") String filter,
                                       @GraphQLArgument(name = "sort", defaultValue = "id") String sort,
                                       @GraphQLArgument(name = "page", defaultValue = "0") Integer page,
                                       @GraphQLArgument(name = "size", defaultValue = "100") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, SortUtil.toSort(sort));
        Query query = new Query().with(pageRequest);
        if (StringUtils.isNotBlank(filter))
            query.addCriteria(pipeline.apply(filter, Store.class).query(new MongoVisitor()));
        List<Store> stores = template.find(query, Store.class);
        long totalElements = template.count(query, Store.class);
        return new PageResponse<Store>(stores, Math.ceil(totalElements/size), totalElements);
    }

}
