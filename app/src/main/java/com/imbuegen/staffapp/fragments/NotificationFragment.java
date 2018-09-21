package com.imbuegen.staffapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imbuegen.staffapp.R;

public class NotificationFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notification,container,false);
    }





    public class notiAdapter extends RecyclerView.Adapter<notiAdapter.myViewHolder>{

        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class myViewHolder extends RecyclerView.ViewHolder{

            public myViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
