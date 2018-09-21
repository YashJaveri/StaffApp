package com.imbuegen.staffapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imbuegen.staffapp.JavaObjects.CommentsObject;
import com.imbuegen.staffapp.JavaObjects.UserObject;
import com.imbuegen.staffapp.R;

import java.util.ArrayList;

public class CommentsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdatper;
    ArrayList<CommentsObject> commentList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        commentList=new ArrayList<>();

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initialize();

        myAdatper=new commentsAdapter();
        recyclerView.setAdapter(myAdatper);

    }

    public void initialize(){
        CommentsObject comment = new CommentsObject(new UserObject(),"this is a comment");
        commentList.add(comment);
        commentList.add(comment);
        commentList.add(comment);
    }


    public class commentsAdapter extends RecyclerView.Adapter<commentsAdapter.myViewHolder>{


        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v =LayoutInflater.from(getActivity()).inflate(R.layout.list_item_comment,viewGroup,false);
            return new myViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

            CommentsObject current=commentList.get(position);

            holder.pic.setImageResource(R.drawable.ic_person_black_24dp);
            holder.content.setText(current.getComment());
        }

        @Override
        public int getItemCount() {
            return commentList.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder{

            ImageView pic;
            TextView content;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                pic=(ImageView)itemView.findViewById(R.id.comment_pic);
                content=(TextView)itemView.findViewById(R.id.comment_content);

            }
        }
    }
}
