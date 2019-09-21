package weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class WeatherApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(WeatherApplication.class, args);
    }
}
