import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest01 {

    @Test
    public void test01(){

        String url = "https://restful-booker.herokuapp.com/booking";

        Response response = given().when().get(url);
        //given().when().get(url) -> end point'e göndermek için request oluşturmuş olduk.
        //Response response -> api tarafından bana dönen response (cevap)

        // Response response = given().auth().basic("username", "password" ).when().get(url)
        // basic auth ile request göndermek için

        //response.prettyPrint();     //response'taki body'i yazdırır

        //response.prettyPeek();         //response taki herşeyi yazdırır.

        //response.peek();

        //response.print();     //string olarak dataye verir
        // [{"bookingid":1215},{"bookingid":844},{"bookingid":87},{"bookingid":747}, ...]

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        System.out.println(response.contentType());

        // 1- JUnit Assert'leri ile API testi yapabiliriz.
        assertEquals("status code hatalı",200,response.getStatusCode());
        assertEquals("status line hatalı","HTTP/1.1 200 OK",response.getStatusLine());
        assertEquals("content teyp hatalı","application/json; charset=utf-8",response.contentType());

        // 2- assertThat() ile API testi yapabiliriz
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType("application/json; charset=utf-8");
        response.then().assertThat().statusLine("HTTP/1.1 200 OK");

        response.then().assertThat().
                contentType("application/json; charset=utf").
                statusLine("HTTP/1.1 200 OK").
                statusCode(200);
    }
}


