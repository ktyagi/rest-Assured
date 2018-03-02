package gson;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestngTest {

    //This test method declares that its data should be supplied by the Data Provider
    // "getdata" is the function name which is passing the data
    // Number of columns should match the number of input parameters


    @Test(dataProvider="d")
//    @Test(dataProvider="getData")
    public void setData(String username, String password, String s)
    {
        System.out.println("you have provided username as::"+username);
        System.out.println("you have provided password as::"+password);
    }

    @DataProvider(name="d")
//    @DataProvider
    public Object[][] getData()
    {
        //Rows - Number of times your test has to be repeated.
        //Columns - Number of parameters in test data.
//        Object[][] data = new Object[3][2];
//
//        // 1st row
//        data[0][0] ="sampleuser1";
//        data[0][1] = "abcdef";
//
//        // 2nd row
//        data[1][0] ="testuser2";
//        data[1][1] = "zxcvb";
//
//        // 3rd row
//        data[2][0] ="guestuser3";
//        data[2][1] = "pass123";

        Object[][] data =new Object[][]{{"sampleuser1", "abcdef" ,"hi1"},{"testuser2", "zxcvb","hi2"},{"guestuser3", "pass123","hi3"}};
        return data;
    }
}
