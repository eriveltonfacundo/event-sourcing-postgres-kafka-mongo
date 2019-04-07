package com.invillia.challenge.storeservice.rsql;

import cz.jirutka.rsql.parser.RSQLParser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RSQLComponent<T> {

    public Specification toSpecification(String filter){
        if(StringUtils.isEmpty(filter))
            return null;
        return new RSQLParser().parse(filter).accept(new CustomRsqlVisitor<T>());
    }
}
