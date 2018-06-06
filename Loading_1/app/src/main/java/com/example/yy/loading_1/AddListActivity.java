package com.example.yy.loading_1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddListActivity extends Activity {
    Spinner spinner_gender, spinner_place,spinner_time;
    EditText time;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4;
    String selectedImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_list);

        spinner_gender = findViewById(R.id.spinner_gender);
        spinner_place = findViewById(R.id.spinner_place);
        spinner_time = findViewById(R.id.spinner_time);

        time = findViewById(R.id.input_time);
        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.btn1);
        rb2 = findViewById(R.id.btn2);
        rb3 = findViewById(R.id.btn3);
        rb4 = findViewById(R.id.btn4);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radio_btn = (RadioButton) findViewById(checkedId);

				switch (checkedId) {

				case R.id.btn1:
				    selectedImg="@drawable/chicken";
					break;

				case R.id.btn2:
				    selectedImg="@drawable/hamburger";
					break;

				case R.id.btn3:
				    selectedImg="@drawable/pizza";
					break;

				case R.id.btn4:
				    selectedImg="@drawable/d";
					break;

				}

            }

        });


        ArrayAdapter<CharSequence> adapter_g = ArrayAdapter.createFromResource(this, R.array.gender_array,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_p = ArrayAdapter.createFromResource(this, R.array.place_array,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_t = ArrayAdapter.createFromResource(this, R.array.time_array,
                android.R.layout.simple_spinner_item);

        adapter_g.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_p.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_t.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_gender.setAdapter(adapter_g);
        spinner_place.setAdapter(adapter_p);
        spinner_time.setAdapter(adapter_t);



    }

    public void onSubmitClicked(View view) {
        Intent intent = new Intent();
        OneFragment of = new OneFragment();
        FragmentManager Frag_Manger = getFragmentManager();
        FragmentTransaction Frag_Trans = Frag_Manger.beginTransaction();
       // Frag_Trans.add(R.id.AddListActivity, of );
        Frag_Trans.commit();
        intent.putExtra("time",time.getText().toString());
        intent.putExtra("str_gender",spinner_gender.getSelectedItem().toString());
        intent.putExtra("str_place",spinner_place.getSelectedItem().toString());
        intent.putExtra("str_time",spinner_time.getSelectedItem().toString());
        setResult(56,intent);
        finish();

    }

    public void onFinish2Clicked(View view) {
        finish();
    }
}
