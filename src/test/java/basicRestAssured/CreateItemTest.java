package basicRestAssured;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateItemTest {

    /**
     * given  = configuracion
     * when() =
     *
     * **/

    @Test
//    public  void verifyCrateProject(){
    public  void verifyCrateItem(){
        given()
                .auth()
                .preemptive()
//                .basic("ucb2022@ucb2022.com","12345")
                .basic("xsotodeveloper@gmail.com","12345678")
//                .body("{\n" +
//                        "  \"Content\":\"Xime\",\n" +
//                        "  \"Icon\":4\n" +
//                        "}\n")
                .body("{\n" +
                        "  \"Content\":\"Xim\"\n" +
//                        "  \"Icon\":4\n" +
                        "}\n")
                .log().all()
         .when()
//                .post("https://todo.ly/api/projects.json")
                .post("https://todo.ly/api/items.json")
        .then()
                .log().all();
    }

    @Test
    public  void verifyCrateItemJSONObject(){

        JSONObject body = new JSONObject();
        body.put("Content","Xim Object");
//        body.put("Icon",1);


        given()
                .auth()
                .preemptive()
                .basic("xsotodeveloper@gmail.com","12345678")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json")
                .then()
                .log().all();
    }

    @Test
    public void verifyCreateProjectFile(){
        String jsonFile= new File("").getAbsolutePath()+"/src/test/resources/createProject.json";

        given()
                .auth()
                .preemptive()
                .basic("xsotodeveloper@gmail.com","12345678")
                .body(new File(jsonFile))
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json")
                .then()
                .log().all();

    }
}
