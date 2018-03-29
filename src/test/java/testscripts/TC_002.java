package testscripts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import webservices.methods.Webservices;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class TC_002 {
    public static final String YOUR_PROXY_HOST_HERE = "access1028.cws.sco.cisco.com";
    public static final String YOUR_PROXY_PORT_HERE="8080";
    Response response;

    @BeforeClass
    public void setUp(){

        System.setProperty("http.proxyHost", YOUR_PROXY_HOST_HERE);
        System.setProperty("http.proxyPort", YOUR_PROXY_PORT_HERE);

    }
@Test
public void verifyGetCountries(){

    //System.out.println(url);
    response = Webservices.Get("http://service-dev-int.sdlc.nzrb.co.nz/openbet-ssviewer/Drilldown/2.30/Event/");
    System.out.println(response.asString());


}

    @Test
    public void test01()  {
        Response res  =given ()
                .when()
                .get (" http://service-dev-int.sdlc.nzrb.co.nz/openbet-ssviewer/Drilldown/2.30/EventToOutcomeForClass/30?existsFilter=event:simpleFilter:market.isActive&prune=market&prune=event")
         .then()
                .contentType(ContentType.XML)
                .extract().response();


        System.out.println (res.asString ());

    }
    @Test
    public void getStatusCode(){
        Response resp = RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        int code = resp.getStatusCode();
        System.out.println(code);


    }

}
