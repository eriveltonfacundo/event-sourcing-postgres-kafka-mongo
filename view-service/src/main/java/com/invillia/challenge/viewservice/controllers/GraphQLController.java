package com.invillia.challenge.viewservice.controllers;


import graphql.GraphQL;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

@RestController
@RequestMapping("/v1/graphql")
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping
    public Map<String, Object> graphql(HttpServletRequest raw) throws IOException {
        return graphQL.execute(IOUtils.toString(raw.getReader())).toSpecification();
    }

}
