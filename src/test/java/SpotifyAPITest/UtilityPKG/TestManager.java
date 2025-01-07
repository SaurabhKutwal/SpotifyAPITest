package SpotifyAPITest.UtilityPKG;

import SpotifyAPITest.POJOClass.PLayList.CreatePlayList;
import SpotifyAPITest.RunnerFile.StepDefinitions.PLaylist;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TestManager {
    public static Properties properties;
    public static Response response = null;
    public static RequestSpecification request = null;
    static FileInputStream file;

    public static CreatePlayList pLaylist;

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

    public static CreatePlayList createPlayList(String name, String description, String publicStatus){
        pLaylist = new CreatePlayList();
        pLaylist.setName(name);
        pLaylist.setDescription(description);
        pLaylist.setPublicStatus(publicStatus);
        return  pLaylist;
    }
}
