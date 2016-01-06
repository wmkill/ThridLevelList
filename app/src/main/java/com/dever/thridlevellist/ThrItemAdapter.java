package com.dever.thridlevellist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;

/**
 * Created by Android Studio
 * Time: 2016/1/5 19:15
 * Author: wangmeng
 */
public class ThrItemAdapter extends RecyclerView.Adapter<ThrItemAdapter.ItemViewHolder> implements View.OnClickListener {

    private Context context;
    private List<ThrList<?>> list;
    private RecyclerView recyclerView;

    public ThrItemAdapter(Context context, List<ThrList<?>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.item_text,parent,false);
                break;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.item_text,parent,false);
                break;
        }

        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ThrList<?> thrList = list.get(position);
        switch (thrList.getLevel()){
            case 0:
                holder.item_list_tv.setText((String)thrList.getData());
                holder.item_list_check.setChecked(thrList.isExpand());
                break;
            case 1:
                holder.item_list_tv.setText((String)thrList.getData());
                holder.item_list_check.setChecked(thrList.isExpand());
                break;
            case 2:
                holder.textView.setText((String)thrList.getData());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("thridList", "onClick: "+list.get(position).getLevel());
        return list.get(position).getLevel();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        ThrList<?> thrList = list.get(position);
        if(thrList.isExpandable()){
            if(thrList.isExpand()){
                removeAll(position+1,thrList.getThridList());
            }else{
                addAll(position+1,thrList.getThridList());
            }
            thrList.setIsExpand(!thrList.isExpand());
            notifyItemChanged(position);
        }else{
            Toast.makeText(v.getContext(), (String) thrList.getData(), Toast.LENGTH_SHORT).show();
        }
        Log.d("thridList", "onClick: "+position);
    }

    public void addAll(int position,Collection<? extends ThrList<?>> collection){
        list.addAll(position,collection);
        notifyItemRangeInserted(position, collection.size());
    }
    public void removeAll(int position,Collection<? extends ThrList<?>> collection){
        list.removeAll(collection);
        notifyItemRangeRemoved(position,collection.size());
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView textView,item_list_tv;
        private CheckBox item_list_check;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            item_list_tv = (TextView) itemView.findViewById(R.id.item_list_tv);
            item_list_check = (CheckBox) itemView.findViewById(R.id.item_list_check);
        }
    }
}
