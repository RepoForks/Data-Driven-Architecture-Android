package com.mrpeak.ppkit.android;

import java.util.ArrayList;
import java.util.HashMap;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by gaofeng on 16/3/3.
 */
public class ObservableUtil {
    private static HashMap<Object, ArrayList<Subscription>> cachedSubscriptions = new HashMap<>();

    public static void preserve(Subscription sub, Object token) {
        synchronized (cachedSubscriptions) {
            ArrayList<Subscription> subs = cachedSubscriptions.get(token);
            if (subs == null) {
                subs = new ArrayList<>();
                cachedSubscriptions.put(token, subs);
            }
            subs.add(sub);
        }
    }

    public static void dispose(Object token) {
        synchronized (cachedSubscriptions) {
            ArrayList<Subscription> subs = cachedSubscriptions.get(token);
            if (subs == null) {

            } else {
                for (Subscription sub : subs) {
                    sub.unsubscribe();
                }
                subs.clear();
                cachedSubscriptions.remove(token);
            }
        }
    }
}
