package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity{
    private ListView listView;
    private OkHttpClient client;
    private String path, response;
    private Newsmodel news;
    private ApiCall apiCall;
    private List<Newsmodel.Res>data;
    private CustomAdapter myCustomAdapter;
    private ProgressDialog pD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pD = new ProgressDialog(this);
        apiCall = new ApiCall();
        data = new ArrayList<>();
        listView = findViewById(R.id.newslist);

        try {
            path = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";
            new GetDataFromServer().execute();
        }catch (Exception e) {
          e.printStackTrace();
        }

    }
    private class GetDataFromServer extends AsyncTask<Void ,Void,Void>
    {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                pD.setMessage("Loading");
                pD.show();
            }catch (Exception e){}
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(pD.isShowing()) {
                pD.dismiss();
                pD.setCancelable(false);
            }
            myCustomAdapter  = new CustomAdapter(MainActivity.this,R.layout.row_item,data);
            listView.setAdapter(myCustomAdapter);
        }
        @Override
        protected Void doInBackground(Void... voids) {

            try {Gson son = new Gson();
                client = new OkHttpClient();
                response = ApiCall.GET(client,path);
                Type type = new TypeToken<Collection<Newsmodel>>()
                {}.getType();
                news = son.fromJson(response,Newsmodel.class);
                if(data.isEmpty()){
                    for(int j=0;j< news.getData().size();j++){
                        data.add(news.getData().get(j));
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return  null;
        }
    }
}





