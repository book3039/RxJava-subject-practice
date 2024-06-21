package com.syncrown.rxjavapracitce;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncSubjectDemo2();

    }

    private void asyncSubjectDemo1() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "XML", "JSON");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        observable.subscribe(asyncSubject);

        asyncSubject.subscribe(getObserver1());
        asyncSubject.subscribe(getObserver2());
        asyncSubject.subscribe(getObserver3());
    }

    private void asyncSubjectDemo2() {

        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(getObserver1());

        asyncSubject.onNext("Java");
        asyncSubject.onNext("Kotlin");
        asyncSubject.onNext("XML");

        asyncSubject.subscribe(getObserver2());

        asyncSubject.onNext("JSON");

        new Handler().postDelayed(asyncSubject::onComplete, 7000L);


        asyncSubject.subscribe(getObserver3());
    }

    private void behaviorSubjectDemo1() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "XML", "JSON");
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        behaviorSubject.onNext(s);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        behaviorSubject.onComplete();
                    }
                });

        behaviorSubject.subscribe(getObserver1());
        behaviorSubject.subscribe(getObserver2());
        behaviorSubject.subscribe(getObserver3());
    }

    private void behaviorSubjectDemo2() {

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.subscribe(getObserver1());

        behaviorSubject.onNext("Java");
        behaviorSubject.onNext("Kotlin");
        behaviorSubject.onNext("XML");

        behaviorSubject.subscribe(getObserver2());

        behaviorSubject.onNext("JSON");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getObserver3());
    }

    private void publishSubjectDemo1() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "XML", "JSON");
        PublishSubject<String> publishSubject = PublishSubject.create();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        publishSubject.onNext(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        publishSubject.onComplete();
                    }
                });


        publishSubject.subscribe(getObserver1());
        publishSubject.subscribe(getObserver2());
        publishSubject.subscribe(getObserver3());
    }

    private void publishSubjectDemo2() {

        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.subscribe(getObserver1());

        publishSubject.onNext("Java");
        publishSubject.onNext("Kotlin");
        publishSubject.onNext("XML");

        publishSubject.subscribe(getObserver2());

        publishSubject.onNext("JSON");
        publishSubject.onComplete();

        publishSubject.subscribe(getObserver3());
    }

    private void replaySubjectDemo1() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "XML", "JSON");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        ReplaySubject<String> replaySubject = ReplaySubject.create();
        observable.subscribe(replaySubject);

        replaySubject.subscribe(getObserver1());
        replaySubject.subscribe(getObserver2());
        replaySubject.subscribe(getObserver3());
    }

    private void replaySubjectDemo2() {

        ReplaySubject<String> replaySubject = ReplaySubject.create();

        replaySubject.subscribe(getObserver1());

        replaySubject.onNext("Java");
        replaySubject.onNext("Kotlin");
        replaySubject.onNext("XML");

        replaySubject.subscribe(getObserver2());

        replaySubject.onNext("JSON");
        new Handler().postDelayed(replaySubject::onComplete, 7000L);
//        replaySubject.onComplete();

        replaySubject.subscribe(getObserver3());
    }

    private Observer<String> getObserver1() {
        Observer<String> obsever = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("MyApp", "Observer1 onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i("MyApp", "Observer1 onNext " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("MyApp", "Observer1 onError");

            }

            @Override
            public void onComplete() {
                Log.i("MyApp", "Observer1 onComplete");

            }
        };

        return obsever;
    }
    private Observer<String> getObserver2() {
        Observer<String> obsever = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("MyApp", "Observer2 onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i("MyApp", "Observer2 onNext " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("MyApp", "Observer2 onError");

            }

            @Override
            public void onComplete() {
                Log.i("MyApp", "Observer2 onComplete");

            }
        };

        return obsever;
    }
    private Observer<String> getObserver3() {
        Observer<String> obsever = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("MyApp", "Observer3 onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i("MyApp", "Observer3 onNext " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("MyApp", "Observer3 onError");

            }

            @Override
            public void onComplete() {
                Log.i("MyApp", "Observer3 onComplete");

            }
        };

        return obsever;
    }
}