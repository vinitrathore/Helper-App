package com.example.helperapp2;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

//public class MessageAdapter extends RecyclerView.Adapter {
//
//    Context context;
//   ArrayList<message1>messageArrayList;
//
//    int ITEM_SEND=2;
//    int ITEM_RECIEVE=1;
//
//    public MessageAdapter(chatActivity chatActivity, ArrayList<message1> messageArrayList) {
//    }
//
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == 1){
////            inflate recieve
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recieve,parent,false);
//            return new RecieveViewHolder(view);
//        }else {
////             inflate send
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent,parent,false);
//            return new SentViewHolder(view);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        message1 message = messageArrayList.get(position);
//        if(holder.getClass() == SentViewHolder.class){
////            do stuff for sent view holder
//            SentViewHolder viewHolder = (SentViewHolder)holder;
//           viewHolder.sentmessage.setText(message.message);
//        }else {
////           do stuff for recieve view holder
//
//            RecieveViewHolder viewHolder = (RecieveViewHolder)holder;
//            viewHolder.recievemessage.setText(message.message);
//
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        message1 currentMessage = messageArrayList.get(position);
//        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(currentMessage.senderuid)){  /* check this line */
//            return  ITEM_SEND;
//        }else {
//            return ITEM_RECIEVE;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return messageArrayList.size();
//    }
//
//    class SentViewHolder extends RecyclerView.ViewHolder{
//        TextView sentmessage;
//       public SentViewHolder(@NonNull View itemView) {
//           super(itemView);
//           sentmessage = itemView.findViewById(R.id.txt_sent_message);
//       }
//   }
//   class RecieveViewHolder extends RecyclerView.ViewHolder{
//       TextView recievemessage;
//       public RecieveViewHolder(@NonNull View itemView) {
//           super(itemView);
//           recievemessage = itemView.findViewById(R.id.txt_recieve_message);
//       }
//   }
//}



public class MessageAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<message1>message1ArrayList;

    int ITEM_SEND = 1;
    int ITEM_RECIEVE = 2;

    public MessageAdapter(Context context, ArrayList<message1> message1ArrayList) {
        this.context = context;
        this.message1ArrayList = message1ArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_SEND)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sent,parent,false);
            return new SentViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.recieve,parent,false);
            return new RecieveViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        message1 message = message1ArrayList.get(position);
        if (holder.getClass()==SentViewHolder.class){
            SentViewHolder viewHolder = (SentViewHolder)holder;
            viewHolder.sentmessage.setText(message.getMessage());
        }else {
            RecieveViewHolder viewHolder = (RecieveViewHolder) holder;
            viewHolder.recievemessage.setText(message.getMessage());
        }
    }

    @Override
    public int getItemViewType(int position) {
        message1 message = message1ArrayList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(message.getSenderuid())){
            return  ITEM_SEND;
        }else {
            return ITEM_RECIEVE;
        }
    }

    @Override
    public int getItemCount() {
        return  message1ArrayList.size();
    }


   class SentViewHolder extends RecyclerView.ViewHolder{

       TextView sentmessage;
       public SentViewHolder(@NonNull View itemView) {
           super(itemView);
           sentmessage = itemView.findViewById(R.id.txt_sent_message);
       }
   }
    class RecieveViewHolder extends RecyclerView.ViewHolder{

        TextView recievemessage;
        public RecieveViewHolder(@NonNull View itemView) {
            super(itemView);
            recievemessage = itemView.findViewById(R.id.txt_recieve_message);
        }
    }

}