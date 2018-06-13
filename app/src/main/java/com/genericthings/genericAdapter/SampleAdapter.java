package com.genericthings.genericAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.genericthings.R;
import com.genericthings.databinding.ListitemGenericAdapterBinding;

import java.util.ArrayList;

public class SampleAdapter extends GenericAdapter<Model, ListitemGenericAdapterBinding> {

    public SampleAdapter(Context context, ArrayList<Model> arrayList) {
        super(context, arrayList);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.listitem_generic_adapter;
    }

    @Override
    public void onBindData(Model model, int position, ListitemGenericAdapterBinding dataBinding) {
        dataBinding.txtName.setText("Model " + position);
    }

    @Override
    public void onItemClick(Model model, int position) {

    }
}
