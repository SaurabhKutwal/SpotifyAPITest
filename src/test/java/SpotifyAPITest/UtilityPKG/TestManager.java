package SpotifyAPITest.UtilityPKG;

import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TestManager {
    public static Properties properties;
    public static Response response = null;
    static FileInputStream file;

    public static Properties loadProperties() throws IOException {
        if(properties == null){
            file = new FileInputStream("/Users/saurabh/Desktop/JavaSelenium/SpotifyAPITest/src/test/java/SpotifyAPITest/Properties/UserDetails.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        }
        return properties;
    }

    public static  void updateToken(String accessToken, String refreshToken) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/saurabh/Desktop/JavaSelenium/SpotifyAPITest/src/test/java/SpotifyAPITest/Properties/UserDetails.properties");
        properties.setProperty("accessToken", accessToken);
        properties.setProperty("refreshToken", refreshToken);
        properties.store(fileOutputStream, "File is modified");
        fileOutputStream.close();
    }
}
