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
package com.elopez.scrum.platform.modules.account.rest.actions.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elopez.scrum.platform.base.rest.actions.BaseAction;
import com.elopez.scrum.platform.modules.account.store.AccountEntity;
import com.elopez.scrum.platform.modules.account.store.AccountService;

@Service
public class ListAccountsAction extends BaseAction<ListAccountsArgument, Page<AccountEntity>> {

    @Autowired
    private AccountService entityService;
    private Page<AccountEntity> entityPage;

    @Override
    protected void checkOnDataBase() {

    }

    @Override
    protected void doAction() {

        if (getArgument().getPageable() == null) {
            Pageable pageable = Pageable.unpaged();
            getArgument().setPageable(pageable);
        }

        if (getArgument().getSearchParam() != null) {
            entityPage = entityService.findAllBySearchParam(getArgument().getPageable(),
                    getArgument().getSearchParam());
            return;
        }
        entityPage = entityService.findAll(getArgument().getPageable());

    }

    @Override
    protected void setActionResult() {
        setResult(entityPage);
    }

}
