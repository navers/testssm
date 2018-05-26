package com.util;

import java.util.ArrayList;
import java.util.List;

public class Page {

    int start;
    int count;
    int total;
    String param;

    public Page(int start,int count){
        this.start = start;
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public boolean isHasPrevious(){
        if (0==start)
            return false;
        return true;
    }

    public boolean isHasNext(){
        if (start==getLast())
            return false;
        return true;
    }

    public int getLast(){
        int last;
        if (0==total%count)
            last = total-count;
        else
            last = total-total%count;
        last = last<0 ? 0 : last;
        return last;
    }

    public int getTotalPage(){
        int totalPage;
        if (0==total%count)
            totalPage = total/count;
        else
            totalPage = total/count+1;
        totalPage = totalPage<1 ? 1 : totalPage;
        return totalPage;
    }

    public int getPageNum(){
        return start/count+1;
    }

    public List<Integer> getNavPages(){
        List<Integer> navPages = new ArrayList<>();
        int navPage,navCount;
        if (getTotalPage()>count) {
            navCount = count;
            if (getTotalPage()-getPageNum()>=count-1) {
                navPage = getPageNum();
            } else {
                navPage = getTotalPage()-count;
            }
        } else {
            navCount = getTotalPage();
            navPage = 1;
        }
        for (int i=0;i<navCount;i++) {
            navPages.add(navPage+i);
        }
        return navPages;
    }
}
