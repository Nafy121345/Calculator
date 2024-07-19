package sg.nus.edu.calculator.service;

import java.util.List;

import sg.nus.edu.calculator.model.CoinCalculationResponse;


public interface CoinCalculatorService {
    CoinCalculationResponse calculateMinimumCoins(double targetAmount, List<Double> coinDenominations);
}
