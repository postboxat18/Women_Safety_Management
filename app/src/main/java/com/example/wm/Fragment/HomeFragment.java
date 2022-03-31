package com.example.wm.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.wm.Adapter.AddAdapter;
import com.example.wm.Class.AddPhonenum;
import com.example.wm.Class.MyLog;
import com.example.wm.R;
import com.example.wm.WebService_Class;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LottieAnimationView press_send_button,send_email;
    private RecyclerView recyclerview_details;
    private AddAdapter addAdapter;
    private ArrayList<AddPhonenum> addPhonenumArrayList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String TAG="HomeFragment";
    Bundle bundle;
  //  private ImageView id_tick;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=   inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Web_Config", MODE_PRIVATE);
        String temp_URL = sharedPreferences.getString("Pin", null);
        String pin=new WebService_Class(getActivity()).getPin();
        String json=new WebService_Class(getActivity()).getArraylist();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<AddPhonenum>>() {}.getType();

        //Post post = gson.fromJson(reader, Post.class);
        //AddPhonenum addPhonenumArray = gson.fromJson(json, AddPhonenum.class);
        addPhonenumArrayList=gson.fromJson(json, type);
    // MyLog.d(TAG,"ClickeTest:updatedMedicine frag:"+new GsonBuilder().setPrettyPrinting().create().toJson(updatedMedicine));
        //MyLog.e(TAG,"list>>home>>"+addPhonenumArrayList.size()+">>"+new GsonBuilder().setPrettyPrinting().create().toJson(addPhonenumArrayList));
        /* id_tick = view.findViewById(R.id.id_tick);*/
        recyclerview_details = view.findViewById(R.id.recyclerview_add_num);
        recyclerview_details.setHasFixedSize(true);
        recyclerview_details.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview_details.setNestedScrollingEnabled(false);
        addAdapter = new AddAdapter(getActivity(), addPhonenumArrayList);
        recyclerview_details.setAdapter(addAdapter);


        send_email = view.findViewById(R.id.send_email);
        press_send_button = view.findViewById(R.id.press_send_button);
        press_send_button
                .addAnimatorUpdateListener(
                        (animation) -> {

                            // Do something.
                        });
        press_send_button
                .playAnimation();

        if (press_send_button.isAnimating()) {

            // Do something.
        }

        press_send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                press_send_button.setVisibility(View.GONE);
                send_email.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "SENDING MESSAGE", Toast.LENGTH_SHORT).show();
                send_email
                        .addAnimatorUpdateListener(
                                (animation) -> {

                                    // Do something.
                                });
                send_email
                        .playAnimation();

                if (send_email.isAnimating()) {

                    // Do something.
                }
            }
        });

        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_email.setVisibility(View.GONE);
                press_send_button.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "STOPPED", Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }
}