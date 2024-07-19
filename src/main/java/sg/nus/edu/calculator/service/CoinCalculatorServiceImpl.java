package sg.nus.edu.calculator.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sg.nus.edu.calculator.model.CoinCalculationResponse;


public class CoinCalculatorServiceImpl implements CoinCalculatorService {

    @Override
    public CoinCalculationResponse calculateMinimumCoins(double targetAmount, List<Double> coinDenominations) {
        List<Double> result = new ArrayList<>();
        Collections.sort(coinDenominations, Collections.reverseOrder());

        for (double coin : coinDenominations) {
            while (targetAmount >= coin) {
                targetAmount = Math.round((targetAmount - coin) * 100.0) / 100.0;
                result.add(coin);            
            }
        }
        return new CoinCalculationResponse(result);
    }
}
