package com.imyeego.frame;

/**
 * @authur : liuzhao
 * @date : 3/20/21:11:49 PM
 * @des :
 */
public class View {


    public boolean dispatchTouchEvent() {
        return onTouchEvent();
    }

    public boolean onTouchEvent() {
        System.out.println("View onTouchEvent");

        return true;
    }
}
