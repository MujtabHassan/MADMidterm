package com.example.midterm;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class ApiCall {

    public static String GET(OkHttpClient client, String url) throws IOException
    {
        Request req = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(req).execute();
        return  response.body().string();

    }
}

