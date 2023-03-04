package com.lodong.lib;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lodong.multipleimagepickers.MainActivity;


public class CustomImagePicker {


    Context context;
    Activity activity;
    Integer backGroundImageId, ButtonHeight, ButtonWidth, REQUEST_PICK_MULTI_IMAGES,btn_textColor;


    private CustomImagePicker(Builder builder) {
        this.context = builder.context;
        this.activity = builder.activity;
        this.backGroundImageId = builder.backGroundImageId;
        this.ButtonHeight = builder.ButtonHeight;
        this.ButtonWidth = builder.ButtonWidth;
        this.REQUEST_PICK_MULTI_IMAGES = builder.REQUEST_PICK_MULTI_IMAGES;
        this.btn_textColor = builder.btn_textColor;


    }

    public void Excute(){

        Intent intent = new Intent(activity, MainActivity.class);

        if (ButtonHeight == null) {
            intent.putExtra("Height", 50);
        } else {
            intent.putExtra("Height", ButtonHeight);
        }


        if(ButtonWidth == null){
            intent.putExtra("Width",100);
        }else{
            intent.putExtra("Width", ButtonWidth);
        }

        if(backGroundImageId != null){
            intent.putExtra("ImageId", backGroundImageId);
        }

        if(btn_textColor != null){
            intent.putExtra("btn_textColor",btn_textColor);
        }


        activity.startActivityForResult(intent, REQUEST_PICK_MULTI_IMAGES);

    }


    public static class Builder {

        // required parameters
        Context context;
        Activity activity;


        // optional parameters
        Integer backGroundImageId, ButtonHeight, ButtonWidth, REQUEST_PICK_MULTI_IMAGES,btn_textColor;

        public Builder(Context context, Activity activity, Integer intentId) {
            this.context = context;
            this.activity = activity;
            this.REQUEST_PICK_MULTI_IMAGES = intentId;
        }


        public Builder setBackGroundImageId(Integer backGroundImageId) {
            this.backGroundImageId = backGroundImageId;
            return this;
        }

        public Builder setButtonHeight(Integer buttonHeight) {
            ButtonHeight = buttonHeight;
            return this;
        }

        public Builder setButtonWidth(Integer buttonWidth) {
            ButtonWidth = buttonWidth;
            return this;
        }

        public Builder setButtonTextColor(Integer textColor){
            btn_textColor = textColor;
            return this;
        }
        public CustomImagePicker build() {
            return new CustomImagePicker(this);
        }

    }






}