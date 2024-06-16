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
package com.elopez.scrum.platform.modules.account.rest.actions.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import org.springframework.context.MessageSource;

import org.keycloak.representations.idm.UserRepresentation;

import com.elopez.scrum.platform.base.rest.actions.BaseAction;
import com.elopez.scrum.platform.connectors.keycloak.KeycloakConnector;
import com.elopez.scrum.platform.modules.account.store.AccountEntity;
import com.elopez.scrum.platform.modules.account.store.AccountService;

@Service
public class UpdateAccountAction extends BaseAction<UpdateAccountArgument, AccountEntity> {

    @Autowired
    private AccountService entityService;
    private AccountEntity entity;
    private UserRepresentation kcUser;
    @Autowired
    private KeycloakConnector keycloakConnector;
    private Logger log = Logger.getLogger(UpdateAccountAction.class.getName());
    @Autowired
    private MessageSource messageSource;

    @Override
    protected void checkOnDataBase() {
        String username = getArgument().getUsername();
        //String newUsername = getArgument().getNewUsername();

        entity = entityService.findByUsername(username);
        if (entity == null)
            throwException(HttpStatus.NOT_FOUND, messageSource.getMessage("account.not.exist", null, null));

        /*if (newUsername != null && entityService.existByUsernameExceptUsername(newUsername, username))
            throwException(HttpStatus.BAD_REQUEST, messageSource.getMessage("username.exists", null, null));*/

    }

    @Override
    protected void doAction() {
        kcUser = getUserRepresentation();
        loadEntities();

        try {
            keycloakConnector.updateUser(entity.getPrincipal(), kcUser);
        } catch (Exception e) {
            log.severe("Error updating user in Keycloak: " + e.getMessage());
            throw new RuntimeException("Error updating user in Keycloak");
        }

        entity = entityService.edit(entity);

    }

    @Override
    protected void setActionResult() {
        setResult(entity);
    }

    private UserRepresentation getUserRepresentation() {
        return keycloakConnector.getUserById(entity.getPrincipal());
    }

    private void loadEntities() {

        if (kcUser == null)
            throwException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting user from Keycloak");

        if (getArgument().getFirstName() != null) {
            entity.setFirstName(getArgument().getFirstName());
            kcUser.setFirstName(getArgument().getFirstName());
        }

        if (getArgument().getLastName() != null) {
            entity.setLastName(getArgument().getLastName());
            kcUser.setLastName(getArgument().getLastName());
        }

        /*if (getArgument().getNewUsername() != null) {
            entity.setUsername(getArgument().getNewUsername());
            kcUser.setUsername(getArgument().getNewUsername());
        }*/
    }

}
