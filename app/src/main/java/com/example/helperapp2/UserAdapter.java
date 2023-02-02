package com.example.helperapp2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context context;
    private List<HelperModel>helperModelList;

    public UserAdapter(Context context) {
        this.context = context;
        helperModelList = new ArrayList<>();
    }
    public void add(HelperModel helperModel){
        helperModelList.add(helperModel);
        notifyDataSetChanged();
    }
    public void clear(){
        helperModelList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HelperModel helperModel = helperModelList.get(position);
        holder.username.setText(helperModel.getUserName());
        holder.email.setText(helperModel.getEmail());
        holder.profession.setText(helperModel.getProfession());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,chatActivity.class);

                intent.putExtra("userName",helperModel.getUserName()); /* check this line */
                intent.putExtra("mobile",helperModel.getMobile());
                intent.putExtra("uid", helperModel.getUid());


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return helperModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView username,email,profession;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.userEmail);
            profession = itemView.findViewById(R.id.profession);
        }
    }









//    private Context context;
//    private List<UserModel> userModelList;
//
//    public UserAdapter(userInterface userInterface, ArrayList<UserModel> usermodelList) {
//
//    }
//
//    public void add(UserModel userModel){
//        userModelList.add(userModel);
//        notifyDataSetChanged();
//    }
//    public void clear(){
//        userModelList.clear();
//        notifyDataSetChanged();
//
//    }
//    @NonNull
//    @Override
//    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
//        UserModel userModel = userModelList.get(position);
//        holder.username.setText(userModel.getUserName());
//        holder.email.setText(userModel.getEmail());
//        holder.profession.setText(userModel.getProfession());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,ChatActivity.class);
//                intent.putExtra("id",userModel.getUserid());
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return userModelList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        private TextView username,email,profession;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            username = itemView.findViewById(R.id.username);
//            email = itemView.findViewById(R.id.userEmail);
//            profession = itemView.findViewById(R.id.profession);
//        }
//    }
}
