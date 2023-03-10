package com.lodong.multipleimagepickers;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements GridAdapter.onClickButton {


    ArrayList<Data> list = new ArrayList<>();
    ArrayList<Integer> selected = new ArrayList<>();
    ActionBar ab;
    ArrayList<Integer> getSelected = new ArrayList<>();

    TextView tv;
    LinearLayout lo;
    RecyclerView rcv;
    private Boolean isMulti;
    GridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_photo_gallery);

        Button btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv_text);
        lo = findViewById(R.id.lo);
        Intent getintent = getIntent();

        if(getintent.getIntExtra("background",0)!=0){
            lo.setBackgroundColor(getintent.getIntExtra("background",0));
        }
        if(getintent.getIntExtra("ImageId",0)!=0){
            btn.setBackground(getResources().getDrawable(getintent.getIntExtra("ImageId",0)));
        }
        if(getintent.getIntExtra("btn_textColor",0)!=0){
            btn.setTextColor(getResources().getColor(getintent.getIntExtra("btn_textColor",0)));
        }

        if(getintent.getIntExtra("Height",0)!=0){
            ViewGroup.LayoutParams params = btn.getLayoutParams();
            params.height =getintent.getIntExtra("Height",0);
            btn.setLayoutParams(params);
        }

        if(getintent.getIntExtra("Width",0)!=0){
            ViewGroup.LayoutParams params = btn.getLayoutParams();
            params.width =getintent.getIntExtra("Width",0);
            btn.setLayoutParams(params);
        }

        if(getintent.getIntExtra("font",0)!=0){

            Typeface typeface = ResourcesCompat.getFont(this, getintent.getIntExtra("font",0));
            btn.setTypeface(typeface);
        }


        isMulti = getintent.getBooleanExtra("isMultiPick",true);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList = new ArrayList<>();
                for(int i =0; i<getSelected.size(); i++){
                    arrayList.add(list.get(getSelected.get(i)).getUri());
                }
                Intent intentR = new Intent();
                intentR.putExtra("data",arrayList);
                setResult(RESULT_OK,intentR); //????????? ??????
                finish();//???????????? ??????
            }
        });
        rcv = findViewById(R.id.rcv_imagePick);
        GridLayoutManager gridLayout = new GridLayoutManager(this, 3);
        rcv.setLayoutManager(gridLayout);
        adapter = new GridAdapter(this, list, this,isMulti);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rcv.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        rcv.setAdapter(adapter);

        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media._ID;

        Uri collection;


         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
             collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
         }else {
             collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
         }
//Stores all the images from the gallery in Cursor
        Cursor cursor = getContentResolver().query(
                collection, columns, null,
                null, orderBy);
//Total number of images
        int count = cursor.getCount();

//Create an array to store path to all the images
        String[] arrPath = new String[count];

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            Data data = new Data();
            data.setSelected(false);
            data.setUri(cursor.getString(dataColumnIndex));
            //Store the path of the image
            list.add(data);
//            arrPath[i] = cursor.getString(dataColumnIndex);
//            Log.i("PATH", arrPath[i]);
            if (i == count - 1) {
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
                rcv.scrollToPosition(rcv.getChildCount()-1);
            }
        }
// The cursor should be freed up after use with close()
        cursor.close();


    }

    @Override
    public void onClick(ArrayList<Integer> positionList) {

        getSelected.clear();
        getSelected.addAll(positionList);

        tv.setText(positionList.size() + "??? ?????????");

    }

    @Override
    public void onClickNotMulti(ArrayList<Integer> positionList, Integer pos) {
        getSelected.clear();
        getSelected.addAll(positionList);

        tv.setText(positionList.size() + "??? ?????????");

        ((ImageView)rcv.findViewHolderForAdapterPosition(pos).itemView.findViewById(R.id.chkImage)).setImageResource(R.drawable.circle_custom);
        ((ImageView)rcv.findViewHolderForAdapterPosition(pos).itemView.findViewById(R.id.cover)).setVisibility(View.GONE);
        list.get(pos).setSelected(false);
        positionList.remove(positionList.indexOf(pos));

        adapter.notifyItemChanged(pos);



    }
}