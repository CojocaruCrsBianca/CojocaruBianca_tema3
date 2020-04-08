package com.example.temabianca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserFragment extends Fragment implements ToDoAdapter.ItemClickListener {

    View v;
    int user_id;
    List<ToDoModel> data = new ArrayList<>();
    ToDoAdapter adapter;
    public UserFragment(int position) {
        user_id = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment2, container, false);
        getRetrofitCall();
        return v;
    }

    private void getRetrofitCall(){
        WebService.getApi().getToDosForUser(user_id).enqueue(new Callback<List<ToDoModel>>() {
            @Override
            public void onResponse(Call<List<ToDoModel>> call, Response<List<ToDoModel>> response) {
                data = response.body();
                initRW();
            }

            @Override
            public void onFailure(Call<List<ToDoModel>> call, Throwable t) {

            }
        });
    }

    private void initRW(){
        RecyclerView rw = v.findViewById(R.id.rw);
        rw.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ToDoAdapter(getContext(), data);
        rw.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Fragment fragment = new AlarmFragment(data.get(position).getTitle());
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.FragmentHolder, fragment);
        transaction.commit();
    }
}
