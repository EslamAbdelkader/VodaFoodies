package com.example.eslam.vodafoodies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.vodafoodies.MyApplication;
import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.model.User;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private View rootView;
    private TextView userNameTextView;
    private TextView userPhoneTextView;
    private TextView userEmailTextView;
    private ImageView userPictureImageView;
    private User user;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        user = MyApplication.getUser();
        initUI();
        initUIValues();
        return rootView;
    }

    private void initUI() {
        userNameTextView = rootView.findViewById(R.id.userName);
        userPhoneTextView = rootView.findViewById(R.id.userPhone);
        userEmailTextView = rootView.findViewById(R.id.userEmail);
        userPictureImageView = rootView.findViewById(R.id.userImage);
    }


    private void initUIValues() {
        int placeHolder;
        if (user.getGender().equalsIgnoreCase("female"))
            placeHolder = R.drawable.user_female_icon;
        else
            placeHolder = R.drawable.user_male_icon;
        Picasso.with(getContext()).load(user.getImg()).placeholder(placeHolder).into(userPictureImageView);
        userNameTextView.setText(user.getName());
        userEmailTextView.setText(user.getEmail());
//        userPhoneTextView.setText(user.getPhone());   // TODO: 9/15/2017 uncomment when applicable
    }

}
