package SpotifyAPITest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Test1 {
    @Test
    public void test(){
        String code = "AQA9ryLmpakeTIo9cGfD487bloIMyy_aM2I0uKQo4wqiAozhs5CitfnaWSxA_YsLjEED4Nalxfvul4z3ssBH-1czpmrmFcFF7huvKVb8jtlXx4rEW1kZ9DuLZvTC9ZXK_ItfP0ADEXZez7eXfNvSzz7b8flvzofcX9aYLafNaHr9p3g";

        RestAssured.baseURI = "https://accounts.spotify.com";
        String authorization = "Basic " + "MmMyY2I2NDk3YWFjNDkzYmE2ODllODA3MzIzYWE0MmE6NWRhMDIwNjRjNTdhNDI1OWJhZWU5ZDlmZGNjYjYwNDc=";
        given().header("Authorization",authorization)
                .header("Content-Type","application/x-www-form-urlencoded")
                .formParam("grant_type","authorization_code")
                .formParam("code",code)
                .formParam("redirect_uri","https://localhost:3000/callback")
                .when().post("/api/token")
                .then().log().all().assertThat().statusCode(200);
    }
}
