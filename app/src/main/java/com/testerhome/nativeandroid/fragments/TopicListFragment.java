package com.testerhome.nativeandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.testerhome.nativeandroid.Config;
import com.testerhome.nativeandroid.R;
import com.testerhome.nativeandroid.data.remote.model.TopicsResponse;
import com.testerhome.nativeandroid.fragments.base.BaseFragment;
import com.testerhome.nativeandroid.injection.Injection;
import com.testerhome.nativeandroid.views.activities.topics.TopicContract;
import com.testerhome.nativeandroid.views.activities.topics.TopicListPresenter;
import com.testerhome.nativeandroid.views.adapters.TopicListAdapter;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Bin Li on 2016/12/9.
 */

public class TopicListFragment extends BaseFragment implements TopicContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private TopicListAdapter mAdapter;

    private TopicListPresenter topicPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar demo = ((Toolbar) getView().findViewById(R.id.toolbar));
        demo.setTitle("test");

        ((AppCompatActivity) getActivity()).setSupportActionBar(demo);

        topicPresenter = new TopicListPresenter(Injection.provideThRepo(), AndroidSchedulers.mainThread(), Schedulers.io());
        topicPresenter.attachView(this);

        topicPresenter.getTopics(Config.TOPICS_TYPE_RECENT, 20);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        topicPresenter.detachView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_topic_list;
    }

    @Override
    protected void setupView() {
        super.setupView();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter = new TopicListAdapter(getContext()));

    }

    @Override
    public void showTopics(TopicsResponse topicsResponse) {
        mAdapter.setItems(topicsResponse.getTopics());
    }
}
