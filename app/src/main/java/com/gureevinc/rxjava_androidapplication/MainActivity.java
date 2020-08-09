package com.gureevinc.rxjava_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.observers.BlockingBaseObserver;
import io.reactivex.rxjava3.subjects.Subject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1: {
                Intent intent = new Intent(this,SimpleExampleActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button2: {
                Intent intent = new Intent(this,OperatorMapActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button3: {
                Intent intent = new Intent(this,OperatorFromActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button4: {
                Intent intent = new Intent(this,OperatorFromActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
