package endpoints;

public class Routes {
    public static String baseURL = "https://petstore.swagger.io/v2";

    //User Routes
    public static String createUser = baseURL+"/user";
    public static String getUserGET = baseURL+"/user/{username}";
    public static String updateUserPUT = baseURL+"/user/{username}";
    public static String deleteUserDELETE = baseURL+"/user/{username}";


    //Store Routes

    public static String getStoreInventory = baseURL + "/store/inventory";
    public static String getStoreOrders = baseURL + "/store/order";
    public static String getOrderByID = baseURL + "/store/order/{orderid}";
    public static String deleteOrderByID = baseURL + "/store/order/{orderid}";

    //Pet Routes

    public static String getPetByID = baseURL+"/pet/{petId}";
    public static String createPetByID = baseURL+"/pet";



}
