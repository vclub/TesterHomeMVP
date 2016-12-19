package com.testerhome.nativeandroid.views.activities.topicdetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.testerhome.nativeandroid.Config;
import com.testerhome.nativeandroid.R;
import com.testerhome.nativeandroid.data.remote.model.TopicDetailEntity;
import com.testerhome.nativeandroid.data.remote.model.TopicDetailResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicEntity;
import com.testerhome.nativeandroid.fragments.MarkdownFragment;
import com.testerhome.nativeandroid.views.activities.topicreply.TopicReplyFragment;
import com.testerhome.nativeandroid.injection.Injection;
import com.testerhome.nativeandroid.util.GlideCircleTransform;
import com.testerhome.nativeandroid.util.StringUtils;
import com.testerhome.nativeandroid.views.activities.base.BackBaseActivity;
import com.testerhome.nativeandroid.views.activities.splash.SplashActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by libin on 2016/12/19.
 * Description:
 */

public class TopicDetailActivity extends BackBaseActivity implements TopicDetailContract.View, TopicReplyFragment.ReplyUpdateListener {

    private TopicDetailPresenter presenter;

    private String mTopicId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        setCustomTitle("this is a test title");

        presenter = new TopicDetailPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), Injection.provideThRepo());
        presenter.attachView(this);


        Uri uri = getIntent().getData();
        if (uri != null) {
            setupView();
            loadInfo(mTopicId = uri.getLastPathSegment());
        } else if (getIntent().hasExtra("topic_id")) {
            setupView();
            loadInfo(mTopicId = getIntent().getStringExtra("topic_id"));
        } else if (getIntent().hasExtra("topic")) {
            TopicEntity topic = getIntent().getParcelableExtra("topic");
            setTopicInfo(topic);
            setupView();
            loadInfo(mTopicId = topic.getId());
        } else {
            finish();
        }


    }

    private void setupView() {
        mAdapter = new TopicDetailPagerAdapter(getSupportFragmentManager());
        viewPagerTopics.setAdapter(mAdapter);

        tabLayoutTopicsTab.setupWithViewPager(viewPagerTopics);
    }

    private void loadInfo(String topicId) {
        presenter.loadTopicDetail(topicId);
    }

    private void setTopicInfo(TopicEntity topicInfo) {
        tvDetailTitle.setText(topicInfo.getTitle());
        tvDetailName.setText(topicInfo.getNode_name().concat(" • "));
        tvDetailUsername.setText(TextUtils.isEmpty(topicInfo.getUser().getLogin()) ?
                "匿名用户" : topicInfo.getUser().getName());
        tvDetailPublishDate.setText(StringUtils.formatPublishDateTime(
                topicInfo.getCreated_at()).concat(" • ")
                .concat("-").concat("次阅读"));

        Glide.with(this).load(Uri.parse(Config.getImageUrl(topicInfo.getUser().getAvatar_url()))).into(sdvDetailUserAvatar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @BindView(R.id.tab_layout)
    TabLayout tabLayoutTopicsTab;

    @BindView(R.id.view_pager)
    ViewPager viewPagerTopics;

    private TopicDetailPagerAdapter mAdapter;

    private MarkdownFragment mMarkdownFragment;
    private TopicReplyFragment mTopicReplyFragment;

    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.sdv_detail_user_avatar)
    ImageView sdvDetailUserAvatar;
    @BindView(R.id.tv_detail_name)
    TextView tvDetailName;
    @BindView(R.id.tv_detail_username)
    TextView tvDetailUsername;
    @BindView(R.id.tv_detail_publish_date)
    TextView tvDetailPublishDate;
    @BindView(R.id.tv_detail_replies_count)
    TextView tvDetailRepliesCount;

    TopicDetailEntity mTopicEntity;

    @Override
    public void showTopicDetail(TopicDetailResponse response) {
        if (response != null) {
            mTopicEntity = response.getTopic();
            if (tvDetailTitle == null) {
                return;
            }
            tvDetailTitle.setText(mTopicEntity.getTitle());
            tvDetailName.setText(mTopicEntity.getNode_name().concat(" • "));
            tvDetailUsername.setText(TextUtils.isEmpty(mTopicEntity.getUser().getLogin()) ?
                    "匿名用户" : mTopicEntity.getUser().getName());
            tvDetailPublishDate.setText(StringUtils.formatPublishDateTime(
                    mTopicEntity.getCreated_at()).concat(" • ")
                    .concat(mTopicEntity.getHits()).concat("次阅读"));

            Glide.with(this)
                    .load(Uri.parse(Config.getImageUrl(mTopicEntity.getUser().getAvatar_url())))
                    .transform(new GlideCircleTransform(this))
                    .into(sdvDetailUserAvatar);

            sdvDetailUserAvatar.setOnClickListener(view -> {
                startActivity(new Intent(TopicDetailActivity.this, SplashActivity.class).
                        putExtra("loginName", mTopicEntity.getUser().getLogin()));
            });
            // 用户回复数
            tvDetailRepliesCount.setText(getString(R.string.reply_count_info, mTopicEntity.getReplies_count()));

//            if (PraiseUtil.hasPraised(TopicDetailActivity.this, mTopicId)) {
//                tvDetailPraise.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_heart_off, 0, 0, 0);
//            } else {
//                tvDetailPraise.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_heart, 0, 0, 0);
//            }
//
//            if (FavoriteUtil.hasFavorite(TopicDetailActivity.this, mTopicId)) {
//                tvDetailCollect.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_bookmark_off, 0, 0, 0);
//            } else {
//                tvDetailCollect.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_bookmark, 0, 0, 0);
//            }

            if (mMarkdownFragment != null) {
                mMarkdownFragment.showWebContent(response.getTopic().getBody_html());
            }
        }
    }

    @BindView(R.id.llAddComment)
    View mAddCommentPanel;

    @BindView(R.id.fab_add_comment)
    FloatingActionButton mFabAddComment;

    @OnClick(R.id.fab_add_comment)
    void onFabClick() {
        mAddCommentPanel.setVisibility(View.VISIBLE);
        mFabAddComment.setVisibility(View.GONE);
    }

    @BindView(R.id.etComment)
    EditText mEtComment;

    @Override
    public void updateReplyTo(String replyInfo) {
        if (mEtComment != null) {
            mAddCommentPanel.setVisibility(View.VISIBLE);
            mFabAddComment.setVisibility(View.GONE);
            mEtComment.setText(replyInfo);
            mEtComment.setSelection(mEtComment.getText().toString().length());
        }
    }

    public class TopicDetailPagerAdapter extends FragmentPagerAdapter {

        private String[] typeName = {"帖子", "评论"};

        public TopicDetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (mMarkdownFragment == null) {
                    mMarkdownFragment = new MarkdownFragment();
                }
                return mMarkdownFragment;
            } else {
                if (mTopicReplyFragment == null) {
                    mTopicReplyFragment = TopicReplyFragment.newInstance(mTopicId, TopicDetailActivity.this);
                }
                return mTopicReplyFragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return typeName[position];
        }

        @Override
        public int getCount() {
            return typeName.length;
        }
    }
}
