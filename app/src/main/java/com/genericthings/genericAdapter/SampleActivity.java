/*------------------------------------------------------------------------------
 - com.genericthings.genericAdapter.SampleActivity
 -
 - Created by manoj.bhadane
 - Copyright (c) 2018. All rights reserved.
 -
 - Last modified 13/6/18 5:13 PM
 -----------------------------------------------------------------------------*/

package com.genericthings.genericAdapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.genericthings.R;
import com.genericthings.base.BaseActivity;
import com.genericthings.databinding.ActivityGenericAdapterBinding;
import com.genericthings.databinding.ListitemGenericAdapterBinding;

import java.util.ArrayList;

public class SampleActivity extends BaseActivity {

    private ActivityGenericAdapterBinding mDataBinding;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_generic_adapter;
    }

    @Override
    public void init(ViewDataBinding dataBinding) {

        mDataBinding = (ActivityGenericAdapterBinding) dataBinding;
        mDataBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        /**
         * Sample usage one
         */
//        SampleAdapter mAdapter = new SampleAdapter(this, getModelList());
//        mDataBinding.recyclerview.setAdapter(mAdapter);


        /**
         * Sample usage Two
         */
        mDataBinding.recyclerview.setAdapter(new GenericAdapter<String, ListitemGenericAdapterBinding>(this, getStringsList()) {
            @Override
            public int getLayoutResId() {
                return R.layout.listitem_generic_adapter;
            }

            @Override
            public void onBindData(String model, int position, ListitemGenericAdapterBinding dataBinding) {
                dataBinding.txtName.setText("String " + position);
            }

            @Override
            public void onItemClick(String model, int position) {
                Toast.makeText(SampleActivity.this, "" + model.toString() + " - " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Model> getModelList() {
        ArrayList<Model> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Model model = new Model();
            model.setName("Name " + i);
            arrayList.add(model);
        }
        return arrayList;
    }

    private ArrayList<String> getStringsList() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add("Name " + i);
        }
        return arrayList;
    }
}
