package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createuser(User payload) {
		
	Response response=given()
		   .accept(ContentType.JSON)
		   .contentType(ContentType.JSON)
		   .body(payload)
		   
		.when()
			.post(Roots.post_url);
		
		return response;
	}
public static Response getUser(String username) {
		
	Response response=given()
		   .accept(ContentType.JSON)
		   .contentType(ContentType.JSON)
		   .pathParam("username",username)
		   
		.when()
			.get(Roots.delete_url);
		
		return response;
	}
	
 public static Response updateUser(User payload, String username) {
		
	Response response=given()
		   .accept(ContentType.JSON)
		   .contentType(ContentType.JSON)
		   .body(payload)
		   .pathParam("username",username)
		   
		.when()
			.put(Roots.delete_url);
		
		return response;
	}
	public static Response deleteUser(String username) {
		
		return given()
			.accept(ContentType.JSON)
			.pathParam("username", username)
		.when()
			.delete(Roots.delete_url);
	}
}
