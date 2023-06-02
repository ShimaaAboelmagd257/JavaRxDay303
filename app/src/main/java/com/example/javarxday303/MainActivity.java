package com.example.javarxday303;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.function.Function;

import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<Integer> sourceObservable = Observable.range(1, 10);
//which accepts an Observable and returns another Observable. This allows you to create reusable operator chains that can be applied to multiple Observables.
        ObservableTransformer<Integer, String> applyCustomOperators = observable ->
                observable
                        .map(number -> number * 2)
                        .map(number -> "Transformed: " + number)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.single());

        sourceObservable
                .compose(applyCustomOperators)
                .subscribe(result -> Log.d("TransformedValue", result));


//he distinct operator is used to filter out duplicate emissions from an Observable. 



/*
        Observable<Integer> sourceObservable = Observable.just(1, 1, 2, 2, 3, 4, 4, 4, 5);

        sourceObservable
                .distinctUntilChanged()
                .subscribe(result -> Log.d("DistinctValue", String.valueOf(result)));
    }*/

    }
}