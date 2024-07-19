package sg.nus.edu.calculator.resource;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sg.nus.edu.calculator.model.CoinCalculationRequest;
import sg.nus.edu.calculator.model.CoinCalculationResponse;
import sg.nus.edu.calculator.service.CoinCalculatorService;

@Path("/coin-calculator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoinCalculatorResource {

	private static final List<Double> VALID_DENOMINATIONS = List.of(
            0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0
    );
	
	 private final CoinCalculatorService coinCalculatorService;

	 public CoinCalculatorResource(CoinCalculatorService coinCalculatorService) {
	        this.coinCalculatorService = coinCalculatorService;
	    }
	
	@POST
	@Path("/calculate")
    public Response calculateMinimumCoins(CoinCalculationRequest request) {
        double targetAmount = request.getTargetAmount();
        List<Double> coinDenominations = request.getCoinDenominations();

        if (targetAmount < 0 || targetAmount > 10000) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Target amount must be between 0 and 10,000.00").build();
        }
        if (coinDenominations  == null || coinDenominations .isEmpty()) {
        	 return Response.status(Response.Status.BAD_REQUEST).entity("Denominations list must not be empty").build();
        }
        for (Double denomination : coinDenominations) {
            if (!VALID_DENOMINATIONS.contains(denomination)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid coin denomination: " + denomination).build();
            }
        }

        CoinCalculationResponse result = coinCalculatorService.calculateMinimumCoins(targetAmount, coinDenominations);
        return Response.ok(result).build();
    }
	
}
