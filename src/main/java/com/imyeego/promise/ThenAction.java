package com.imyeego.promise;

public class ThenAction<T> implements IAction<T> {

    private IAction<T> target;
    private Action<T> action;

    public ThenAction(IAction<T> target, Action<T> action) {
        this.target = target;
        this.action = action;
    }

    @Override
    public void execute(T t) {
        if (target != null) {
            target.execute(t);
        }
        action.call(t);
    }
}
