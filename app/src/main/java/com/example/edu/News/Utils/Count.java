package com.example.edu.News.Utils;

public class Count {
    private long time;
    private static Count tc;

    private Count() {
    }

    public static Count getInstance() {
        if (tc == null) {
            tc = new Count();
        }
        return tc;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
