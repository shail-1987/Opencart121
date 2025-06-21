package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;



public class differentWaysToCreatePostRequestBody {
	
	@Test(priority=1)
		    public void testPostUsingHashMap() {
		
	        // Create a HashMap with correct type
	        HashMap data = new HashMap();
	        
	        // Add data to the HashMap
	        data.put("name", "preethi");
	        data.put("location", "india");
	        data.put("phone", "1234567898");
	        
	        // Add courses as an array to the HashMap
	        String[] courseArray = {"English", "Commerce"};
	        data.put("courses", courseArray);
	        
	        // Send the POST request
	       
	      given()
	            .contentType(ContentType.JSON) // Use ContentType.JSON for better readability
	            .body(data) // Pass the actual data object here, not the string "data"
	        .when()
	            .post("http://localhost:3000/students")
//	            .jsonPath().getInt(id)
	            
	        .then()
	            .statusCode(201)
	            .body("name", equalTo("preethi"))
	            .body("location", equalTo("india"))
	            .body("phone", equalTo("1234567898"))
	            .body("courses[0]", equalTo("English"))
	            .body("courses[1]", equalTo("Commerce"))
	            .header("Content-Type", equalTo("application/json")) // Correct header key
	            .log().all();
		    }


	
//@Test(priority=2)
	void deleteRequest() {
		
		given()
		
		.when()
			.delete("http://localhost:3000/students")
			
		.then()
			.statusCode(200);
	}
	
@Test (priority=3)
public void testPostUsingJson() {
	
   JSONObject data=new JSONObject();
   data.put("name", "Atharv");
   data.put("location", "india");
   data.put("phone", "1234567898");
   
   String[] courseArray = {"English", "Commerce"};
   data.put("courses", courseArray);   
   
  given()
        .contentType(ContentType.JSON) // Use ContentType.JSON for better readability
        .body(data.toString()) // Convert to string 
    .when()
        .post("http://localhost:3000/students")
//        .jsonPath().getInt(id)
        
    .then()
        .statusCode(201)
        .body("name", equalTo("Atharv"))
        .body("location", equalTo("india"))
        .body("phone", equalTo("1234567898"))
        .body("courses[0]", equalTo("English"))
        .body("courses[1]", equalTo("Commerce"))
        .header("Content-Type", equalTo("application/json")) // Correct header key
        .log().all();
    }

@Test (priority=4)
public void testPostUsingPOJO() {
	
	PojoRequest data=new PojoRequest();
	data.setName("shail");
	data.setLocation("france");
	data.setPhone("879879879");
	
	String coursesArr[]= {"C","C++"};
	data.setCourses(coursesArr);
   
  given()
        .contentType(ContentType.JSON) // Use ContentType.JSON for better readability
        .body(data) //important 
    .when()
        .post("http://localhost:3000/students")
        
    .then()
        .statusCode(201)
        .body("name", equalTo("shail"))
        .body("location", equalTo("france"))
        .body("phone", equalTo("879879879"))
        .body("courses[0]", equalTo("C"))
        .body("courses[1]", equalTo("C++"))
        .header("Content-Type", equalTo("application/json")) // Correct header key
        .log().all();
    }

@Test (priority=5)
public void testPostUsingExternalFile() throws FileNotFoundException {
	
	File f=new File(".//body.json");
	FileReader fr=new FileReader(f);
	JSONTokener jt=new JSONTokener(fr);
	JSONObject data=new JSONObject(jt);
	  
  given()
        .contentType(ContentType.JSON) // Use ContentType.JSON for better readability
        .body(data.toString()) //important 
    .when()
        .post("http://localhost:3000/students")
        
    .then()
        .statusCode(201)
        .body("name", equalTo("preethi123"))
        .body("location", equalTo("india"))
        .body("phone", equalTo("1234567898"))
        .body("courses[0]", equalTo("English"))
        .body("courses[1]", equalTo("Commerce"))
        .header("Content-Type", equalTo("application/json")) // Correct header key
        .log().all();
    }


}
