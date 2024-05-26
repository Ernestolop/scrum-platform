package com.elopez.scrum.platform.base.rest.actions;

import org.springframework.http.HttpStatus;

import com.elopez.scrum.platform.base.rest.EndpointException;

public abstract class BaseAction<A, R> implements Action<A, R>{

    private A argument;
    private R result;

    @Override
    public A getArgument() {
        return argument;
    }

    @Override
    public void setArgument(A argument) {
        this.argument = argument;
    }

    @Override
    public R getResult() {
        return result;
    }

    @Override
    public void setResult(R result) {
        this.result = result;
    }
    

    protected void checkArgument() {

    }

    protected void checkOnDataBase() {

    }

    protected abstract void doAction();

    protected abstract void setActionResult();

    @Override
    public void execute() {
        checkArgument();
        checkOnDataBase();
        doAction();
        setActionResult();
    }

    @Override
    public void throwException(int code, String message) {
        throw new EndpointException(code, message, HttpStatus.BAD_REQUEST);
    }
}
