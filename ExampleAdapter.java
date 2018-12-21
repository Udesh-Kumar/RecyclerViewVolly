package com.example.cc.recyclervollypicasso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> { // When we create adapter we set super class Recyclerview.Adapter


    // Firstly we create viewholderclass
    private Context mContext;  //oncreate view holder me use hoga
    private ArrayList<ExampleItems> mExampleList;
    private OnItemClickListioner mListioner;

    public interface OnItemClickListioner{
        void onitemClick(int position);
    }

    public void setOnItemClickListioner(OnItemClickListioner listioner){
        mListioner=listioner;

    }

    public ExampleAdapter(Context context, ArrayList<ExampleItems> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_list, viewGroup, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) { //Here we pass the position
        ExampleItems currentitems = mExampleList.get(i); //  Our Exampleitems in our mExamplelist objext is position i is saved in currentitems.With the help of currentitems we getimageurl,getmLikes,getmCreator method

        String imageurl = currentitems.getmImageUrl();
        String creatorName = currentitems.getmCreator();
        int likeCount = currentitems.getmLikes();  // Then we want to set our cards in rthose values

        exampleViewHolder.mTextViewCreator.setText("Name :"+creatorName);
        exampleViewHolder.mTextViewLikes.setText("Likes :"+likeCount);

        Picasso.get().load(imageurl).fit().centerInside().into(exampleViewHolder.mImageView);




    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.textview_Likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListioner!=null){

                        int position =getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){

                            mListioner.onitemClick(position); //These method we created in the interface
                        }
                    }
                }
            });
        }
    }


}
