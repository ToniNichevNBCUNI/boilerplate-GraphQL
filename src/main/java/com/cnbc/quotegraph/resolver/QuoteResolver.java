package com.cnbc.quotegraph.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.cnbc.quotegraph.model.Quote;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class QuoteResolver {

    @DgsQuery
    public Quote quote(@InputArgument String symbol) {
        // TODO: Replace with actual data source
        return Quote.builder()
                .id(symbol + "-" + System.currentTimeMillis())
                .symbol(symbol)
                .price(150.25)
                .change(2.50)
                .changePercent(1.69)
                .volume(1000000)
                .timestamp(Instant.now().toString())
                .build();
    }

    @DgsQuery
    public List<Quote> quotes(@InputArgument List<String> symbols) {
        return symbols.stream()
                .map(this::quote)
                .collect(Collectors.toList());
    }    
    
}
