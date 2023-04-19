package com.lodong.multipleimagepickers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    Context context;
    ArrayList<Data> list;
    ArrayList<Integer> positionList = new ArrayList<>();
    Activity activity;
    onClickButton onClickButton;
    Boolean isMulti;
    boolean isSelected = false;
    int selectedPos;

    public GridAdapter(Context context, ArrayList<Data > list,onClickButton onClickButton,Boolean isMulti) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.onClickButton = onClickButton;
        this.isMulti = isMulti;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_gallery_item, parent, false);


        return new ViewHolder(v,onClickButton);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Glide.with(context)
                .load(list.get(position).getUri())
                .thumbnail(0.1f)
                .into(holder.imgThumb);

        if(list.get(position).getSelected()==false){
            holder.chkImage.setImageResource(R.drawable.circle_custom);
            holder.cover.setVisibility(View.GONE);


        }else{
            holder.chkImage.setImageResource(R.drawable.ic_baseline_check_circle_24);
            holder.cover.setVisibility(View.VISIBLE);

        }



    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void updatePositionList(ArrayList<Integer> positionList) {
        this.positionList = positionList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        ImageView chkImage;
        ImageView cover;
        public ViewHolder(@NonNull View itemView, GridAdapter.onClickButton onClickButton) {
            super(itemView);

            imgThumb = itemView.findViewById(R.id.imgThumb);
            chkImage = itemView.findViewById(R.id.chkImage);
            cover = itemView.findViewById(R.id.cover);

            if(isMulti==true) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (list.get(getAdapterPosition()).getSelected() == false) {

                            ((ImageView) itemView.findViewById(R.id.chkImage)).setImageResource(R.drawable.ic_baseline_check_circle_24);
                            ((ImageView) itemView.findViewById(R.id.cover)).setVisibility(View.VISIBLE);
//                        holder.chkImage.setImageResource(R.drawable.ic_baseline_check_circle_24);
//                        holder.cover.setVisibility(View.VISIBLE);

                            list.get(getAdapterPosition()).setSelected(true);
                            positionList.add(getAdapterPosition());

                            onClickButton.onClick(positionList);


                        } else {
                            ((ImageView) itemView.findViewById(R.id.chkImage)).setImageResource(R.drawable.circle_custom);
                            ((ImageView) itemView.findViewById(R.id.cover)).setVisibility(View.GONE);
                            list.get(getAdapterPosition()).setSelected(false);
                            positionList.remove(positionList.indexOf(getAdapterPosition()));
                            onClickButton.onClick(positionList);

                        }
                    }
                });
            }else{
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(isSelected == false){
                            isSelected=true;
                            selectedPos = getAdapterPosition();
                            ((ImageView) itemView.findViewById(R.id.chkImage)).setImageResource(R.drawable.ic_baseline_check_circle_24);
                            ((ImageView) itemView.findViewById(R.id.cover)).setVisibility(View.VISIBLE);
                            list.get(getAdapterPosition()).setSelected(true);
                            positionList.add(getAdapterPosition());
                            onClickButton.onClick(positionList);

                        }else{
                            ((ImageView) itemView.findViewById(R.id.chkImage)).setImageResource(R.drawable.ic_baseline_check_circle_24);
                            ((ImageView) itemView.findViewById(R.id.cover)).setVisibility(View.VISIBLE);
                            list.get(getAdapterPosition()).setSelected(true);
                            positionList.clear();
                            positionList.add(getAdapterPosition());
                            onClickButton.deletePreItemInRecyclerview();
                            onClickButton.onClick(positionList);

                        }

                    }
                });

            }


        }
    }

    public interface onClickButton{
        void onClick(ArrayList<Integer> positionList);
        void deletePreItemInRecyclerview();

    }
}
