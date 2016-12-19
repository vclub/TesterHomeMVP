package com.testerhome.nativeandroid.views.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.testerhome.nativeandroid.Config;
import com.testerhome.nativeandroid.R;
import com.testerhome.nativeandroid.data.remote.model.TopicReplyEntity;
import com.testerhome.nativeandroid.util.GlideCircleTransform;
import com.testerhome.nativeandroid.util.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhou.widget.RichText;

/**
 * Created by libin on 2016/12/19.
 * Description:
 */
public class TopicReplyAdapter extends BaseAdapter<TopicReplyEntity> {

    public static String TAG = "TopicReplyAdapter";

    public TopicReplyAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.list_item_reply, null);
        return new ReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        TopicReplyEntity topicReplyEntity = mItems.get(position);
        ReplyViewHolder holder = (ReplyViewHolder) viewHolder;
        holder.praiseReplyLayout.setVisibility(View.VISIBLE);
        holder.userAvatar.setVisibility(View.VISIBLE);
        holder.topicItemAuthor.setVisibility(View.VISIBLE);
        holder.topicTime.setVisibility(View.VISIBLE);
        holder.topicTime.setText(StringUtils.formatPublishDateTime(topicReplyEntity.getCreated_at()));
        holder.topicItemAuthor.setText(TextUtils.isEmpty(topicReplyEntity.getUser().getName()) ?
                topicReplyEntity.getUser().getLogin() : topicReplyEntity.getUser().getName());
        String html = topicReplyEntity.getBody_html();

        html = html.replaceAll("src=\"/", "src=\"https://testerhome.com/");
        html = html.replaceAll("/2/svg/", "/2/72x72/").replace(".svg", ".png");

        Log.d(TAG, "onBindViewHolder: " + html);

        holder.topicItemBody.setRichText(html);
        holder.topicItemBody.setMovementMethod(LinkMovementMethod.getInstance());

        Glide.with(mContext)
                .load(Uri.parse(Config.getImageUrl(topicReplyEntity.getUser().getAvatar_url())))
                .transform(new GlideCircleTransform(mContext))
                .into(holder.userAvatar);

        holder.mToReply.setTag(String.format("#%s楼 @%s ", position + 1, topicReplyEntity.getUser().getLogin()));
        holder.mToReply.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onReplyClick((String) v.getTag());
            }
        });


        if (position == mItems.size() - 1 && mListener != null) {
            mListener.onListEnded();
        }
    }

    private TopicReplyListener mListener;

    public void setListener(TopicReplyListener mListener) {
        this.mListener = mListener;
    }

    public interface TopicReplyListener {
        void onListEnded();

        void onReplyClick(String replyInfo);
    }

    public static class ReplyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_praise_reply_layout)
        LinearLayout praiseReplyLayout;

        @BindView(R.id.id_topic_item_author)
        TextView topicItemAuthor;

        @BindView(R.id.id_topic_item_content)
        RichText topicItemBody;

        @BindView(R.id.id_topic_time)
        TextView topicTime;

        @BindView(R.id.id_user_avatar)
        ImageView userAvatar;

        @BindView(R.id.tv_reply_to_reply)
        TextView mToReply;

        public ReplyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
