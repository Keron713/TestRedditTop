package com.homework.testkeron.uI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.homework.testkeron.data.model.DataHolder;
import com.homework.testkeron.data.model.PostModel;
import com.homework.testkeron.data.model.PostServerModel;
import com.homework.testkeron.data.network.RedditService;
import com.homework.testkeron.data.network.RetrofitHelper;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    private RedditService service;
    private CompositeDisposable disposables = new CompositeDisposable();
    public MutableLiveData<ArrayList<PostModel>> items = new MutableLiveData<>(new ArrayList<PostModel>());
    public MutableLiveData<Boolean> isProgressActive = new MutableLiveData<>(false);
    String newAfter = null;

    public PostViewModel() {
        service = new RetrofitHelper().getCityService();
    }

    public void getPost(){
        if (items.getValue().isEmpty()){
            getData();
        }
    }

    public void getNewPost(){
        getData();
    }

    private void getData(){
        showProgress();
        disposables.add(

                service.getRedditPost(10,newAfter)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<PostServerModel>() {
                            @Override
                            public void accept(PostServerModel postServerModel) throws Exception {
                                Log.i("getPost", "http success " + postServerModel);
                                convertData(postServerModel);
                                newAfter = postServerModel.dataReddit.after;
                                hideProgress();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.i("getPost", "http throwable " + throwable);
                                hideProgress();
                            }
                        })
        );
    }

    private void convertData(PostServerModel postServerModel){
        ArrayList<PostModel> models = new ArrayList<>();
        for(DataHolder holder : postServerModel.dataReddit.childrenList){
            PostModel model = new PostModel();
            model.toPostModel(holder.data);
            models.add(model);
        }
        ArrayList<PostModel> oldModels = items.getValue();
        oldModels.addAll(models);
        items.setValue(oldModels);
    }

    private void showProgress(){
        isProgressActive.setValue(true);
    }

    private void hideProgress(){
        isProgressActive.setValue(false);
    }


}
