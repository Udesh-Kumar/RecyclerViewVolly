package com.example.cc.recyclervollypicasso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.cc.recyclervollypicasso.MainActivity.EXTRA_CREATER;
import static com.example.cc.recyclervollypicasso.MainActivity.EXTRA_LIKES;
import static com.example.cc.recyclervollypicasso.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra(EXTRA_URL);
        int Likes=intent.getIntExtra(EXTRA_LIKES,0);
        String CreaterName=intent.getStringExtra(EXTRA_CREATER);

        ImageView imageView=findViewById(R.id.imageView1);
        TextView createrName=findViewById(R.id.text_view_cretorname_detail);
        TextView likecount=findViewById(R.id.text_view_like_count);

        Picasso.get().load(imageUrl).into(imageView);
        createrName.setText(CreaterName);
        likecount.setText("Likes :"+Likes);



    }
}
