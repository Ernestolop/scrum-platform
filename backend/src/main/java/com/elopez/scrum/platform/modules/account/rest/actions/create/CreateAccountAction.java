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
package com.elopez.scrum.platform.modules.account.rest.actions.create;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elopez.scrum.platform.base.rest.actions.BaseAction;
import com.elopez.scrum.platform.connectors.keycloak.KeycloakConnector;
import com.elopez.scrum.platform.modules.account.store.AccountEntity;
import com.elopez.scrum.platform.modules.account.store.AccountService;

@Service
public class CreateAccountAction extends BaseAction<CreateAccountArgument, AccountEntity> {

    @Autowired
    private AccountService entityService;
    private AccountEntity entity;
    @Autowired
    private KeycloakConnector keycloakConnector;

    @Override
    protected void checkOnDataBase() {
        if (entityService.existByUsername(getArgument().getUsername()))
            throwException(400, "Username already exists");

        if (entityService.existByEmail(getArgument().getEmail()))
            throwException(400, "Email already exists");
    }

    @Override
    protected void doAction() {
        
        try {
            UserRepresentation user = new UserRepresentation();
            user.setUsername(getArgument().getUsername());
            user.setEmail(getArgument().getEmail());
            user.setFirstName(getArgument().getFirstName());
            user.setLastName(getArgument().getLastName());
            user.setEmailVerified(false);
            user.setEnabled(true);
            keycloakConnector.createUser(user, getArgument().getPassword());
        } catch (Exception e) {
            throwException(500, e.getMessage());
        }

        entity = new AccountEntity();
        entity.setPrincipal(getArgument().getUsername());
        entity.setEmail(getArgument().getEmail());
        entity.setFirstName(getArgument().getFirstName());
        entity.setLastName(getArgument().getLastName());
        entity = entityService.add(entity);

    }

    @Override
    protected void setActionResult() {
        setResult(entity);
    }

}
