package com.example.yy.loading_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;


import java.util.ArrayList;

public class OneFragment extends ListFragment {
    GridView gridView;
    FoodAdapter foodAdapter;

    public OneFragment() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        gridView = (GridView) view.findViewById(R.id.gridView);

        // 어댑터 생성
        foodAdapter = new FoodAdapter();
        // 어댑터 통해서 아이템들을 추가
        foodAdapter.addItem(new FoodItem("여자","기숙사 C동","오후 6시", R.drawable.chicken));
        foodAdapter.addItem(new FoodItem("남자","학관","오후 2시", R.drawable.hamburger));
        foodAdapter.addItem(new FoodItem("남자","기숙사 A동","오후 9시", R.drawable.pigfoot));
        foodAdapter.addItem(new FoodItem("여자", "기숙사 C동", "오후 6시",R.drawable.pizza));
        foodAdapter.addItem(new FoodItem("여자", "명은빌라아파트","오후8시", R.drawable.d));
        // 리스트뷰에 어댑터 설정
        gridView.setAdapter(foodAdapter);
        foodAdapter.notifyDataSetChanged();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);


        return view;
    }

    class FoodAdapter extends BaseAdapter {
        ArrayList<FoodItem> items = new ArrayList<FoodItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(FoodItem item){
            items.add(item);
        }

        @Override // ******중요*******
        public View getView(int position, View convertView, ViewGroup parent) {
            FoodItemView view = new FoodItemView(getContext());

            FoodItem item = items.get(position);
            view.setTextGender(item.getGender());
            view.setTextPlace(item.getPlace());
            view.setTextTime(item.getTime());
            view.setImageView(item.getResId());
            return view;
        }
    }
}