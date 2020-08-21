package com.homework.testkeron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.homework.testkeron.model.DataHolder;
import com.homework.testkeron.model.PostModel;
import com.homework.testkeron.model.PostServerModel;
import com.homework.testkeron.network.RedditService;
import com.homework.testkeron.network.RetrofitHelper;

import java.util.ArrayList;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private AdapterPost adapter;
    private RedditService service;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler);
        adapter = new AdapterPost();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        service = new RetrofitHelper().getCityService();
        getPost();

    }

    private void getPost(){
        disposables.add(

                service.getRedditPost(100,null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PostServerModel>() {
                    @Override
                    public void accept(PostServerModel postServerModel) throws Exception {
                        Log.i("getPost", "http success " + postServerModel);
                        setData(postServerModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("getPost", "http throwable " + throwable);
                    }
                })

        );

    }

    private void setData(PostServerModel postServerModel){
        ArrayList<PostModel> items = new ArrayList<>();
        for(DataHolder holder : postServerModel.dataReddit.childrenList){
            PostModel model = new PostModel();
            model.toPostModel(holder.data);
            items.add(model);
        }
        adapter.setData(items);
    }

    @Override
    protected void onDestroy() {
        disposables.dispose();
        super.onDestroy();
    }
}