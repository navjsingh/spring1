package com.navi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.navi.exception.IncorrectDataException;
import com.navi.model.Portfolio;
import com.navi.model.Stock;
import com.navi.model.User;
import com.navi.service.PortfolioService;
import com.navi.service.StockService;
import com.navi.service.UserService;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    
    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;

    @GetMapping("/getPortfolio")
    public ResponseEntity<Portfolio> getPortfolio(@RequestParam String id) {
        Portfolio portfolio = portfolioService.getPortfolio(id);
        if (portfolio == null) {
            throw new IncorrectDataException("Portfolio with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Portfolio>(portfolio, HttpStatus.OK);
        }
    }

    @GetMapping("/getPortfolioStocks")
    public ResponseEntity<List<Stock>> getPortfolioStocks(@RequestParam String id) {
        List<Stock> stocks = portfolioService.getPortfolioStocks(id);
        if (stocks == null) {
            throw new IncorrectDataException("No stocks found for portfolio " + id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
        }
    }

    @PostMapping("/createPortfolio")
    public ResponseEntity<String> createPortfolio(@RequestParam String email) {
        User user = userService.getUser(email);
        if (user == null) {
            throw new IncorrectDataException("User with email " + email + " does not exist", HttpStatus.NOT_FOUND);
        } else {
            if (userService.hasPortfolio(email)) {
                throw new IncorrectDataException("User with email " + email + " already has a portfolio", HttpStatus.CONFLICT);
            } else {
                String id = portfolioService.createPortfolio();
                userService.assignPortfolio(email, id);
                return new ResponseEntity<String>("Portfolio created with id " + id, HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/deletePortfolio")
    public ResponseEntity<String> deletePortfolio(@RequestParam String email) {
        User user = userService.getUser(email);
        if (user == null) {
            throw new IncorrectDataException("User with email " + email + " does not exist", HttpStatus.NOT_FOUND);
        } else {
            if (!userService.hasPortfolio(email)) {
                throw new IncorrectDataException("User with email " + email + " does not have a portfolio", HttpStatus.NOT_FOUND);
            } else {
                portfolioService.deletePortfolio(user.getPortfolioId());
                userService.assignPortfolio(email, null);
                return new ResponseEntity<String>("Portfolio deleted", HttpStatus.OK);
            }
        }
    }

    @PostMapping("/addStock")
    public ResponseEntity<String> addStock(@RequestParam String email, @RequestParam String ticker) {
        User user = userService.getUser(email);
        if (user == null) {
            throw new IncorrectDataException("User with email " + email + " does not exist", HttpStatus.NOT_FOUND);
        }
        if (!userService.hasPortfolio(email)) {
            throw new IncorrectDataException("User with email " + email + " does not have a portfolio", HttpStatus.NOT_FOUND);
        }
        Stock stock = stockService.viewStock(ticker);
        if ( stock == null) {
            throw new IncorrectDataException("Stock with ticker " + ticker + " does not exist", HttpStatus.NOT_FOUND);
        }
        portfolioService.addStock(user.getPortfolioId(), stock);
        return new ResponseEntity<String>("Stock " + ticker + " has been added to portfolio", HttpStatus.OK);
    }

    @DeleteMapping("/deleteStock")
    public ResponseEntity<String> deleteStock(@RequestParam String email, @RequestParam String ticker) {
        User user = userService.getUser(email);
        if (user == null) {
            throw new IncorrectDataException("User with email " + email + " does not exist", HttpStatus.NOT_FOUND);
        }
        if (!userService.hasPortfolio(email)) {
            throw new IncorrectDataException("User with email " + email + " does not have a portfolio", HttpStatus.NOT_FOUND);
        }
        Stock stock = stockService.viewStock(ticker);
        if ( stock == null) {
            throw new IncorrectDataException("Stock with ticker " + ticker + " does not exist", HttpStatus.NOT_FOUND);
        }
        if (portfolioService.deleteStock(user.getPortfolioId(), ticker) == null) {
            throw new IncorrectDataException("Stock " + ticker + " does not exist in portfolio", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Stock " + ticker + " has been deleted from portfolio", HttpStatus.OK);
    }

}
