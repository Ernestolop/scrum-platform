/*
 * Copyright (c) 2024 Ernesto López
 *
 * Todos los derechos reservados.
 *
 * Este programa es software libre; puedes redistribuirlo y/o modificarlo
 * bajo los términos de la Licencia Pública General GNU tal como fue publicada por
 * la Free Software Foundation; ya sea la versión 3 de la Licencia, o
 * (a tu elección) cualquier versión posterior.
 *
 * Este programa se distribuye con la esperanza de que sea útil,
 * pero SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita de
 * COMERCIABILIDAD o IDONEIDAD PARA UN PROPÓSITO PARTICULAR. Consulta los
 * detalles de la Licencia Pública General GNU para obtener más detalles.
 */
package com.elopez.scrum.platform.base.rest.actions;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.elopez.scrum.platform.base.rest.EndpointException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

public abstract class BaseAction<A, R> implements Action<A, R> {

    @Autowired
    private Validator validator;

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
        Set<ConstraintViolation<A>> violations = validator.validate(getArgument());
        if (!violations.isEmpty()) {
            throwException(HttpStatus.BAD_REQUEST.value(), violations.iterator().next().getMessage());
        }
    }

    protected abstract void checkOnDataBase();

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
