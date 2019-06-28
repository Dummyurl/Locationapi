package service.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import service.model.Posts;

public interface JsonPlaceHolderService {

    @GET
    Call<List<Posts>> getPostList();

    @GET("{id}")
    Call<Posts> getPost(@Path("id") String id);

}
