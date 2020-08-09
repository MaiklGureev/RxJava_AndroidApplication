package com.gureevinc.rxjava_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

public class OperatorFromActivity extends AppCompatActivity {

    private String TAG = "OperatorFromActivity";
    private TextView textView;
    private String[] strings = new String[]{"One","Two","Three","Four"};
    private Observer<String> stringObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_from);

        textView = findViewById(R.id.textViewStatus);
        stringObserver = new Observer<String>() {
            String message;
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                message= "stringObserver: onSubscribe().isDisposed: " + d.isDisposed();
                textView.append("##" + message + "\n");
            }

            @Override
            public void onNext(@NonNull String s) {
                message = "stringObserver: onNext(). String: " +s;
                textView.append("##" + message + "\n");
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
        Observable.fromArray(strings)
                .subscribe(stringObserver);
    }
}
