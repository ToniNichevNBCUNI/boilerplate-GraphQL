package com.cnbc.quotegraph.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import com.netflix.graphql.dgs.DgsData;
import com.cnbc.quotegraph.model.Quote;

import java.time.Instant;
import java.util.Map;

@DgsComponent
public class QuoteFederationResolver {

    /**
     * Entity resolver for Apollo Federation.
     * This allows other subgraphs to reference Quote by its key field (symbol).
     */
    @DgsEntityFetcher(name = "Quote")
    public Quote resolveQuote(Map<String, Object> values) {
        String symbol = (String) values.get("symbol");
        
        // TODO: Replace with actual data source lookup
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
}
