package SpotifyAPITest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Predicate;

import static io.restassured.RestAssured.given;

public class Test1 {
    @Test
    public void test(){
        Predicate<Integer> isEven = a-> (a%2 == 0);
        List<Integer> list = List.of(1,2,3,4,5,6);
        list.stream().filter(isEven).forEach(System.out::println);
    }
}
