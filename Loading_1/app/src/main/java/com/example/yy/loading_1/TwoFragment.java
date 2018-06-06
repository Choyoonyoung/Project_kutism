package com.example.yy.loading_1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;
import static com.google.android.gms.flags.impl.SharedPreferencesFactory.getSharedPreferences;

public class TwoFragment extends Fragment implements View.OnClickListener{

    private static final int RC_SIGN_IN = 1001;

    boolean isPageOpen = false;

    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;

    Button button;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReferece;
    private ChildEventListener mChildEventListener;
    private ChatAdapter mAdapter;
    private ListView mListView;
    private EditText mEditMessage;
    private String userName;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;



    public TwoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        Button joinButton = (Button) view.findViewById(R.id.button);
        joinButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = (Button) view.findViewById(R.id.button);

        // 슬라이딩으로 보여질 레이아웃 객체 참조
        page = (LinearLayout) view.findViewById(R.id.page);

        //initView
        mListView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new ChatAdapter(view.getContext(), R.layout.listitem_chat);
        mListView.setAdapter(mAdapter);
        mEditMessage = (EditText) view.findViewById(R.id.textInput);
        view.findViewById(R.id.btnSend).setOnClickListener(this);



        //
        mFirebaseDatabase = mFirebaseDatabase.getInstance();
        mDatabaseReferece = mFirebaseDatabase.getReference("message");
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                chatData.firebaseKey = dataSnapshot.getKey();
                mAdapter.add(chatData);
                mListView.smoothScrollToPosition(mAdapter.getCount());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String firebaseKey = dataSnapshot.getKey();
                int count = mAdapter.getCount();
                for(int i=0; i< count; i++){
                    if(mAdapter.getItem(i).firebaseKey.equals(firebaseKey)){
                        mAdapter.remove(mAdapter.getItem(i));
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseReferece.addChildEventListener(mChildEventListener);


        // 애니메이션 객체 로딩
        translateLeftAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.translate_right);

        // 애니메이션 객체에 리스너 설정
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);



            userName = "먹짱:" + new Random().nextInt(5000);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (isPageOpen) {
                    page.startAnimation(translateRightAnim);
                } else {
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeftAnim);
                }
                break;

        }
            String message = mEditMessage.getText().toString();
            if (!TextUtils.isEmpty(message)) {
                mEditMessage.setText("");
                ChatData chatData = new ChatData();
                chatData.userName = userName;
                chatData.message = message;
                chatData.time = System.currentTimeMillis();
                mDatabaseReferece.push().setValue(chatData);
            }
    }


    /**
     * 애니메이션 리스너 정의
     */
    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        /**
         * 애니메이션이 끝날 때 호출되는 메소드
         */
        public void onAnimationEnd(Animation animation) {

            if (isPageOpen) {
                page.setVisibility(View.INVISIBLE);
                button.setText("메뉴 열기");
                isPageOpen = false;
            } else {
                button.setText("메뉴 닫기");
                isPageOpen = true;
            }
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {

        }

    }

    public void onDestroy() {
        super.onDestroy();
        mDatabaseReferece.removeEventListener(mChildEventListener);
    }


}
