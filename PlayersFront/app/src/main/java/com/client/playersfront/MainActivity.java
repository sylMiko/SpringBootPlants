package com.client.playersfront;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.client.playersfront.model.Model;
import com.client.playersfront.retrofit.ApiPlayers;
import com.client.playersfront.retrofit.RetrofitSer;
import com.client.playersfront.view.PlayersView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = findViewById(R.id.main_buttonAdd);
        RecyclerView rView = findViewById(R.id.main_recyclerView);
        rView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitSer retrofit = new RetrofitSer();
        ApiPlayers apiPlayers = retrofit.getRetrofit().create(ApiPlayers.class);

        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddPlayer.class));
            }
        });
        apiPlayers.getAllModels().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                Toast.makeText(MainActivity.this, "RREADNIG DATA SUCCESSFULK", Toast.LENGTH_LONG).show();
                PlayersView playersView = new PlayersView(response.body(), model -> {
                    Intent i = new Intent(MainActivity.this, PlayersDet.class);
                    i.putExtra("id", model.getId());
                    startActivity(i);

                });
                rView.setAdapter(playersView);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "RREADNIG DATA UNSUCCESSFULK", Toast.LENGTH_LONG).show();

            }
        });
    }
}