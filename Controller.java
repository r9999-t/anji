import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;

@RestController
public class StockTradeRestController {
	@Autowired
	StockTradeRepository stockTradeRepository;

  @RequestMapping(value= "/trades", method = RequestMethod.GET)
  public ResponseEntity<List<StockTrade>> getAllTrades(){
    List<StockTrade> stocks = stockTradeRepository.findAll();
    ResponseEntity<List<StockTrade>> responseEntity =  new ResponseEntity<>(stocks, HttpStatus.OK);
	return responseEntity;
  }
  
  @RequestMapping(value= "/trades/{id}", method = RequestMethod.GET)
  public ResponseEntity<StockTrade> getTrade(@PathVariable int id){
    Optional<StockTrade> stocks = stockTradeRepository.findById(id);
    if(stocks.isPresent()) {
    	ResponseEntity<StockTrade> responseEntity =  new ResponseEntity<>(stocks.get(), HttpStatus.OK);
    	return responseEntity;
    }
    else {
    	ResponseEntity<StockTrade> responseEntity =  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	return responseEntity;
    }
  }
  
  @RequestMapping(value = "/trades", method = RequestMethod.POST)
  public ResponseEntity<StockTrade> insertTrade(@RequestBody StockTrade stockTrade){
	  StockTrade stock = stockTradeRepository.save(stockTrade);
	  ResponseEntity<StockTrade> response = new ResponseEntity<StockTrade>(stock, HttpStatus.CREATED);
	  return response;
  }
  
  @RequestMapping(value="/trades/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteTrade(@PathVariable int id){
	  ResponseEntity<String> response = new ResponseEntity<String>("",HttpStatus.METHOD_NOT_ALLOWED)
	  
  }
}
