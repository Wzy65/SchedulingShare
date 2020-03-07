package com.wzy.schedulingshare.MainFourPage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.wzy.schedulingshare.MainFourPage.modle.Friend;
import com.wzy.schedulingshare.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @ClassName FriendListAdapter
 * @Author Wei Zhouye
 * @Date 2020/3/2
 * @Version 1.0
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder> {

    private Context mContext;

    private List<Friend> mList;

    public FriendListAdapter(Context context, List<Friend> list) {
        mContext = context;
        mList = list;
    }

    public void setDataList(List<Friend> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.friend_list_item, parent, false);
        ButterKnife.bind(this, itemView);
        return new FriendViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {

        Glide.with(mContext).
                load(mList.get(position).getFriendUser().getHeadIcon()).
                error(R.drawable.ic_picture_error). //异常时候显示的图片
                placeholder(R.drawable.ic_picture_placeholder).//加载成功前显示的图片
                fallback(R.drawable.login_head).//url为空的时候,显示的图片
                apply(RequestOptions.bitmapTransform(new CircleCrop())).
                signature(new ObjectKey(System.currentTimeMillis())). //增加版本号，避免同一名称缓存不更新
                into(holder.mFriendlistHeadIcon);
        holder.mFriendlistNickname.setText(mList.get(position).getFriendUser().getNickname());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView mFriendlistHeadIcon;
        TextView mFriendlistNickname;

        public FriendViewHolder(View itemView) {
            super(itemView);
            mFriendlistHeadIcon=(ImageView)itemView.findViewById(R.id.friendlist_headIcon);
            mFriendlistNickname=(TextView)itemView.findViewById(R.id.friendlist_nickname);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}