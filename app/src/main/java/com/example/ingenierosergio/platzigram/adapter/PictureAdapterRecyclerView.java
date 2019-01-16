package com.example.ingenierosergio.platzigram.adapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ingenierosergio.platzigram.R;
import com.example.ingenierosergio.platzigram.model.Picture;
import com.example.ingenierosergio.platzigram.post.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

         private ArrayList<Picture> pictures;
         private int resource;
         private Activity activity;


    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder pictureViewHolder, int i) {
       Picture picture=pictures.get(i);
       pictureViewHolder.usernameCard.setText(picture.getUserName());
       pictureViewHolder.timeCard.setText(picture.getTime());
       pictureViewHolder.likeNumberCard.setText(picture.getLike_number());
        Picasso.get().load(picture.getPicture()).into(pictureViewHolder.pictureCar);

        pictureViewHolder.pictureCar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, PictureDetailActivity.class);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode=new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);

                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, activity.getString(R.string.transitionname_picture)).toBundle());

                }else {
                    activity.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCar;
        private TextView usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;


        public PictureViewHolder(View itemView){
            super(itemView);
            pictureCar=(ImageView) itemView.findViewById(R.id.pictureCard);
            usernameCard=(TextView) itemView.findViewById(R.id.userNameCard);
            timeCard=(TextView)itemView.findViewById(R.id.timeCard);
            likeNumberCard=(TextView)itemView.findViewById(R.id.likeNumberCard);
        }
    }
}
