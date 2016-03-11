package com.mrpeak.trimdata.service.network.github;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrpeak.trimdata.service.network.PPHttpRequest;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaofeng on 16/3/4.
 */
public class GitHubRequest extends PPHttpRequest {
    public static String GitHubAPI = "https://api.github.com/";
    private GitHubRequestProvider githubServiceProvider;

    public GitHubRequest() {
        githubServiceProvider = GitHubRequest.getRetrofit().create(GitHubRequestProvider.class);
    }

    public GitHubRequestProvider getGitHubServiceProvider() {
        return githubServiceProvider;
    }

    public static String getBaseUrl() {
        return GitHubAPI;
    }

    public static Retrofit getRetrofit() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkClient()).build();

        return retrofit;
    }



    public static OkHttpClient getOkClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(HTTP_REQUEST_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(HTTP_REQUEST_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(HTTP_REQUEST_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();

        return client;
    }
}
