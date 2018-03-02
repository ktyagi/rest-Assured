package pojoclass;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AddCountry {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("countryName")
@Expose
private String countryName;
@SerializedName("population")
@Expose
private Integer population;

/**
* 
* @return
* The id
*/
public Integer getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(Integer id) {
this.id = id;
}

/**
* 
* @return
* The countryName
*/
public String getCountryName() {
return countryName;
}

/**
* 
* @param countryName
* The countryName
*/
public void setCountryName(String countryName) {
this.countryName = countryName;
}

/**
* 
* @return
* The population
*/
public Integer getPopulation() {
return population;
}

/**
* 
* @param population
* The population
*/
public void setPopulation(Integer population) {
this.population = population;
}

}