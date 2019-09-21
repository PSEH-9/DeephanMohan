package weather;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }


    @RequestMapping(value ="/weather",produces = "application/json")
    public String getWeather(){
        String test = "https://api.openweathermap.org/data/2.5/forecast?q=London,us&mode=json&appid=d2929e9483efc82c82c32ee7e02d563e";
        RestTemplate restTemplate = new RestTemplate();
        Weather st = restTemplate.getForObject(test, Weather.class);
        return st.toString();
    }

    /*
    @RequestMapping(value ="/weather/{city}",produces = "application/json")
    public String getWeather(@RequestParam(value="city", defaultValue="London") String name) {
        //return JSON response of the weather for the next three days
    }*/

}
