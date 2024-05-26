package com.elopez.scrum.platform.base.rest.actions;

public interface Action<A, R> {

    public A getArgument();

    public void setArgument(A argument);

    public R getResult();

    public void setResult(R result);

    public void execute();

    public void throwException(int code, String message);

}
