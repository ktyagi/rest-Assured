package testscripts;

import java.util.ArrayList;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pojoclass.AddCountry;
import pojoclass.GetCountries;
import utills.EndpointURL;
import utills.URL;
import utills.WebservicesUtil;
import webservices.methods.Webservices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

public class TC_001 {
	Response response;
	ArrayList<String> country;
	ArrayList<Integer> population;
	ArrayList<Integer> countryid;
	
	@BeforeClass
	public void setUp(){
		country = new ArrayList<String>();
		population = new ArrayList<Integer>();
		countryid = new ArrayList<Integer>();
		
	}
	
	@Test(dependsOnMethods="verifyAddCountry")
	public void verifyGetCountries(){
		Gson gson = new GsonBuilder().create();
	    GetCountries[] getCountries;
		String url = URL.fixURL+EndpointURL.GET_COUNTRIES.getResourcePath();
		//System.out.println(url);
		response = Webservices.Get(url);
		System.out.println(response.asString());
		if(response.getStatusCode()==200){
			getCountries = gson.fromJson(response.getBody().asString(), GetCountries[].class);
			for(int i =1; i<=getCountries.length;i++){
				//Assert.assertEquals(new Integer(i), getCountries[i-1].getId());
				country.add(getCountries[i-1].getCountryName());
				population.add(getCountries[i-1].getPopulation());
				countryid.add(getCountries[i-1].getId());
			}
			
		}
		
	}

	@Test(dataProvider="getCountryByID")
	public void verifyGetCountryById(Integer id, Integer popu, String country){
		Gson gson = new GsonBuilder().create();
		GetCountries getCountries;
		String url = URL.fixURL+EndpointURL.GET_COUNTRY_BY_ID.getResourcePath(id.toString());
		System.out.println(url);
		response = Webservices.Get(url);
		System.out.println(response.getBody().asString());
		if(response.getStatusCode() == 200){
			getCountries = gson.fromJson(response.getBody().asString(), GetCountries.class);
			Assert.assertEquals(popu, getCountries.getPopulation());
			Assert.assertEquals(country, getCountries.getCountryName());
		}
	}
	
	/**
	 * This data provider is used to validate country details
	 * @return
	 */
	@DataProvider(name="getCountryByID")
	public Object[][] getCountryByID(){
		Object[][] result = new Object[countryid.size()][3];
		for(int i = 0; i<result.length; i++){
			result[i][0] = countryid.get(i);
			result[i][1] = population.get(i);
			result[i][2] = country.get(i);
		}
		return result;
	}
	
	@DataProvider(name="addCountry")
	public Object[][] addCountry(){
		Object[][] result = new Object[2][3];
		
		result[0][0] = "{\"id\":5,\"countryName\":\"UK\",\"population\":10000}";
		result[0][1]="UK";
		result[0][2] = 10000;
		
		result[1][0] = "{\"id\":6,\"countryName\":\"Japan\",\"population\":69000}";
		result[1][1] = "Japan";
		result[1][2] = 69000;
		return result;
	}
	
	@Test(dataProvider="addCountry")
	public void verifyAddCountry(String json, String countryName, int population){
		Gson gson = new GsonBuilder().create();
		AddCountry addCountry;
		String url = URL.fixURL+EndpointURL.ADD_COUNTRY.getResourcePath();
		response = Webservices.Post(url, json);
		if(response.getStatusCode() == 200){
			addCountry = gson.fromJson(response.asString(), AddCountry.class);
			Assert.assertEquals(countryName, addCountry.getCountryName());
			Assert.assertEquals(new Integer(population), addCountry.getPopulation());
			
			//System.out.println(response.asString());
		}
	}
	
	@Test
	public void verifyUpdateCountry(){
		
	}
	
	@Test
	public void verifyDeleteCountry(){
		
	}

}
