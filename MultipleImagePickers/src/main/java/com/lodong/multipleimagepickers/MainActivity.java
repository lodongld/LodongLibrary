package com.lodong.multipleimagepickers;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GridAdapter.onClickButton {


    ArrayList<Data> list = new ArrayList<>();
    ArrayList<Integer> selected = new ArrayList<>();
    ActionBar ab;
    ArrayList<Integer> getSelected = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_photo_gallery);

        ab = getSupportActionBar();
//        ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("확인사이즈", String.valueOf(getSelected.size()));
            }
        });
        RecyclerView rcv = findViewById(R.id.rcv_imagePick);
        GridLayoutManager gridLayout = new GridLayoutManager(this, 3);
        rcv.setLayoutManager(gridLayout);
        GridAdapter adapter = new GridAdapter(this, list, this);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rcv.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        rcv.setAdapter(adapter);

        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media._ID;
//Stores all the images from the gallery in Cursor
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
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
                adapter.notifyDataSetChanged();
            }
        }
// The cursor should be freed up after use with close()
        cursor.close();


    }

    @Override
    public void onClick(ArrayList<Integer> positionList) {
        getSelected.addAll(positionList);
        Log.d("왔어요", "ㅇㅇ");
        ab.setTitle(positionList.size() + "개 선택됨");
        ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));


    }
}