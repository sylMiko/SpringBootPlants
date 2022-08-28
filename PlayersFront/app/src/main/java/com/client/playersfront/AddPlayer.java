package com.client.playersfront;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.client.playersfront.model.Model;
import com.client.playersfront.retrofit.ApiPlayers;
import com.client.playersfront.retrofit.RetrofitSer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        EditText name = findViewById(R.id.et_name);
        EditText surname = findViewById(R.id.et_surname);
        EditText nationality = findViewById(R.id.et_nationality);
        EditText age = findViewById(R.id.et_age);
        EditText branch = findViewById(R.id.et_branch);
        Button save = findViewById(R.id.btn_savePlayer);

        RetrofitSer retrofitSer = new RetrofitSer();
        ApiPlayers apiPlayers = retrofitSer.getRetrofit().create(ApiPlayers.class);


        Intent intent = getIntent();

        if (intent.hasExtra("id")) {
            Bundle extras = getIntent().getExtras();
            int id = extras.getInt("id");

            apiPlayers.findById((long) id).enqueue(new Callback() {

                @Override
                public void onResponse(Call call, Response response) {
                    Model model = new Model();
                    model = (Model) response.body();
                    assert model!=null;
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


            save.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Model model = new Model();
                    model.setId(id);
                    model.setFirstName(name.getText().toString());
                    model.setSecondName(surname.getText().toString());
                    model.setNationality(nationality.getText().toString());
                    model.setAge(age.getText().toString());
                    model.setBranch(branch.getText().toString());
                    apiPlayers.update(model, model.getId()).enqueue(new Callback(){

                        @Override
                        public void onResponse(Call call, Response response) {
                            Toast.makeText(AddPlayer.this, "Update successful!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddPlayer.this, MainActivity.class));
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                }
            });

        } else {
            // Do something else
        save.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Model model = new Model();
                model.setFirstName(name.getText().toString());
                model.setSecondName(surname.getText().toString());
                model.setNationality(nationality.getText().toString());
                model.setAge(age.getText().toString());
                model.setBranch(branch.getText().toString());
                apiPlayers.save(model).enqueue(new Callback<Model>(){

                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Toast.makeText(AddPlayer.this, "Save successful!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddPlayer.this, MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {

                    }
                });

            }
        });


        }
    }
}