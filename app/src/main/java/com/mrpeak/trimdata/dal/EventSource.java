package com.mrpeak.trimdata.dal;


import com.mrpeak.trimdata.dal.rawModel.GitHubRepository;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by gaofeng on 16/3/3.
 */


public class EventSource {
    private static EventSource ourInstance = new EventSource();
    public static EventSource getInstance() {
        return ourInstance;
    }

    private EventSource() {
        githubEvent = new DataEvent<>();
    }

    public DataEvent<GitHubRepository> githubEvent;

    public class DataEvent <T> {
        public PublishSubject<T> insert = PublishSubject.create();
        public PublishSubject<T> delete = PublishSubject.create();
        public PublishSubject<T> update = PublishSubject.create();
    }
}
