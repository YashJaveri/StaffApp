package com.imbuegen.staffapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    boolean showRelated;


    boolean isLoading=false;         //specifies whether request is being processed
    boolean isEnd =false;            //specifies end of users feed

    int oldListsize;
    int currentItems,totalItems,scrollOutItems=0;
       /*totalItems are the total items in the adapter's arraylist
         currentItems  are the item visible on the screen
         scrolloutItems are the items tha are scrolled out of the screen*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        showRelated=this.getArguments().getBoolean("showRelated");
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView) view.findViewById(R.id.home_recyclerView);

        posts=new ArrayList<>();

        initializeDummy();

        final LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new homeAdapter();
        recyclerView.setAdapter(myAdapter);

        //todo
        //on scroll listener

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                Log.e(TAG, "onScrolled: ci "+currentItems+",ti "+totalItems+",soi "+scrollOutItems);

                if(dy>0 && (totalItems==currentItems+scrollOutItems)) {

                    oldListsize=posts.size();
                    //dy>0 means that the user is going down

                    // (currentItems+scrollOutItems)  gives the total no of items seen by the user
                    //(totalItems==currentItems+scrollOutItems) means that the user has already seen all the items in the adapter's arraylist


                    if(!isLoading&&(!isEnd)){
                        //request new data here
                        //with incremented page number



                        if(posts.size()>oldListsize) {
                            oldListsize = posts.size();
                        }else{
                            isEnd=true;
                        }
                        Log.e(TAG, "onScrolled: request data");
                    }
                }
            }
        });



        //If showRelated is true we only show posts and events of the user


    }



public void initializeDummy(){


    UserObject user = new UserObject();
    user.setName("user one");
    user.setEmployeeID(1);

    PostObject post = new PostObject();

    post.set_id("1");
    post.setContent("dummy post content");
    post.setUser(user);

    ArrayList<LikesObject> likeList = new ArrayList<>();
    likeList.add(new LikesObject(user.getEmployeeID()));

    ArrayList<DisLikesObject> disLikeList = new ArrayList<>();
    disLikeList.add(new DisLikesObject(user.getEmployeeID()));

    ArrayList<CommentsObject> commentList= new ArrayList<>();
    commentList.add(new CommentsObject(user,"dummey comment"));

    post.setlikeObjs(likeList);
    post.setDislikeObjs(disLikeList);
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
           holder.thumbsUpCount.setText(Integer.toString(currentobject.getlikeObjss().size()));
           holder.thumbsDownCount.setText(Integer.toString(currentobject.getDislikeObjs().size()));

        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder{

            TextView postTitle,postContent,thumbsUpCount,thumbsDownCount;
            Button attachment, thumbsUp,thumbsDown,comment;


            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                postTitle=(TextView)itemView.findViewById(R.id.postTitle);
                postContent=(TextView)itemView.findViewById(R.id.postContent);
                thumbsUpCount=(TextView)itemView.findViewById(R.id.txt_no_thumbsUp);
                thumbsDownCount=(TextView)itemView.findViewById(R.id.txt_no_thumbsDown);

                attachment=(Button)itemView.findViewById(R.id.post_attachment);
                thumbsUp=(Button)itemView.findViewById(R.id.post_up_button);
                thumbsDown=(Button)itemView.findViewById(R.id.post_down_button);
                comment=(Button)itemView.findViewById(R.id.btn_comment);

            }
        }
    }
}
