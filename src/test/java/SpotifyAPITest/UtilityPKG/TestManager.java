package SpotifyAPITest.UtilityPKG;

import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestManager {
    public static Properties properties;
    public static Response response = null;
    
    public static String accessToken;
    public static String refreshToken;




    public static Properties loadProperties() throws IOException {
        if(properties == null){
            FileInputStream file = new FileInputStream("/Users/saurabh/Desktop/JavaSelenium/SpotifyAPITest/src/test/java/SpotifyAPITest/Properties/UserDetails.properties");
            properties = new Properties();
            properties.load(file);
        }
        return properties;
    }
}
