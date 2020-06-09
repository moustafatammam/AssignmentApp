package com.projects.android.domian.usecases.base;

import com.projects.android.domian.executor.PostExecutionThread;
import com.projects.android.domian.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class AbstractUseCaseForObservable <T, Params>{

    private final ThreadExecutor mThreadThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private final CompositeDisposable mCompositeDisposable;

    public AbstractUseCaseForObservable(ThreadExecutor mThreadThreadExecutor
            , PostExecutionThread mPostExecutionThread) {
        this.mThreadThreadExecutor = mThreadThreadExecutor;
        this.mPostExecutionThread = mPostExecutionThread;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public abstract Observable<T> buildObservableUseCase(Params params);

    public void execute(DisposableObserver<T> observer, Params params){
        if (observer == null){
            throw new NullPointerException();
        }
        final Observable<T> observable = this.buildObservableUseCase(params)
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
