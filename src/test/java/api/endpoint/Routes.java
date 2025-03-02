package api.endpoint;

public class Routes {
    //base URL-https://petstore.swagger.io/v2/swagger.json
    //Create User-

    public static String base_url= "https://petstore.swagger.io/v2";
    //endpoint of user module
    public static String post_url=base_url+"/user";
    public static String get_url= base_url+"/user/{username}";
    public static String update_url=base_url+"/user/{username}";
    public static String delete_url= base_url+"/user/{username}";


}
