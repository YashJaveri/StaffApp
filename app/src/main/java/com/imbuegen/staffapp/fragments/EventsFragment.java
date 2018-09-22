package com.imbuegen.staffapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imbuegen.staffapp.JavaObjects.EventObject;
import com.imbuegen.staffapp.JavaObjects.UserObject;
import com.imbuegen.staffapp.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    JSONArray eventsList;
    boolean showRelated;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        showRelated=this.getArguments().getBoolean("showRelated");
        return inflater.inflate(R.layout.fragment_events,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventsList=new JSONArray();
        recyclerView =(RecyclerView)view.findViewById(R.id.events_recyclerView);

        //initialize();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        myAdapter =new eventAdapter();
        recyclerView.setAdapter(myAdapter);

        //If showRelated is true we only show posts and events of the user

    }


    public void initialize(){
        UserObject user = new UserObject();
        user.setName("nishant");
        EventObject event = new EventObject();
        event.setEventDate("10/10/2010");
        event.setPostDate("1/1/2010");
        event.setUser(user);
        event.setMessage("come to my party bitch");

    }


    public class eventAdapter extends RecyclerView.Adapter<eventAdapter.myViewholder>{

        @NonNull
        @Override
        public myViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view= LayoutInflater.from(getActivity()).inflate(R.layout.list_item_event,viewGroup,false);

            return new myViewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewholder holder, int position) {
            try {
                holder.eventMessage.setText(eventsList.getJSONObject(position).getString("message"));
                holder.eventDate.setText(eventsList.getJSONObject(position).getString("eventDate"));
                holder.name.setText(eventsList.getJSONObject(position).getJSONObject("user").getString("name"));
            }catch (JSONException ex){
                ex.printStackTrace();
            }
            //todo
            //set the image of the user here with glide

            holder.userPic.setImageResource(R.drawable.ic_person_white_24dp);

        }

        @Override
        public int getItemCount() {
            return eventsList.length();
        }

        public class myViewholder extends RecyclerView.ViewHolder{

            TextView name,eventMessage,eventDate;
            ImageView userPic;
            public myViewholder(@NonNull View itemView) {
                super(itemView);

                name=(TextView)itemView.findViewById(R.id.txt_user_name);
                eventMessage=(TextView)itemView.findViewById(R.id.txt_event_message);
                eventDate=(TextView)itemView.findViewById(R.id.txt_event_date);

                userPic=(ImageView)itemView.findViewById(R.id.img_user_pic);

            }
        }
    }
}
