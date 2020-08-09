package com.gureevinc.rxjava_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.Observable;

public class SimpleExampleActivity extends AppCompatActivity {

    private Observable<String> stringObservable;
    private Subscriber<String> stringSubscriber;
    private TextView textView;
    private String value = "Hello from Observer!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_example);
        setTitle(getClass().getSimpleName());

        textView = findViewById(R.id.textViewStatus);
    }

    public void onClick(View view) {
        String message;
        switch (view.getId()) {
            case R.id.buttonCreateObserver: {
                if (stringObservable == null) {
                    createObserver(value);
                    message = "buttonCreateObserver: create with value " + value;
                    textView.append("## " + message + "\n");
                }
                break;
            }
            case R.id.buttonCreateSubscriber: {
                if (stringSubscriber == null) {
                    message = "buttonCreateSubscriber: create!";
                    textView.append("## " + message + "\n");
                    createSubscriber();
                }
                break;
            }
            case R.id.buttonSubscribe: {
                if (stringSubscriber != null && stringObservable != null) {
                    message = "buttonSubscribe: do subscribe!!";
                    textView.append("## " + message + "\n");
                    doSubscribe();
                }
                break;
            }
        }
    }

    void createObserver(String value) {
        stringObservable = Observable.create(emitter -> {
            String message = "stringObservable: subscribe()";
            textView.append("## " + message + "\n");
            emitter.onNext(value);
            emitter.onComplete();
        });
    }

    void createSubscriber() {
        stringSubscriber = new Subscriber<String>() {
            String message;

            @Override
            public void onSubscribe(Subscription s) {
                message = "stringSubscriber: onSubscribe(). Message: " + s;
                textView.append("## " + message + "\n");
            }

            @Override
            public void onNext(String s) {
                message = "stringSubscriber: onNext(). Message: " + s;
                textView.append("## " + message + "\n");
            }

            @Override
            public void onError(Throwable t) {
                message = "stringSubscriber: onError()";
                textView.append("## " + message + "\n");
            }

            @Override
            public void onComplete() {
                message = "stringSubscriber: onComplete()";
                textView.append("## " + message + "\n");
            }
        };
    }

    void doSubscribe() {

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
