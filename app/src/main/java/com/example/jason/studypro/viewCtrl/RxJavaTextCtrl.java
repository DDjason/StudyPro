package com.example.jason.studypro.viewCtrl;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.jason.CallBack.MyCallBack;
import com.example.jason.CallBack.PrintStr;
import com.example.jason.tool.FileUtil;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/11$ 10:08$
 * <p/>
 */
public class RxJavaTextCtrl {

    public RxJavaTextCtrl(){

    }

    public void onClickLoadFile(View view){
        File createFile = new File(Environment.getExternalStorageDirectory(), "log_rxJava.log");
        File file       = FileUtil.getAlbumStorageDir(createFile);
        String first_line = "File OutPut";
        try {
            FileOutputStream ops = new FileOutputStream(file);
            ops.write(first_line.getBytes());
            ops.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        PrintStr printStr = new PrintStr();
        printStr.pt_Str("Hello",new DoCallBack());

    }


    public void onClickRxJava2(){
//        rxJava2First();
//
//        rxJavaSimple();
//
//        rxJavaLambda();

        //rxJava2Map();

 //       rxJavafromIterable();
//        rxJavaSentList();
        rxJavaflatMap();
    }

    private void rxJava2First(){
        Log.i("RxJavaTextCtrl","onClickRxJava2");

        //create a flowable
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("Hello RxJava2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        Subscriber subscriber = new Subscriber() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i("onSubscribe","onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Object o) {
                Log.i("onNext","onNext"+ o);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.i("onComplete","onComplete");
            }
        };

        flowable.subscribe(subscriber);
    }

    private void rxJavaSimple(){
        Flowable flowable = Flowable.just("RxJava2 Simple ");
        flowable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("rxJavaSimple:",o + "rxJavaSimple");
            }
        });

    }

    private void rxJavaLambda(){
        Flowable.just("RxJavaSimpleLambda").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("RxJavaSimpleLambda:",s + "RxJavaSimpleLambda");
            }
        });
    }

    private void rxJava2Map(){
        Flowable.just("map").map(new Function<String, Object>() {
            @Override
            public Object apply(String s) throws Exception {
                return s+"pass map";
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("rxJava2Map:",o + "");
            }
        });
    }

    private void rxJavaSentList(){
        List<String > list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");


        Flowable.just(list).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                for (String  str : strings ){
                    Log.i("just list",str);
                }
            }
        });
    }

    private void rxJavafromIterable(){
        List<String > list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        Flowable.fromIterable(list).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("fromIterable",s);
            }
        });

    }

    private void rxJavaflatMap(){
        List<String > list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

       Flowable.just(list).flatMap(new Function<List<String>, Publisher<?>>() {
           @Override
           public Publisher<?> apply(List<String> strings) throws Exception {
               return Flowable.fromIterable(strings);
           }
       }).map(new Function<Object, Object>() {
           @Override
           public Object apply(Object o) throws Exception {
               return o+"www";
           }
       }).subscribe(new Consumer<Object>() {
           @Override
           public void accept(Object o) throws Exception {
               Log.i("rxJavaflatMap",o+"");
           }
       });
    }

    //提问者
    class DoCallBack implements MyCallBack{
        @Override
        public void onDoSome(String str) {
            Log.i("DoCallBack",str + "onDoSome");
        }
    }

}

