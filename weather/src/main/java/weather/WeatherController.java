package weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    private static final Double MAX_TEMPERATURE = 313.15; // IN KELVIN EQUAL TO 40 DEGREE CELSIUS
    private static final String ENDPOINT = "https://api.openweathermap.org/data/2.5/forecast?q=";
    private static final String MODE = ",us&mode=json&appid=d2929e9483efc82c82c32ee7e02d563e";

    @RequestMapping(value ="/weather",produces = "application/json")
    public String getWeather(@RequestParam(value="city", defaultValue="London") String city) throws CityNotFoundException, ParseException {
        String url = ENDPOINT + city + MODE;
        RestTemplate restTemplate = new RestTemplate();
        Weather forecast = restTemplate.getForObject(url, Weather.class);
        boolean carryUmbrella = false;
        boolean useSunScreen = false;
        int noOfRecords = Integer.parseInt(forecast.getCnt());
        int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
        for(int i = 0; i< noOfRecords; i++) {
            ObjectNode response = (ObjectNode) forecast.getList().get(i);
            Long val  = (Long) response.get("dt").asLong();
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = originalFormat.parse(val.toString());
            if(date.getMonth() == thisMonth) {
                ObjectNode temp = (ObjectNode) response.get("main");
                String weather =  response.get("weather").get(0).get("main").toString();
                Double tempMin  = temp.get("temp_min").asDouble();
                Double tempMax = temp.get("temp_max").asDouble();
                Double currTemp = temp.get("temp").asDouble();
                if(weather.equals("Rain")) carryUmbrella=true;
                if(currTemp > MAX_TEMPERATURE) useSunScreen = true;
                return forecast.toString(city,tempMin, tempMax, carryUmbrella, useSunScreen);
            }
        }
        return "No forecast available!";
        // TEST FORECAST RESPONSE
        //return st.toString("Dallas",200.0,300.23,true,true);

    }

}
