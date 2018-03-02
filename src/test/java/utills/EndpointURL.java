package utills;

public enum EndpointURL {

	ADD_COUNTRY("/countries/details"),
	UPDATE_COUNTRY("/countries/update/details"),
	GET_COUNTRY_BY_ID("/country/"),
	DELETE_COUNTRY("/country/"),
	GET_COUNTRIES("/countries");
	
	String resourcePath;
	
	EndpointURL(String resourcePath){
		this.resourcePath = resourcePath;
		}
	
	public String getResourcePath() {
		return this.resourcePath;
		}
	
	public String getResourcePath(String data) {
		//System.out.println(this.resourcePath);
		return this.resourcePath+data;
		}
	
	public static void main(String[] args) {
		System.out.println(EndpointURL.ADD_COUNTRY.getResourcePath());
		
		System.out.println("making full url for Add_country");
		
		String url = URL.fixURL+EndpointURL.ADD_COUNTRY.getResourcePath();
		System.out.println(url);
	}
}
