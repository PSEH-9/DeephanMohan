package weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    private String cod;
    private String message;
    private String cnt;
    private List<JsonNode> list;

    public Weather() {
    }


    public List<JsonNode> getList() {
        return list;
    }

    public void setList(List<JsonNode> list) {
        this.list = list;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String toString(String city, Long min, Long max, boolean carryUmbrella, boolean useSunScreen) {
        return "Weather forecast for the city "+city +
                " for the next three days: \n\nMaximum temperature(In kelvin) = '" + max + '\'' +
                "\nMinimum temperature(In kelvin) = '" + min +'\''+
                "\n"+(carryUmbrella ? new String("Carry an umbrella!") : "")+
                "\n"+(useSunScreen ? new String("Use Sunscreen Lotion!") : "");
    }
}