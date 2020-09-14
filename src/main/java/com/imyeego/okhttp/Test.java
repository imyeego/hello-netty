package com.imyeego.okhttp;

/**
 * @authur : liuzhao
 * @date : 2020/9/14
 * @time : 下午 3:58
 * @Des :
 */
public class Test {
    public static void main(String[] args) {
        OkHttpUtil.getInstance().get("http://localhost:8080/user/liuzhao", new OkHttpUtil.HttpCallback() {
            @Override
            public void onSuccess(String response) {
                System.out.println(response);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
