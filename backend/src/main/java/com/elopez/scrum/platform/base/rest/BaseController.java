package com.elopez.scrum.platform.base.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.elopez.scrum.platform.base.rest.actions.BaseAction;

public class BaseController {

    private static final Logger LOGGER = Logger.getLogger(BaseController.class.getName());

    protected <C extends BaseAction, A> ResponseEntity<?> handle(Class<C> actionClass, A argument) {
        try {
            BaseAction<A, ?> action = actionClass.getConstructor().newInstance();
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
            endpointException = new EndpointException(0, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.severe(endpointException.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("code", endpointException.getCode());
        body.put("message", endpointException.getMessage());

        return ResponseEntity.status(endpointException.getStatus()).body(body);
    }
}
