package com.example.cc.recyclervollypicasso;

public class ExampleItems {
    String mImageUrl;
    String mCreator;
     int mLikes;

     ExampleItems(String ImageUrl, String Creator, int Likes) {
        this.mImageUrl = ImageUrl;
        this.mCreator = Creator;
        this.mLikes = Likes;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }
}
