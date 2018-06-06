package com.example.yy.loading_1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FoodItemView extends LinearLayout {

    ImageView imageView;
    TextView textGender,textPlace,textTime,textSetTime;

    public FoodItemView(Context context) {
        super(context);
        init(context);
    }

    public FoodItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        // food_item.xml 을 대상으로 inflation하는 코드 작성
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.food_item,this, true);

        textGender = (TextView)findViewById(R.id.txtGender);
        textPlace = (TextView)findViewById(R.id.txtPlace);
        textTime = (TextView)findViewById(R.id.txtTime);
        imageView = (ImageView)findViewById(R.id.imageView);
        textSetTime = (TextView)findViewById(R.id.txtSetTime);
    }

    public void setImageView(int resID) {
        imageView.setImageResource(resID);
    }

    public void setTextGender(String gender) {
        textGender.setText(gender);
    }

    public void setTextPlace(String place) {
        textPlace.setText(place);
    }

    public void setTextTime(String time) {
        textTime.setText(time);
    }

    public void setTextSetTime(String s_time){ textSetTime.setText(s_time); }
}


