package com.projects.android.domian.usecases.base;

import com.projects.android.domian.executor.PostExecutionThread;
import com.projects.android.domian.executor.ThreadExecutor;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class AbstractUseCaseForCompletable<Params>{

    private final ThreadExecutor mThreadThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private final CompositeDisposable mCompositeDisposable;

    public AbstractUseCaseForCompletable(ThreadExecutor mThreadThreadExecutor, PostExecutionThread mPostExecutionThread){
        this.mThreadThreadExecutor = mThreadThreadExecutor;
        this.mPostExecutionThread = mPostExecutionThread;
        this.mCompositeDisposable = new CompositeDisposable();
    }
    public abstract Completable buildCompletableUseCase(Params params);

    public void execute(DisposableCompletableObserver observer, Params params){
        if (observer == null){
            throw new NullPointerException();
        }
        final Completable observable = this.buildCompletableUseCase(params)
                .subscribeOn(Schedulers.from(mThreadThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose(){
        if(!mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
    }

    private void addDisposable(Disposable disposable){
        if(disposable == null){
            throw new NullPointerException();
        }else if (mCompositeDisposable == null){
            throw new NullPointerException();
        }
        mCompositeDisposable.add(disposable);

    }
}
