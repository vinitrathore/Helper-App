package com.example.helperapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.helperapp2.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    String name,recieveruid,mobile1;
    Intent intent;

    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    ArrayList<message1> messageArrayList;


    String senderRoom,recieverRoom,senderuid;
    DatabaseReference mDbref;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
         name = intent.getStringExtra("userName");
         recieveruid = intent.getStringExtra("uid");
         mobile1 = intent.getStringExtra("mobile");

         senderuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
         mDbref = FirebaseDatabase.getInstance().getReference("chats");


        messageArrayList = new ArrayList<>();
        messageAdapter = new MessageAdapter(chatActivity.this,messageArrayList);



        senderRoom = recieveruid + senderuid;
         recieverRoom = senderuid + recieveruid;

         getSupportActionBar().setTitle(name);




        recyclerView = binding.chatRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        messageAdapter = new MessageAdapter(chatActivity.this,messageArrayList);
//        recyclerView.setAdapter(messageAdapter);

        recyclerView.setAdapter(messageAdapter);


//adding the data to the recycler view

        mDbref.child(senderRoom).child("messages")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageArrayList.clear();
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            message1 message = snapshot1.getValue(message1.class);
                             messageArrayList.add(message);
                        }
                        messageAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





//        adding the message to the database

       binding.sentBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String message = binding.messagebox.getText().toString().trim();
//               String messageObject = message1(senderuid,message); /* check this line */
               message1 messageObject = new message1(senderuid,message);

              mDbref.child(senderRoom).child("messages").push().setValue(messageObject).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
                      mDbref.child(recieverRoom).child("messages").push().setValue(messageObject);
                  }
              });
             binding.messagebox.setText("");


           }
       });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuforchat,menu);
//        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.call){
           Intent intent = new Intent(Intent.ACTION_DIAL);
           intent.setData(Uri.parse("tel:"+mobile1));
           startActivity(intent);
            return true;
        }
        return true;
    }



}

