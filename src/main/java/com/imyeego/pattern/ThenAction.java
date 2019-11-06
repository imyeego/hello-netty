package com.imyeego.pattern;

public class ThenAction implements IAction {

    private IAction tatget;

    private Action action;

    public ThenAction(IAction tatget, Action action) {
        this.tatget = tatget;
        this.action = action;
    }

    @Override
    public void execute() {
        tatget.execute();
        action.call();
    }
}
