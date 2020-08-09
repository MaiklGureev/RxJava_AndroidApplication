package com.gureevinc.rxjava_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.Observable;

public class SimpleExampleActivity extends AppCompatActivity {

    Observable<String> stringObservable;
    Subscriber<String> stringSubscriber;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_example);
        textView = findViewById(R.id.textViewStatus);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCreateObserver: {
                createObserver();
                break;
            }
            case R.id.buttonCreateSubscriber: {
                createSubscriber();
                break;
            }
            case R.id.buttonSubscribe: {
                doSubscribe();
                break;
            }
        }
    }

    void createObserver() {
        stringObservable = Observable.create(emitter -> {
            String message = "stringObservable: subscribe()";
            textView.append("## " + message + "\n");
            emitter.onNext("Hello from Observer!");
            emitter.onComplete();
        });
    }

    void createSubscriber() {
        stringSubscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                String message = "stringSubscriber: onSubscribe(). Message: " + s;
                textView.append("## " + message + "\n");
            }

            @Override
            public void onNext(String s) {
                String message = "stringSubscriber: onNext(). Message: " + s;
                textView.append("## " + message + "\n");
            }

            @Override
            public void onError(Throwable t) {
                String message = "stringSubscriber: onError()";
                textView.append("## " + message + "\n");
            }

            @Override
            public void onComplete() {
                String message = "stringSubscriber: onComplete()";
                textView.append("## " + message + "\n");
            }
        };
    }

    void doSubscribe() {
        if (stringSubscriber != null && stringObservable != null) {
            stringObservable.subscribe(
                    s -> {
                        stringSubscriber.onNext(s);
                    },
                    throwable -> {
                        stringSubscriber.onError(throwable);
                    },
                    () -> {
                        stringSubscriber.onComplete();
                    });
        }
    }

}
