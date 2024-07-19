package sg.nus.edu.calculator.model;

import java.util.List;

public class CoinCalculationResponse {
    private List<Double> coins;

    public CoinCalculationResponse(List<Double> coins) {
        this.coins = coins;
    }

    public List<Double> getCoins() {
        return coins;
    }

    public void setCoins(List<Double> coins) {
        this.coins = coins;
    }
}
