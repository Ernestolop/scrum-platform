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
package com.elopez.scrum.platform.modules.account.rest.actions.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elopez.scrum.platform.base.rest.actions.BaseAction;
import com.elopez.scrum.platform.modules.account.store.AccountEntity;
import com.elopez.scrum.platform.modules.account.store.AccountService;

@Service
public class GetAccountAction extends BaseAction<GetAccountArgument, AccountEntity> {

    @Autowired
    private AccountService entityService;
    private AccountEntity entity;
    @Autowired
    private MessageSource messageSource;

    @Override
    protected void checkOnDataBase() {
        entity = entityService.findByUsername(getArgument().getUsername());
        if (entity == null)
            throwException(HttpStatus.NOT_FOUND, messageSource.getMessage("account.not.exist", null, null));
    }

    @Override
    protected void doAction() {

    }

    @Override
    protected void setActionResult() {
        setResult(entity);
    }

}
