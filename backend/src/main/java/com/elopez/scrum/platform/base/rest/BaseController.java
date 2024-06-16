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
package com.elopez.scrum.platform.base.rest;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.elopez.scrum.platform.base.rest.actions.BaseAction;

@Controller
public class BaseController {

    @Autowired
    private ApplicationContext aplicationContext;

    private static final Logger LOGGER = Logger.getLogger(BaseController.class.getName());

    protected <C extends BaseAction, A> ResponseEntity<?> handle(Class<C> actionClass, A argument) {
        try {
            C action = aplicationContext.getBean(actionClass);
            action.setArgument(argument);
            action.execute();
            return responseOk(action);
        } catch (Exception e) {
            return responseError(e);
        }
    }

    private ResponseEntity<?> responseOk(BaseAction<?, ?> action) {
        Object body = action.getResult();
        return body != null ? ResponseEntity.ok(body) : ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> responseError(Exception e) {

        EndpointException endpointException = null;

        if (e instanceof EndpointException) {
            endpointException = (EndpointException) e;
        } else {
            throw new RuntimeException(e);
        }

        LOGGER.severe(endpointException.getMessage());

        ExceptionDto error = new ExceptionDto();
        error.setMessage(endpointException.getMessage());

        return ResponseEntity.status(endpointException.getStatus()).body(error);
    }
}
