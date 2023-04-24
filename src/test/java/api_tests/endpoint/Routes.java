package api_tests.endpoint;

public class Routes {
// this class will only have end point
    public static String base_url = "https://petstore.swagger.io/v2";

    //user  model urls
    public static String get_user = base_url + "/user/{username}";
    public static String create_user = base_url + "/user";
    public static String delete_user = base_url + "/user/deleteUser";
    public static String update_user = base_url + "/user/updateUser";


}
