package com.qiniu.pili.droid.shortvideo.demo.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiniu.bytedanceplugin.model.FilterModel;
import com.qiniu.pili.droid.shortvideo.demo.R;

import java.util.List;

public class FilterRVAdapter extends SelectRVAdapter<FilterRVAdapter.ViewHolder> {
    private List<FilterModel> mFilterList;
    private OnItemClickListener mListener;

    public FilterRVAdapter(List<FilterModel> filterList, OnItemClickListener listener) {
        mFilterList = filterList;
        mListener = listener;
    }

    @Override
    public FilterRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter, parent, false));
    }

    @Override
    public void onBindViewHolder(final FilterRVAdapter.ViewHolder holder, final int position) {
        final FilterModel item = mFilterList.get(position);
        if (mSelect == position) {
            holder.ll.setBackgroundResource(R.drawable.bg_item_select_selector);
        } else {
            holder.ll.setBackgroundResource(R.drawable.bg_item_unselect_selector);
        }

        if (item.getIconPath() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(item.getIconPath());
            holder.iv.setImageBitmap(bitmap);
            holder.tv.setText(item.getDisplayName());
        } else {
            holder.iv.setImageResource(R.drawable.clear);
            holder.tv.setText("清除");
        }
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelect != position) {
                    mListener.onItemClick(item);
                    setSelect(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilterList.size();
    }

    public void setSelectItem(String fileName) {
        if (fileName == null) {
            setSelect(0);
            return;
        }
        for (int i = 1; i < mFilterList.size(); i++) {
            if (fileName.contains(mFilterList.get(i).getFilePath())) {
                setSelect(i);
                return;
            }
        }
        setSelect(0);
    }

    public interface OnItemClickListener {
        void onItemClick(FilterModel filterModel);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll;
        ImageView iv;
        TextView tv;

        ViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll_item_filter);
            iv = itemView.findViewById(R.id.iv_item_filter);
            tv = itemView.findViewById(R.id.tv_item_filter);
        }
    }
}
