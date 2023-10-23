package com.navi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navi.model.Stock;
import com.navi.repository.StockRepository;

@Service
public class StockService {
    
    @Autowired
    private StockRepository stockRepository;

    public Stock viewStock(String ticker) {
        return stockRepository.viewStock(ticker);
    }
}
