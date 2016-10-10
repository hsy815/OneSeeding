package com.hsy.directseeding.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsy.directseeding.R;
import com.hsy.directseeding.uitl.MyViewHolder;
import com.hsy.directseeding.view.CircleImageView;

import java.util.List;

/**
 * Created by hsy on 16/9/28.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<String> stringList;
    private MyViewHolder.OnItemClickListener mOnItemClickListener;
    private MyViewHolder.OnItemLongClickListener mOnItemLongClickListener;

    public RecyclerAdapter(Context context, List<String> stringList) {
        this.mContext = context;
        this.stringList = stringList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(view, mOnItemClickListener, mOnItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.img_item.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
            return stringList.size();
    }

    private class ViewHolder extends MyViewHolder {
        private CircleImageView img_item;

        public ViewHolder(View itemView, MyViewHolder.OnItemClickListener listener1, MyViewHolder.OnItemLongClickListener listener2) {
            super(itemView, listener1, listener2);
            img_item = (CircleImageView) itemView.findViewById(R.id.img_item);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }

    public void setOnItemClickListener(MyViewHolder.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyViewHolder.OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

}
