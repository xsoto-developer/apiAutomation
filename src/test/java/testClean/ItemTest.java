package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import util.ApiConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;

public class ItemTest {
    Response response;
    JSONObject body= new JSONObject();
    RequestInfo requestInfo = new RequestInfo();

    @Test
    public void verifyCRUDItem(){
        String now = getCurrentDate();

        // CREATE ITEM (creación)
        body.put(ApiConfiguration.CONTENT,ApiConfiguration.NEW.concat(now));
        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make(ApiConfiguration.POST).send(requestInfo);
        response.then().body(ApiConfiguration.CONTENT,equalTo(body.get(ApiConfiguration.CONTENT))).statusCode(200);

        // UPDATE ITEM (actualización)
        int idItem =response.then().extract().path("Id");
        body.put(ApiConfiguration.CONTENT,ApiConfiguration.UPDATE.concat(now));
        requestInfo.setUrl(String.format(ApiConfiguration.UPDATE_ITEM,idItem));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make(ApiConfiguration.PUT).send(requestInfo);
        response.then().body(ApiConfiguration.CONTENT,equalTo(body.get(ApiConfiguration.CONTENT))).statusCode(200);

        // READ ITEM (lectura)
        requestInfo.setUrl(String.format(ApiConfiguration.READ_ITEM,idItem));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make(ApiConfiguration.GET).send(requestInfo);
        response.then().body(ApiConfiguration.CONTENT,equalTo(body.get(ApiConfiguration.CONTENT))).statusCode(200);

        // DELETE ITEM (eliminación)
        requestInfo.setUrl(String.format(ApiConfiguration.DELETE_ITEM,idItem));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make(ApiConfiguration.DELETE).send(requestInfo);
        response.then().body(ApiConfiguration.CONTENT,equalTo(body.get(ApiConfiguration.CONTENT))).statusCode(200);
    }

    private String getCurrentDate(){
        DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String current = "->" + format.format(new Date());
        System.out.println(current);
        return current;
    }

}
