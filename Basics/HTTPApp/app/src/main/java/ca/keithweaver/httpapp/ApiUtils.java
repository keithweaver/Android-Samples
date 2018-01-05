package ca.keithweaver.httpapp;

/**
 * Created by weaver on 2018-01-05.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static APIClient getAPIClient() {
        return RetrofitClient.getClient(BASE_URL).create(APIClient.class);
    }
}
