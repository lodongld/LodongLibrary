package com.lodong.lib;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.lodong.multipleimagepickers.MainActivity;


public class CustomImagePicker {


    Context context;
    Activity activity;
    Integer backGroundImageId, ButtonHeight, ButtonWidth, REQUEST_PICK_MULTI_IMAGES,btn_textColor,layoutBackground;
    Fragment fragment;
    Boolean isMultiPick = true;


    private CustomImagePicker(Builder builder) {
        this.context = builder.context;
        this.activity = builder.activity;
        this.backGroundImageId = builder.backGroundImageId;
        this.ButtonHeight = builder.ButtonHeight;
        this.ButtonWidth = builder.ButtonWidth;
        this.REQUEST_PICK_MULTI_IMAGES = builder.REQUEST_PICK_MULTI_IMAGES;
        this.btn_textColor = builder.btn_textColor;
        this.layoutBackground = builder.layoutBackground;
        this.fragment = builder.fragment;
        this.isMultiPick = builder.isMultiPick;


    }

    public void excute(){

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

        if(layoutBackground != null){
            intent.putExtra("background",layoutBackground);
        }

        if(isMultiPick == true){
            intent.putExtra("isMultiPick",true);
        }else{
            intent.putExtra("isMultiPick",false);
        }

        if(fragment!=null){
            fragment.startActivityForResult(intent,REQUEST_PICK_MULTI_IMAGES);
        }else{
            activity.startActivityForResult(intent, REQUEST_PICK_MULTI_IMAGES);

        }


    }


    public static class Builder {

        // required parameters
        Context context;
        Activity activity;
        Fragment fragment;
        Boolean isMultiPick;

        // optional parameters
        Integer backGroundImageId, ButtonHeight, ButtonWidth, REQUEST_PICK_MULTI_IMAGES,btn_textColor,layoutBackground;

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

        public Builder setLayoutBackgroundColor(Integer backgroundColor){
            layoutBackground = backgroundColor;
            return this;
        }

        public Builder setFragment(Fragment fragment){
            this.fragment = fragment;
            return this;
        }

        public Builder setMultiPick(Boolean isMultiPick){
            this.isMultiPick = isMultiPick;
            return this;
        }
        public CustomImagePicker build() {
            return new CustomImagePicker(this);
        }

    }






}