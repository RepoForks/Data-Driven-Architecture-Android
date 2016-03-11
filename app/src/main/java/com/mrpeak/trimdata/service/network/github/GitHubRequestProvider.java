package com.mrpeak.trimdata.service.network.github;

import com.mrpeak.trimdata.service.network.retrofitModel.RfRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gaofeng on 16/3/4.
 */
public interface GitHubRequestProvider {
    @GET("users/{username}/repos?type=owner")
    Call<List<RfRepository>> userReposList(@Path("username") String username);
}
