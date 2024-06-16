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
package com.elopez.scrum.platform.modules.account.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elopez.scrum.platform.base.store.BaseService;

@Service
public class AccountService extends BaseService<AccountEntity> {

    @Autowired
    private AccountRepository repository;

    public boolean existById(String id) {
        return repository.existsById(id);
    }

    public boolean existByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public boolean existByUsernameExceptId(String username, String id) {
        return repository.existsByUsernameExceptId(username, id);
    }

    public boolean existByUsernameExceptUsername(String newUsername, String username) {
        return repository.existsByUsernameExceptUsername(newUsername, username);
    }

    public AccountEntity findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public Page<AccountEntity> findAllBySearchParam(Pageable pageable, String searchParam) {
        return repository.findAllBySearchParam(pageable, searchParam);
    }

    @Override
    protected AccountRepository getRepository() {
        return repository;
    }

}
