package com.client.playersfront;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.client.playersfront.model.Model;
import com.client.playersfront.retrofit.ApiPlayers;
import com.client.playersfront.retrofit.RetrofitSer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersDet extends AppCompatActivity {

    private Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_det);

        Button update = findViewById(R.id.btn_update);
        Button delete = findViewById(R.id.btn_delete);

        TextView name = findViewById(R.id.tv_Name);
        TextView surname = findViewById(R.id.tv_surname);
        TextView branch = findViewById(R.id.tv_branch);
        TextView nationality = findViewById(R.id.tv_nationality);
        TextView age = findViewById(R.id.tv_age);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");

        RetrofitSer retrofitSer = new RetrofitSer();
        ApiPlayers apiPlayers = retrofitSer.getRetrofit().create(ApiPlayers.class);

        apiPlayers.findById((long) id).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {

                model = (Model) response.body();
                assert model != null;
                Toast.makeText(PlayersDet.this, model.getFirstName(), Toast.LENGTH_SHORT).show();
                name.setText(model.getFirstName());
                surname.setText(model.getSecondName());
                branch.setText(model.getBranch());
                nationality.setText(model.getNationality());
                age.setText(model.getAge());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlayersDet.this, AddPlayer.class);
                i.putExtra("id", model.getId());
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                apiPlayers.delete(model.getId()).enqueue(new Callback() {

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        Toast.makeText(PlayersDet.this, "Delete successful!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(PlayersDet.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull Throwable t) {

                    }
                });
            }
        });
    }
}