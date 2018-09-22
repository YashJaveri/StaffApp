package com.imbuegen.staffapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.imbuegen.staffapp.Interfaces.fragmentCallback;
import com.imbuegen.staffapp.JavaObjects.Data;
import com.imbuegen.staffapp.R;
import com.imbuegen.staffapp.RestClass;

import org.json.JSONArray;
import org.json.JSONException;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    RecyclerView recyclerView;
    JSONArray posts;
    RecyclerView.Adapter myAdapter;
    fragmentCallback callback;
    boolean showRelated;
    Data dataController;
    int page=1;


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

        this.callback=(fragmentCallback) getActivity();

        posts=new JSONArray();

        //initializeDummy();

        final LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        DividerItemDecoration  dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        dataController = new Data(getContext());
        myAdapter = new homeAdapter();
        recyclerView.setAdapter(myAdapter);
        dataController.init();
        dataController.getPosts(page++, new RestClass.RestListner<JSONArray>() {
            @Override
            public void onComplete(JSONArray jsonString) {
                Log.d("ONCP",jsonString.toString());
                posts  = jsonString;
                Log.d("HAJAM",posts.toString());
                myAdapter.notifyDataSetChanged();
            }
        });




        //todo
        //on scroll listener

       /* recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, int dx, int dy) {
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
                        dataController.requestPosts(page++, new RestClass.RestListner<ArrayList<PostObject>>() {
                            @Override
                            public void onComplete(ArrayList<PostObject> jsonString) {
                                posts.addAll(jsonString);
                                myAdapter.notifyDataSetChanged();
                            }
                        });


                        if(posts.size()>oldListsize) {
                            oldListsize = posts.size();
                        }else{
                            isEnd=true;
                        }
                        Log.e(TAG, "onScrolled: request data");
                    }
                }
            }
        });*/



        //If showRelated is true we only show posts and events of the user


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
            try {
                Log.d("post",posts.getJSONObject(i).getJSONObject("user").getString("name"));
                holder.postTitle.setText(posts.getJSONObject(i).getJSONObject("user").getString("name"));
                holder.postContent.setText(posts.getJSONObject(i).getString("content"));
                holder.thumbsUpCount.setText(posts.getJSONObject(i).getJSONArray("likes").length());
                holder.thumbsDownCount.setText(posts.getJSONObject(i).getJSONArray("dislikes").length());
            }catch (JSONException ex){
                ex.printStackTrace();
            }
           //if currentobject = the loged in user we show the edit button

        }

        @Override
        public int getItemCount() {
            return posts.length();
        }

        public class myViewHolder extends RecyclerView.ViewHolder{

            TextView postTitle,postContent,thumbsUpCount,thumbsDownCount;
            Button  thumbsUp,thumbsDown,comment,edit;


            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                postTitle = (TextView) itemView.findViewById(R.id.postTitle);
                postContent = (TextView) itemView.findViewById(R.id.postContent);
                thumbsUpCount = (TextView) itemView.findViewById(R.id.txt_no_thumbsUp);
                thumbsDownCount = (TextView) itemView.findViewById(R.id.txt_no_thumbsDown);

                thumbsUp = (Button) itemView.findViewById(R.id.post_up_button);
                thumbsDown = (Button) itemView.findViewById(R.id.post_down_button);
                comment = (Button) itemView.findViewById(R.id.btn_comment);
                edit = (Button) itemView.findViewById(R.id.btn_edit_post);

                //setting on Click listeners
                clickListener listener = new clickListener();
                thumbsUp.setOnClickListener(listener);
                thumbsDown.setOnClickListener(listener);
                comment.setOnClickListener(listener);
                edit.setOnClickListener(listener);
                postTitle.setOnClickListener(listener);

            }

                 class clickListener implements View.OnClickListener{
                boolean  hasLiked=false,hasDisliked=false;
                     Data data = new Data(getContext());

                     @Override
                    public void onClick(View view) {

                        switch (view.getId()){

                            case R.id.post_up_button:
                                try {
                                    data.like(posts.getJSONObject(getAdapterPosition()).getString("_id"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.post_down_button:
                                try {
                                    data.dislike(posts.getJSONObject(getAdapterPosition()).getString("_id"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.btn_comment:
                                //todo
                                //start comment fragment here
                                try {
                                    callback.showComments(posts.getJSONObject(getAdapterPosition()).getJSONArray("comments"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                break;

                            case R.id.btn_edit_post:

                                break;

                            case R.id.postTitle:
                                try {
                                    callback.showUser(posts.getJSONObject(getAdapterPosition()).getJSONObject("user"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;

                        }
                    }
                }


        }
    }




}
