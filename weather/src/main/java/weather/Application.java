package weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws ParseException {
        int[] vals = {1569067200,1569078000,1569088800};
        for(Integer val: vals) {
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = originalFormat.parse(val.toString());
            System.out.println("\nDate => " + date);
        }
        SpringApplication.run(Application.class, args);
    }
}
