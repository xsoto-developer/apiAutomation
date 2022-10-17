package basicRestAssured;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
public class CreateItemCheckTest {
    @Test
    public void verifyCreateProjectJSONObject(){

        JSONObject body = new JSONObject();
        body.put("Content","Xime verify JSON :P");


        Response response =given()
                .auth()
                .preemptive()
//                .basic("ucb2022@ucb2022.com","12345")
                .basic("xsotodeveloper@gmail.com","12345678")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json");


        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("Xime verify JSON :P"));
//                .body("Icon",equalTo(1));

    }

    @Test
    public void verifyCreateProjectSCHEMA(){

        JSONObject body = new JSONObject();
        body.put("Content","ITEM-JSON-SCHEMA2");
//        body.put("Icon",1);

        Response response =given()
                .auth()
                .preemptive()
                .basic("xsotodeveloper@gmail.com","12345678")
                .body(body.toString())
                .log().all()
                .when()
//                .post("https://todo.ly/api/projects.json");
                .post("https://todo.ly/api/items.json");


        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("ITEM-JSON-SCHEMA2"));
//                .body("Icon",equalTo(1));;

        // verificacion por schema
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(
                        SchemaVersion.DRAFTV4
                ).freeze()).freeze();

        response.then()
                .body(matchesJsonSchemaInClasspath("schemaCreateResponse.json")
                        .using(jsonSchemaFactory));

        int id=response.then().extract().path("Id");
        String nameProject= response.then().extract().path("Content");

        System.out.println("************* ID: "+id);
        System.out.println("************* NAME_PROJECT: "+nameProject);

    }




}
