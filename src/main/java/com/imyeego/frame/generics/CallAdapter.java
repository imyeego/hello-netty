package com.imyeego.frame.generics;

public interface CallAdapter<T> {

    <R> T adapt(Call<R> call);

}
