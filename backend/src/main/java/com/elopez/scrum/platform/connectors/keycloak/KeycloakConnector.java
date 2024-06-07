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
package com.elopez.scrum.platform.connectors.keycloak;

import java.util.Collections;
import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.core.Response;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

@Service
public class KeycloakConnector {

    @Autowired
    private Keycloak keycloak;

    @Value("${keycloak.connector.realm}")
    private String realm;

    public List<UserRepresentation> getAllUsers() {
        return keycloak.realm(realm).users().list();
    }

    public UserRepresentation getUserById(String userId) {
        return keycloak.realm(realm).users().get(userId).toRepresentation();
    }

    public String createUser(UserRepresentation user, String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false); // Si la contraseña es temporal o no
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);

        user.setCredentials(Collections.singletonList(credential));

        Response response = keycloak.realm(realm).users().create(user);
        if (response.getStatus() == 201) {
            return response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        }
        return null;
    }
    public void updateUser(String userId, UserRepresentation user) {
        keycloak.realm(realm).users().get(userId).update(user);
    }

    public void deleteUser(String userId) {
        keycloak.realm(realm).users().get(userId).remove();
    }

}
