package com.navi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navi.model.Portfolio;
import com.navi.model.Stock;
import com.navi.repository.PortfolioRepository;

@Service
public class PortfolioService {
    
    @Autowired
    private PortfolioRepository portfolioRepository;

    public Portfolio getPortfolio(String id) {
        return portfolioRepository.getPortfolio(id);
    }

    public List<Stock> getPortfolioStocks(String id) {
        return portfolioRepository.getPortfolioStocks(id);
    }

    public String createPortfolio() {
        return portfolioRepository.createPortfolio();
    }

    public Portfolio deletePortfolio(String id) {
        return portfolioRepository.deletePortfolio(id);
    }

    public Portfolio addStock(String id, Stock stock) {
        return portfolioRepository.addStock(id, stock);
    }

    public Portfolio deleteStock(String id, String ticker) {
        return portfolioRepository.deleteStock(id, ticker);
    }
}