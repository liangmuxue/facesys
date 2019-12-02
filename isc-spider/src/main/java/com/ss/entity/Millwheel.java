package com.ss.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Millwheel<T> extends Object {

    private ConcurrentLinkedQueue<T> store = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<T> currt = new ConcurrentLinkedQueue();


    public void put(T element) {
        try {
            this.currt.add(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addAll(List<T> list) {
        try {
            this.currt.addAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int size() {
        return this.currt.size();
    }


    public List<T> elements() {
        ConcurrentLinkedQueue<T> swith = null;


        swith = this.currt;
        this.currt = this.store;
        this.store = swith;


        List<T> list = new ArrayList<T>();
        try {
            list.addAll(this.store);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.store.clear();
        }

        return list;
    }

}
