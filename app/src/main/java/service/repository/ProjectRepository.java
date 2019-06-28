package service.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {

    private static final String url = "https://jsonplaceholder.typicode.com/posts/";

    public static JsonPlaceHolderService jsonPlaceHolderService = null;

    public static JsonPlaceHolderService getService() {
        if (jsonPlaceHolderService == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return jsonPlaceHolderService;
    }


}
