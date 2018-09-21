package com.imbuegen.staffapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.imbuegen.staffapp.R;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView) view.findViewById(R.id.home_recyclerView);

    }






    public class homeAdapter extends RecyclerView.Adapter<homeAdapter.myViewHolder>{

        @NonNull
        @Override
        public homeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_home,viewGroup,false);

            return new myViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull homeAdapter.myViewHolder myViewHolder, int i) {

            
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class myViewHolder extends RecyclerView.ViewHolder{

            TextView postTitle,postContent;
            Button attachment, like,thumbsUp,thumbsDown;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                postTitle=(TextView)itemView.findViewById(R.id.postTitle);
                postContent=(TextView)itemView.findViewById(R.id.postContent);

                attachment=(Button)itemView.findViewById(R.id.postContent);
                like=(Button)itemView.findViewById(R.id.postContent);
                thumbsUp=(Button)itemView.findViewById(R.id.post_up_button);
                thumbsDown=(Button)itemView.findViewById(R.id.post_down_button);

            }
        }
    }
}
