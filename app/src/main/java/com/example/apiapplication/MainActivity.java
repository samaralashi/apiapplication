package com.example.apiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView postTitleTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Post post = new Post(5, "codingWithNerds","hi, this is my first post");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("title", "SamarAlashi");
        map.put("body", "hi, this is my second post");
        map.put("userId", 6);

        postTitleTV = findViewById(R.id.post_title);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//        Call<List<Post>> call = apiInterface.getPost("1");
//
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                postTitleTV.setText(response.body().get(0).getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                postTitleTV.setText(t.getMessage());
//            }
//        });

        Call<Post> call = apiInterface.storePost(map);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                postTitleTV.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                postTitleTV.setText(t.getMessage());
            }
        });
    }
}