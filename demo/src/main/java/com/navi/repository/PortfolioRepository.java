package com.navi.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import com.navi.model.Portfolio;
import com.navi.model.Stock;

@Repository
public class PortfolioRepository {
    
    private List<Portfolio> portfolios = new ArrayList<>();

    Stock apple = new Stock("AAPL", "Apple Inc.", 172.88, 175.31, 59970000);
    List<Stock> stocks = new ArrayList<>();

    @PostConstruct
    public void init() {
        stocks.add(apple);
        portfolios.add(new Portfolio(stocks, 518.64));
    }

    public Portfolio getPortfolio(String id) {
        for (Portfolio portfolio : portfolios) {
            if (portfolio.getId().equals(id)) {
                return portfolio;
            }
        }
        return null;
    }

    public List<Stock> getPortfolioStocks(String id) {
        for (Portfolio portfolio : portfolios) {
            if (portfolio.getId().equals(id)) {
                return portfolio.getStocks();
            }
        }
        return null;
    }

    public String createPortfolio() {
        List<Stock> stocks = new ArrayList<>();
        Portfolio portfolio = new Portfolio(stocks, 0.0);
        portfolios.add(portfolio);
        return portfolio.getId();
    }

    public Portfolio deletePortfolio(String id) {
        for (Portfolio portfolio : portfolios) {
            if (portfolio.getId().equals(id)) {
                portfolios.remove(portfolio);
                return portfolio;
            }
        }
        return null;
    }

    public Portfolio addStock(String id, Stock stock) {
        for (Portfolio portfolio : portfolios) {
            if (portfolio.getId().equals(id)) {
                portfolio.getStocks().add(stock);
                return portfolio;
            }
        }
        return null;
    }

    public Portfolio deleteStock(String id, String ticker) {
        for (Portfolio portfolio : portfolios) {
            if (portfolio.getId().equals(id)) {
                for (Stock stock : portfolio.getStocks()) {
                    if (stock.getTicker().equals(ticker)) {
                        portfolio.getStocks().remove(stock);
                        return portfolio;
                    }
                }
            }
        }
        return null;
    }
}