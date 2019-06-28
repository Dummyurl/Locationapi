package com.example.android.mvvmadi;

public interface Github_service {
        String HTTPS_API_GITHUB_URL = "https://api.github.com/";

        @GET("users/{user}/repos")
        Call<List<Project>> getProjectList(@Path("user") String user);

        @GET("/repos/{user}/{reponame}")
        Call<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);
}
