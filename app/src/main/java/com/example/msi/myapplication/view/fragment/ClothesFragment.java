package com.example.msi.myapplication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.msi.myapplication.DataFakeGenerator;
import com.example.msi.myapplication.R;
import com.example.msi.myapplication.adapter.ClothesAdapter;

/**
 * Created by msi on 8/16/2017.
 */
public class ClothesFragment extends Fragment {
    private RecyclerView recyclerView;
    private  View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_clothes, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.clothes_recycler);
        ClothesAdapter clothesAdapter = new ClothesAdapter(getActivity(), DataFakeGenerator.getClothes(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(clothesAdapter);
        return view;
    }

    public static ClothesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ClothesFragment fragment = new ClothesFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
