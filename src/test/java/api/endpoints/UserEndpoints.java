package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	
	

	// this is user endpoints for CURD Operation to the user API

	public static Response RecreateUser(User payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				.when().post(Routes.post_url);

		return response;
	}
	
	
	
	
	

	public static Response readerUser(String Username) {

		Response response = given().pathParam("username", Username)

				.when().get(Routes.Get_url);

		return response;
	}

	
	
	
	public static Response UpdateUser(String Username, User payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", Username).body(payload)

				.when().put(Routes.Update_Url);

		return response;
	}

	
	
	
	public static Response DeleteUser(String Username) {

		Response response = given().pathParam("username", Username)

				.when().delete(Routes.Delete_url);

		return response;
	}

}
