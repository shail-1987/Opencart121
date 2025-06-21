package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {
	
	int id;
	
	@Test(priority=1)
	void getUsers()
	{
		
		given()
		
		.when()
			.get("https://www.google.com/#sbfbu=1&pi=")
		
		.then()
			.statusCode(200)
			//.body("page",equalTo(2))
			.log().all();
		
	}
	
	@Test(priority=2)
	void createUsers() {
		
		HashMap data = new HashMap();
		data.put("name", "morpheus");
		data.put("job", "leader");
		
		
	id=given()
			.contentType("application/json")
			.body(data)
		
		.when()
		    .post("https://reqres.in/api/users")
		    .jsonPath().getInt("id");		    
		
//		.then()
//			.statusCode(201)
//			.log().all();
		
	}
	
	@Test(priority=3,dependsOnMethods= {"createUsers"})
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "pavan");
		data.put("job", "trainer");
		
		given()
				.contentType("application/json")
				.body(data)
			
		.when()
			    .put("https://reqres.in/api/users/"+id)
			    		
		.then()
			.statusCode(200)
			.log().all();
				
	}
	
	@Test(priority=4)
	void deleteUser() {
	
	given()
	
	.when()
		.delete("https://reqres.in/api/users/" + id)
	
	.then()
		.statusCode(204)
		.log().all();

	}
	





}
	


