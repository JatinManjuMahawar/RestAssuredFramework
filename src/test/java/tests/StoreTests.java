package tests;

import configuration.Utilities;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payloads.StorePojo;

import java.io.File;
import java.util.Date;

public class StoreTests {
    static StorePojo storePojo;

    @DataProvider(name = "getTestDataForStore")
    public Object[][] getTestDataForStore(){
        File testExcelFile = new File("src/test/java/configuration/APItests.xlsx");
        Object[][] testData = Utilities.getTestData(testExcelFile, "Store");
        return testData;
    }

    @Test(priority = 0, dataProvider = "getTestDataForStore")
    public static void getStoreOrders(int id, int petid, int quantity, String shipdate, String status, boolean complete){
        storePojo = new StorePojo();
        storePojo.setId(id);
        storePojo.setComplete(complete);
        storePojo.setPetId(petid);
        storePojo.setQuantity(quantity);
        storePojo.setShipDate(shipdate.toString());
        storePojo.setStatus(status);
        Response res = StoreEndpoints.getStoreOrders(storePojo);

        res.then().log().all();
        Assert.assertEquals(res.statusCode(), 200);
    }

    @Test(priority = 1)
    public static void getStoreInventory(){
        Response res = StoreEndpoints.getStoreInventory();
        res.then().log().all();
        Assert.assertEquals(res.statusCode(), 200);
    }

    @Test(priority = 2)
    public static void getStoreOrderByID(){
        Response res = StoreEndpoints.getOrdersByID(storePojo.getId());

        res.then().log().all();
        Assert.assertEquals(res.statusCode(), 200);
    }

    @Test(priority = 3)
    public static void deleteStoreOrder(){
        Response res = StoreEndpoints.deleteOrderByID(storePojo.getId());

        res.then().log().all();
        Assert.assertEquals(res.statusCode(), 200);
    }

}
