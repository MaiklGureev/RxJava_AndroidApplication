package com.gureevinc.rxjava_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.reactivestreams.Subscriber;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.observers.BasicFuseableObserver;
import io.reactivex.rxjava3.internal.observers.BlockingBaseObserver;

public class OperatorMapActivity extends AppCompatActivity {

    Observer<Integer> stringObserver;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_map);
        textView = findViewById(R.id.textViewStatus);
        setTitle(getClass().getSimpleName());

        stringObserver = new Observer<Integer>() {
            String message;
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                message = "stringObserver: onSubscribe().isDisposed: " + d.isDisposed();
                textView.append("##" + message + "\n");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                if (integer == 100) {
                    message = "stringObserver: onNext(). Integer: " + integer;
                    textView.append("##" + message + "\n");
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                message = "stringObserver: onError()";
                textView.append("##" + message + "\n");
            }

            @Override
            public void onComplete() {
                message = "stringObserver: onComplete()";
                textView.append("##" + message + "\n");
            }
        };
    }

    public void onClick(View view) {

        Observable.just("Ten!")
                .map(s -> {
                    if (s.equals("Ten!")) {
                        String message = "Observable.map1: accepted string " + s + " if (s.equals(\"Ten!\")) then return 10.";
                        textView.append("##" + message + "\n");
                        return 10;
                    } else
                        return 0;
                })
                .map(integer -> {
                    if (integer == 10) {
                        String message = "Observable.map2: if(integer==10) then return 100.";
                        textView.append("##" + message + "\n");
                        return 100;
                    } else {
                        return 0;
                    }
                })
                .subscribe(stringObserver);

    }
}
