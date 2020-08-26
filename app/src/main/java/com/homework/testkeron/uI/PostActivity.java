package com.homework.testkeron.uI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.homework.testkeron.R;
import com.homework.testkeron.data.model.DataHolder;
import com.homework.testkeron.data.model.PostModel;
import com.homework.testkeron.data.model.PostServerModel;
import com.homework.testkeron.data.network.RedditService;
import com.homework.testkeron.data.network.RetrofitHelper;
import com.homework.testkeron.uI.AdapterPost;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private AdapterPost adapter;
    private RedditService service;
    private CompositeDisposable disposables = new CompositeDisposable();
    private PostViewModel viewModel;
    private ProgressBar progressBar;
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler);
        adapter = new AdapterPost();
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        service = new RetrofitHelper().getCityService();
        progressBar = findViewById(R.id.progress_bar);
        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
        viewModel.getPost();
        viewModel.items.observe(this, new Observer<ArrayList<PostModel>>() {
            @Override
            public void onChanged(ArrayList<PostModel> postModels) {
                adapter.setData(postModels);
                loading = true;
            }
        });
        viewModel.isProgressActive.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("getPost", "onScrolled success " + dy);
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            viewModel.getNewPost();
                            Log.v("...", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        disposables.dispose();
        super.onDestroy();
    }


}