package sg.nus.edu.calculator;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import sg.nus.edu.calculator.service.CoinCalculatorServiceImpl;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import sg.nus.edu.calculator.config.CoinCalculatorConfiguration;
import sg.nus.edu.calculator.resource.CoinCalculatorResource;

public class CoinCalculatorApplication extends Application<CoinCalculatorConfiguration>{

	 public static void main(final String[] args) throws Exception {
	        new CoinCalculatorApplication().run(args);
	    }
	 
	 @Override
	 public void initialize(final Bootstrap<CoinCalculatorConfiguration> bootstrap) {
		 
	    }
	 
	 @Override
	    public void run(CoinCalculatorConfiguration configuration, Environment environment) {
		    final CoinCalculatorServiceImpl service = new CoinCalculatorServiceImpl();
	        final CoinCalculatorResource resource = new CoinCalculatorResource(service);
	        environment.jersey().register(resource);

	        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
	        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "http://localhost:3000");
	        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
	        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
	        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
	    
	        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	    }
}
