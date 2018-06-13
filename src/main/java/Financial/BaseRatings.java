package Financial;

public class BaseRatings {

    public void loadProperties() {
        Thread.currentThread().getContextClassLoader().getResource("config.properties");
    }

}
