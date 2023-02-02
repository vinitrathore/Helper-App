package com.example.helperapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Toast;

import com.example.helperapp2.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
     ActivitySignUpBinding binding;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference mDbref;

    String email,password,profession,mobile,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mDbref = FirebaseDatabase.getInstance().getReference("users");



        binding.login1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

        binding.resister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.gmailL.getText().toString();
                profession = binding.profession.getText().toString();
                password = binding.password.getText().toString();
                username = binding.username.getText().toString();
                mobile = binding.mobile.getText().toString();
                signup();
            }
        });

//        email,password,profession,mobile,username
//        String profession,String username,String mobile,String email,String password
    }
    private void signup(){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            HelperModel helperModel =new  HelperModel(FirebaseAuth.getInstance().getUid(),username,email,password,profession,mobile);
                            mDbref.child(FirebaseAuth.getInstance().getUid()).setValue(helperModel);
                            startActivity(new Intent(SignUp.this,HelperInterface.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUp.this, "user creation failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }




    }
