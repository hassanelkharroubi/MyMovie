package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tacheyourself.mymovie.R;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText mUsernameText;
    private EditText mPasswordText;
    private Button signInButton;
    private Button signUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsernameText=findViewById(R.id.username);
        mPasswordText=findViewById(R.id.passsword);
        signInButton=findViewById(R.id.loginIn);
        signUpButton=findViewById(R.id.signUp);

        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(signUpButton.getId()==view.getId()){
            startActivity(new Intent(this,AdminActivity.class));

            return;
        }

        if(signInButton.getId()==view.getId()){
            startActivity(new Intent(this,MovieListActivity.class));

            return ;
        }


    }
}