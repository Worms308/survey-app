package com.github.worms308.surveyapp.api;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream().map(this::getNested).collect(Collectors.toList());
    }

    private GraphQLError getNested(GraphQLError graphQLError) {
        if (graphQLError instanceof ExceptionWhileDataFetching){
            ExceptionWhileDataFetching dataFetching = (ExceptionWhileDataFetching) graphQLError;
            if (dataFetching.getException() instanceof GraphQLError){
                return (GraphQLError) dataFetching.getException();
            }
        }
        return graphQLError;
    }


}
