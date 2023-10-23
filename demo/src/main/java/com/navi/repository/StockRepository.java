package com.navi.repository;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import com.navi.model.Stock;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StockRepository {

    private List<Stock> stocks = new ArrayList<>();

    @PostConstruct
    public void init() {
        stocks.add(new Stock("AAPL", "Apple Inc.", 172.88, 175.31, 59970000));
        stocks.add(new Stock("GOOG", "Alphabet Inc.", 136.74, 138.59, 24020000));
        stocks.add(new Stock("MSFT", "Microsoft Corporation", 326.67, 331.72, 24990000));
    }

    public Stock viewStock(String ticker) {
        for (Stock stock : stocks) {
            if (stock.getTicker().equals(ticker)) {
                return stock;
            }
        }
        return null;
    }
}
