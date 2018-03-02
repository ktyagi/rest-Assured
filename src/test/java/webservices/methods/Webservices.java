package webservices.methods;

import io.restassured.path.json.JsonPath;
import org.json.JSONException;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Webservices {
	public static String authToken;
	
	public static Response Post(String uRI,String stringJSON){
		RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.post(uRI);
//		response.path();
//		JsonPath jsonPath = new JsonPath(response.asString());
//		jsonPath.get();
		return response;
	}
	
	public static Response Get(String uRI){
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.get(uRI);
		return response;
	}
	
	public static Response Put(String uRI,String stringJSON){
		RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.put(uRI);
		return response;
	}
	
	public static Response Delete(String uRI){
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.delete(uRI);
		return response;
	}

	public static Response PostCallWithHeader(String uRI,String stringJSON){
		RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.headers("Authorization", authToken);
		Response response = requestSpecification.post(uRI);
		return response;
	}
	
	public static Response GetCallwithheader(String uRI){
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.headers("Authorization", authToken);
		requestSpecification.headers("companyid", 101);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.get(uRI);
		return response;
	}
	
	public static Response PutCallWithHeader(String uRI,String stringJSON){
		RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
		requestSpecification.headers("Authorization", authToken);
		requestSpecification.headers("companyid", 101);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.put(uRI);
		return response;
	}
	
	public static Response DeleteCallWitHeader(String uRI){
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.headers("Authorization", authToken);
		requestSpecification.headers("companyid", 101);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.delete(uRI);
		return response;
	}
	
	public static void loginToApplication(String uRI, String userName, String password) throws JSONException{
		RequestSpecification requestSpecification = RestAssured.given().auth().form(userName, password);
		Response response = requestSpecification.get(uRI);
		org.json.JSONObject jsonObject = new org.json.JSONObject(response);
		String auth = jsonObject.getString("authToken");
		authToken = auth;
	}
}
