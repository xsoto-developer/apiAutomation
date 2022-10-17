package basicRestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class ItemTest {

    @Test
    public void verifyCreateItemJsonObject(){
        String now = getCurrentDate();
        JSONObject body = new JSONObject();
        body.put("Content","NewItem".concat(now));

        //create
        Response response =given()
                .auth()
                .preemptive()
                .basic("xsotodeveloper@gmail.com","12345678")
                .body(body.toString())
                .log().all()
        .when()
                .post("https://todo.ly/api/items.json");
        //verify create
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("NewItem".concat(now)));

        int idItem= response.then().extract().path("Id");

        // read
        response =given()
                .auth()
                .preemptive()
                .basic("xsotodeveloper@gmail.com","12345678")
                .log().all()
        .when()
                .get("https://todo.ly/api/items/"+idItem+".json");
        //verify read
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("NewItem".concat(now)));

        // update
        body.put("Content","UpdateItem".concat(now));
        response =given()
                .auth()
                .preemptive()
                .basic("xsotodeveloper@gmail.com","12345678")
                .body(body.toString())
                .log().all()
        .when()
                .put("https://todo.ly/api/items/"+idItem+".json");
        //verify update
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("UpdateItem".concat(now)));

        // delete
        response =given()
                .auth()
                .preemptive()
                .basic("xsotodeveloper@gmail.com","12345678")
                .log().all()
        .when()
                .delete("https://todo.ly/api/items/"+idItem+".json");
        //verify delete
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("UpdateItem".concat(now)));

    }

    private String getCurrentDate(){
        String current = "-";
        DateFormat format = new SimpleDateFormat("yyMMddHHmmssZ");
        current = current + format.format(new Date());
        System.out.println(current);
        return current;
    }

}
