package com.mechatronase.maker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Edwin on 18/01/2015.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private String currentUserId;
    private ArrayList<String> names;
    List<NatureItem> mItems;
    Context mContext;


    public CardAdapter(Context context) {
        super();
        this.mContext = context;
        names = new ArrayList<String>();
        mItems = new ArrayList<NatureItem>();
        currentUserId = ParseUser.getCurrentUser().getObjectId();
        ParseQuery<ParseUser> query = ParseUser.getQuery();

        //query.whereEqualTo("interest", interest);  ---> commenting this part because need to design checkbock in each catagory
        query.whereNotEqualTo("objectId", currentUserId);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> userList, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < userList.size(); i++) {
                        names.add(userList.get(i).getUsername());
                        Log.d("myyname", names.get(i));
                        NatureItem nature = new NatureItem();
                        nature.setName(names.get(i));
                        nature.setDes("I am a maker who loves arduino, IOIO and now looking to go into IOT, anyone share same interest?");
                        nature.setThumbnail(R.drawable.great_barrier_reef);
                        mItems.add(nature);

                    }

                }
            }
        });

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NatureItem nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvDesNature.setText(nature.getDes());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());

    }

    //display clickable a list of all users
    private ArrayList<String> setConversationsList() {
        currentUserId = ParseUser.getCurrentUser().getObjectId();

        names = new ArrayList<String>();

        ParseQuery<ParseUser> query = ParseUser.getQuery();

        //query.whereEqualTo("interest", interest);  ---> commenting this part because need to design checkbock in each catagory
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> userList, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < userList.size(); i++) {
                        names.add(userList.get(i).getUsername().toString());
                        Log.d("myname", names.get(i).toString());

                    }

                }
            }
        });
        return names;
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView)itemView.findViewById(R.id.tv_nature);
            tvDesNature = (TextView)itemView.findViewById(R.id.tv_des_nature);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereEqualTo("username", tvNature.getText().toString());
            query.findInBackground(new FindCallback<ParseUser>() {
                public void done(List<ParseUser> user, ParseException e) {
                    if (e == null) {
                        /*Intent intent = new Intent(mContext.getApplicationContext(), MessagingActivity.class);
                        intent.putExtra("RECIPIENT_ID", user.get(0).getObjectId());
                        mContext.startActivity(intent);*/
                       Toast.makeText(mContext,
                                user.get(0).getObjectId(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext,
                                "Error finding that user",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


}


