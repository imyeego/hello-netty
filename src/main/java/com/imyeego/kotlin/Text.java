package com.imyeego.kotlin;

public class Text<T> {
    private T text;
    private OnWatchListener<? super T> onWatchListener;

    public void setText(T text) {
        this.text = text;
    }

    public void setOnWatchListener(OnWatchListener<T> onWatchListener) {
        this.onWatchListener = onWatchListener;
    }

    public void setTextAndListener(T content, OnWatchListener<? super T> onWatchListener) {
        this.text = content;
        this.onWatchListener = onWatchListener;
    }

    interface OnWatchListener<T> {
        void onWatch(T content);
    }
}
