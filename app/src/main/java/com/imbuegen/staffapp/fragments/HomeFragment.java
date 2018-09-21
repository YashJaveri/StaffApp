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
import android.widget.Button;
import android.widget.TextView;

import com.imbuegen.staffapp.JavaObjects.CommentsObject;
import com.imbuegen.staffapp.JavaObjects.DisLikesObject;
import com.imbuegen.staffapp.JavaObjects.LikesObject;
import com.imbuegen.staffapp.JavaObjects.PostObject;
import com.imbuegen.staffapp.JavaObjects.UserObject;
import com.imbuegen.staffapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    RecyclerView recyclerView;
    ArrayList<PostObject> posts;
    RecyclerView.Adapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView) view.findViewById(R.id.home_recyclerView);

        posts=new ArrayList<>();

        initializeDummy();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myAdapter = new homeAdapter();
        recyclerView.setAdapter(myAdapter);

        //todo
        //on scroll listener
    }



public void initializeDummy(){


    UserObject user = new UserObject();
    user.setName("user one");
    user.setEmployeeID(1);

    PostObject post = new PostObject("1","dummy post content",user);

    ArrayList<LikesObject> likeList = new ArrayList<>();
    likeList.add(new LikesObject(user.getEmployeeID()));

    ArrayList<DisLikesObject> disLikeList = new ArrayList<>();
    disLikeList.add(new DisLikesObject(user.getEmployeeID(),"suggestion"));

    ArrayList<CommentsObject> commentList= new ArrayList<>();
    commentList.add(new CommentsObject(user,"dummey comment"));

    post.setLikeObj(likeList);
    post.setDisLikeObj(disLikeList);
    post.setComment(commentList);

    posts.add(post);
    posts.add(post);
    posts.add(post);
    posts.add(post);
    posts.add(post);
    posts.add(post);
    posts.add(post);



}


    public class homeAdapter extends RecyclerView.Adapter<homeAdapter.myViewHolder>{

        @NonNull
        @Override
        public homeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_home,viewGroup,false);

            return new myViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull homeAdapter.myViewHolder holder, int i) {
            PostObject currentobject =posts.get(i);

            holder.postTitle.setText(currentobject.getUser().getName());
           holder.postContent.setText(currentobject.getContent());
           holder.thumbsUpCount.setText(Integer.toString(currentobject.getLikeObj().size()));
           holder.thumbsDownCount.setText(Integer.toString(currentobject.getDisLikeObj().size()));

        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder{

            TextView postTitle,postContent,thumbsUpCount,thumbsDownCount;
            Button attachment, thumbsUp,thumbsDown;


            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                postTitle=(TextView)itemView.findViewById(R.id.postTitle);
                postContent=(TextView)itemView.findViewById(R.id.postContent);
                thumbsUpCount=(TextView)itemView.findViewById(R.id.txt_no_thumbsUp);
                thumbsDownCount=(TextView)itemView.findViewById(R.id.txt_no_thumbsDown);

                attachment=(Button)itemView.findViewById(R.id.post_attachment);
                thumbsUp=(Button)itemView.findViewById(R.id.post_up_button);
                thumbsDown=(Button)itemView.findViewById(R.id.post_down_button);

            }
        }
    }
}
