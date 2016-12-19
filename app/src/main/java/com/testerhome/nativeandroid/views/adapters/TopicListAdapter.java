package com.testerhome.nativeandroid.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.testerhome.nativeandroid.Config;
import com.testerhome.nativeandroid.R;
import com.testerhome.nativeandroid.data.remote.model.TopicEntity;
import com.testerhome.nativeandroid.util.GlideCircleTransform;
import com.testerhome.nativeandroid.util.StringUtils;
import com.testerhome.nativeandroid.views.activities.topicdetail.TopicDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bin Li on 2015/9/16.
 */
public class TopicListAdapter extends BaseAdapter<TopicEntity> {

    public static final int TOPIC_LIST_TYPE_BANNER = 0;
    public static final int TOPIC_LIST_TYPE_TOPIC = 1;

    public TopicListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_topic, parent, false);
        return new TopicItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {


        TopicItemViewHolder holder = (TopicItemViewHolder) viewHolder;

        TopicEntity topic = mItems.get(position);

        Glide.with(mContext)
                .load(Config.getImageUrl(topic.getUser().getAvatar_url()))
                .transform(new GlideCircleTransform(mContext))
                .into(holder.topicUserAvatar);

        holder.textViewTopicTitle.setText(topic.getTitle());
        holder.topicUsername.setText(TextUtils.isEmpty(topic.getUser().getName()) ?
                topic.getUser().getLogin() : topic.getUser().getName());

        holder.topicPublishDate.setText(StringUtils.formatPublishDateTime(topic.getCreated_at()));
        holder.topicName.setText(topic.getNode_name());

        holder.topicItem.setTag(topic.getId());
        holder.topicItem.setOnClickListener(v -> {
            mContext.startActivity(new Intent(mContext, TopicDetailActivity.class)
                    .putExtra("topic", topic));
        });

        if (position == mItems.size() - 1 && mListener != null) {
            mListener.onListEnded();
        }
    }

    private EndlessListener mListener;

    public void setListener(EndlessListener mListener) {
        this.mListener = mListener;
    }

    public interface EndlessListener {
        void onListEnded();
    }

    public class TopicItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sdv_topic_user_avatar)
        ImageView topicUserAvatar;

        @BindView(R.id.tv_topic_title)
        TextView textViewTopicTitle;

        @BindView(R.id.tv_topic_username)
        TextView topicUsername;

        @BindView(R.id.tv_topic_publish_date)
        TextView topicPublishDate;

        @BindView(R.id.tv_topic_name)
        TextView topicName;

        @BindView(R.id.tv_topic_replies_count)
        TextView topicRepliesCount;

        @BindView(R.id.rl_topic_item)
        View topicItem;

        public TopicItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static final String TAG = "TopicsListAdapter";

}
