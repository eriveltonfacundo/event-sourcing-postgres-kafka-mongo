package com.invillia.challenge.view.fiql;

import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;
import org.springframework.data.mongodb.core.query.Criteria;

public class RSQLQuery {
    private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

    public Criteria toCriteria(String filter, Class<?> clazz){
        Condition<GeneralQueryBuilder> condition = pipeline.apply(filter, clazz);
        return condition.query(new MongoVisitor());
    }
}
