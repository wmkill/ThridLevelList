package com.dever.thridlevellist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio
 * Time: 2016/1/5 18:00
 * Author: wangmeng
 */
public class ThrList<T> {
    private T thrData;
    private int thrlevel = 0;
    private List<ThrList<?>> thridList;
    private  boolean isthrExpand;

    public ThrList(T thrData) {
        this.thrData = thrData;
    }

    public T getData() {
        return thrData;
    }

    public void setData(T data) {
        this.thrData = data;
    }

    public int getLevel() {
        return thrlevel;
    }

    public void setLevel(int level) {
        this.thrlevel = level;
    }

    public List<ThrList<?>> getThridList() {
        return thridList;
    }

    public void setThridList(List<ThrList<?>> thridList) {
        this.thridList = thridList;
    }

    public boolean isExpand() {
        return isthrExpand;
    }

    public void setIsExpand(boolean isExpand) {
        this.isthrExpand = isExpand;
    }

    public boolean isExpandable(){
        return thridList != null && !thridList.isEmpty();
    }

    public void addThridList(ThrList<?> listThr){
        if (thridList==null) {
            thridList = new ArrayList<>();
        }
        listThr.setLevel(thrlevel+1);
        thridList.add(listThr);
    }
}
