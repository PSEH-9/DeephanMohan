package weather;

public class CityNotFoundException extends Exception {
    public CityNotFoundException(String message) {
        super(message);
    }
}
