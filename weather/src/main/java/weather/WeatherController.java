package weather;

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
   

    @RequestMapping(value ="/weather",produces = "application/json")
    public String getWeather(@RequestParam(value="city", defaultValue="London") String city){
        String test = "https://api.openweathermap.org/data/2.5/forecast?q="+city+",us&mode=json&appid=d2929e9483efc82c82c32ee7e02d563e";
        RestTemplate restTemplate = new RestTemplate();
        Weather st = restTemplate.getForObject(test, Weather.class);
        boolean carryUmbrella = false;
        boolean useSunScreen = false;
        ObjectNode resp = (ObjectNode) st.getList().get(0);
        ObjectNode temp = (ObjectNode) resp.get("main");
        String weather =  resp.get("weather").get(0).get("main").toString();

        Long tempMin  = temp.get("temp_min").asLong();
        Long tempMax = temp.get("temp_max").asLong();
        Long currTemp = temp.get("temp").asLong();
        if(weather.equals("Rain")) carryUmbrella=true;
        if(currTemp > MAX_TEMPERATURE) useSunScreen = true;
        return st.toString(city,tempMin, tempMax, carryUmbrella, useSunScreen);


        //return st.toString("Dallas",200.0,300.23,true,true);
    }

    /*
    @RequestMapping(value ="/weather/{city}",produces = "application/json")
    public String getWeather(@RequestParam(value="city", defaultValue="London") String name) {
        //return JSON response of the weather for the next three days
    }*/

}
