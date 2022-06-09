//package com.example.designpattern.观察者模式_发布订阅模式.开关与灯的案例;
//
//import io.reactivex.Observable;
//import io.reactivex.ObservableEmitter;
//import io.reactivex.ObservableOnSubscribe;
//import io.reactivex.Observer;
//import io.reactivex.annotations.NonNull;
//import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;
//
///**
// * 被观察者就是事件的发起者，被观察者中的事件发生了改变，通知观察者，观察者去关联被观察者。
// *
// * RxJava的骨架：
// *  1. 创建被观察者，产生事件
// *  2. 设置事件传递过程中的过滤，合并，变换等加工操作。
// *  3. 订阅一个观察者对象，实现事件最终的处理。
// *
// *  Tips: 当调用订阅操作（即调用Observer.subscribe()方法）的时候，被观察者才真正开始发出事件。
// */
//public class TestClient {
//    public static void main(String[] args) {
//
//
//        //创建被观察者,这是最正宗的写法，创建了一个开关类，产生了五个事件，分别是：开，关，开，开，结束。
////        Observable switcher= Observable.create(new ObservableOnSubscribe<String>() {
////            @Override
////            public void subscribe(@NonNull ObservableEmitter<String> subscriber) throws Exception {
////                subscriber.onNext("On");
////                subscriber.onNext("Off");
////                subscriber.onNext("On");
////                subscriber.onNext("On");
////                subscriber.onComplete();
////            }
////        });
//
//        //偷懒写法：
//        Observable switcher=Observable.just("On","Off","On","On");
//
//       //创建观察者
//        Subscriber<String> light = new Subscriber<>() {
//
//            @Override
//            public void onSubscribe(Subscription s) {
//                System.out.println(s);
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("接收到元素");
//                if (s.equals("On")) {
//                    System.out.println("观察到发送过来的数据：" + s + "\t开灯");
//                } else {
//                    System.out.println("观察到发送过来的数据：" + s + "\t关灯");
//                }
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println("错误发生：" + t.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("观察结束");
//            }
//        };
//
//
//         //订阅 ,现在已经创建了观察者和被观察者，但是两者还没有联系起来
//        switcher.subscribe((Observer) light);
//    }
//}
