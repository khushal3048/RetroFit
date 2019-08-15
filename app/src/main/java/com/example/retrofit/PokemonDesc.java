package com.example.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class PokemonDesc extends AppCompatActivity {

    ImageView img_poke;
    TextView txt_name,txt_type,txt_ability,txt_h,txt_w,txt_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_desc);

        img_poke = findViewById(R.id.desc_img);
        txt_name = findViewById(R.id.txt_name);
        txt_type = findViewById(R.id.txt_type);
        txt_ability = findViewById(R.id.txt_ability);
        txt_h = findViewById(R.id.txt_height);
        txt_w = findViewById(R.id.txt_weight);
        txt_desc = findViewById(R.id.txt_desc);

        Intent i = getIntent();
        Pokemon p = i.getParcelableExtra("data");

        //Picasso.get().load(p.getImage()).into(img_poke);
        Glide.with(getApplicationContext()).asBitmap().load(p.getImage()).into(img_poke);
        txt_name.setText(p.getName());
        txt_type.setText(p.getType());
        txt_ability.setText(p.getAbility());
        txt_h.setText(p.getHeight());
        txt_w.setText(p.getWeight());
        txt_desc.setText(p.getDescription());


        txt_desc.setMovementMethod(new ScrollingMovementMethod());
    }
}
