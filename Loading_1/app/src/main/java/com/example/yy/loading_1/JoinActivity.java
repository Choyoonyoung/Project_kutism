package com.example.yy.loading_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinActivity extends AppCompatActivity {

    EditText id,password,email,numb,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Button button=(Button)findViewById(R.id.button3);
        id=(EditText)findViewById(R.id.id);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        numb=(EditText)findViewById(R.id.numb);
        name=(EditText)findViewById(R.id.name);



        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SharedPreferences pref=getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();

                editor.putString("id",id.getText().toString());
                editor.putString("password",password.getText().toString());
                editor.putString("email",email.getText().toString());
                editor.putString("numb",numb.getText().toString());
                editor.putString("name", name.getText().toString());

                editor.commit();

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();


            }

        });
    }


}

