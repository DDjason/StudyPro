package com.example.jason.studypro.viewCtrl;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.jason.studypro.R;
import com.example.jason.studypro.databinding.RecycleViewShowBinding;
import com.example.jason.studypro.viewModel.RecycleModel;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/13$ 11:38$
 * <p/>{@link com.example.jason.studypro.act.RecycleViewAct}
 */
public class RecycleViewCtrl {
    private RecycleModel           viewModel;
    private RecycleViewShowBinding binding;
    private MyRecyclerAdapter      adapter;
    List<String> data = new ArrayList<>();
    private ArrayList<String> oldData;

    public RecycleViewCtrl(RecycleViewShowBinding binding) {
        viewModel = new RecycleModel();
        //        if (Config.isDebug.getmValue()){
        //            Log.i("RecycleViewCtrl","TRUE");
        //        }
        this.binding = binding;
        Random rd = new Random();
        for (int i = 0; i < 1225; i++) {
            int k = rd.nextInt(100000) + 1;
            data.add(k + "");
        }
        init();
    }

    private void init() {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {
                if (source.equals(" ")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        InputFilter[] filtersold = binding.editText.getFilters();
        InputFilter[] filters    = new InputFilter[filtersold.length + 1];
        int           position   = 0;
        for (; position < filtersold.length; position++) {
            filters[position] = filtersold[position];
        }

        filters[position] = filter;

        binding.editText.setFilters(filters);
        LinearLayoutManager manager = new LinearLayoutManager(binding.getRoot().getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recycleView.setLayoutManager(manager);
        adapter = new MyRecyclerAdapter(binding.getRoot().getContext());
        binding.recycleView.setAdapter(adapter);
    }

    public void onChangeEdit(View view) {
        Log.i(this.getClass().getName(), "ChangeEdit");
    }

    public void onClickExec(View view) {
        try {
            //获取权限 2333
            Process          localProcess          = Runtime.getRuntime().exec("su");
            Object           localObject           = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream((OutputStream) localObject);
            String           str                   = String.valueOf("echo test");
            localObject = str + "\n";
            localDataOutputStream.writeBytes((String) localObject);
            localDataOutputStream.flush();
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localProcess.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> implements Filterable {
        private Context mContext;
        private Filter  filter;
        String mlock = "myLock";

        public MyRecyclerAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.binding.setVariable(BR.item, data.get(position));
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_view_item,parent,false);
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.recycle_view_item, parent, false);
            MyViewHolder    vh      = new MyViewHolder(binding);

            return vh;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ViewDataBinding binding;

            public MyViewHolder(ViewDataBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }

        @Override
        public Filter getFilter() {
            if (filter == null) {
                filter = new MyFilter();
            }
            return filter;
        }

        class MyFilter extends Filter {
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                data = (ArrayList<String>) filterResults.values;
                if (filterResults.count > 0) {
                    notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (oldData == null) {
                    synchronized (mlock) {
                        oldData = new ArrayList<>(data);
                    }
                }
                if (charSequence == null || charSequence.length() == 0) {
                    synchronized (mlock) {
                        ArrayList<String> list = new ArrayList<String>(
                                oldData);
                        filterResults.values = list;
                        filterResults.count = list.size();
                    }
                } else {
                    String prefixString = charSequence.toString().toLowerCase();
                    // 声明一个临时的集合对象 将原始数据赋给这个临时变量
                    final ArrayList<String> values = oldData;
                    final int               count  = values.size();
                    // 新的集合对象
                    final ArrayList<String> newValues = new ArrayList<String>(
                            count);
                    for (int i = 0; i < count; i++) {
                        final String value = (String) values.get(i);
                        if (value.startsWith(
                                prefixString)) {
                            newValues.add(value);
                        }
                    }

                    // 然后将这个新的集合数据赋给FilterResults对象
                    filterResults.values = newValues;
                    filterResults.count = newValues.size();
                }
                return filterResults;
            }
        }
    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            adapter.getFilter().filter(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public RecycleModel getViewModel() {
        return viewModel;
    }
}
