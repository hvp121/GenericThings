# Generic Things
Project intends to make generics, easy to use android component. Do contribute to make more generic component

----------------------------------------------
1. Generic Adapter : 
Its an easy to use generic adapter with dataBinding setup

Usage 
```
mDataBinding.recyclerview.setAdapter(new GenericAdapter<String, ListitemGenericAdapterBinding>(this,getStringsList()) {
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
```
