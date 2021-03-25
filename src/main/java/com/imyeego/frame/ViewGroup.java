package com.imyeego.frame;

/**
 * @authur : liuzhao
 * @date : 3/20/21:11:51 PM
 * @des :
 */
public class ViewGroup extends View {

    public void tranformTouchEvent() {
        super.dispatchTouchEvent();
    }


    @Override
    public boolean onTouchEvent() {
        System.out.println("ViewGroup onTouchEvent");
        return true;
    }
}
