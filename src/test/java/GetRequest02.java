import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest02 {

    @Test
    public void test02(){

        String url = "https://reqres.in/api/users";
        Response response = given().when().get(url);

        // Header Test
        response.then().assertThat().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType(ContentType.JSON);

        response.then().assertThat().header("Via","1.1 vegur");
        response.then().assertThat().header("Server","cloudflare");

        // Body Test - id'si 1 olanın datalarının aşağıdaki gibi olup olmadığını test ediniz.
        /*
            "id": 1,
            "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
         */

        // 3- Matcher Class ile API testi yapabiliriz.
        response.then().body("data[0].email", equalTo("george.bluth@reqres.in"),
                "data[0].first_name",equalTo("George"),
                "data[0].last_name",equalTo("Bluth"));


    }

}
