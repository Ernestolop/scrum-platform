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
package com.elopez.scrum.platform.modules.account.rest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elopez.scrum.platform.base.rest.BaseController;
import com.elopez.scrum.platform.modules.account.rest.actions.create.CreateAccountAction;
import com.elopez.scrum.platform.modules.account.rest.actions.create.CreateAccountArgument;
import com.elopez.scrum.platform.modules.account.rest.actions.get.GetAccountAction;
import com.elopez.scrum.platform.modules.account.rest.actions.get.GetAccountArgument;
import com.elopez.scrum.platform.modules.account.rest.actions.list.ListAccountsAction;
import com.elopez.scrum.platform.modules.account.rest.actions.list.ListAccountsArgument;
import com.elopez.scrum.platform.modules.account.rest.actions.update.UpdateAccountAction;
import com.elopez.scrum.platform.modules.account.rest.actions.update.UpdateAccountArgument;

@RestController
@RequestMapping("accounts")
public class AccountController extends BaseController implements IAccountController {

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountArgument account) {
        return handle(CreateAccountAction.class, account);
    }

    @GetMapping
    public ResponseEntity<?> getAccounts(@RequestParam(required = false) Pageable pageable,
            @RequestParam(required = false) String searchParam) {
        var argument = new ListAccountsArgument();
        return handle(ListAccountsAction.class, argument);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getAccount(@PathVariable String username) {
        var argument = new GetAccountArgument();
        argument.setUsername(username);
        return handle(GetAccountAction.class, argument);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateAccount(@PathVariable String username, @RequestBody UpdateAccountArgument argument) {
        argument.setUsername(username);
        return handle(UpdateAccountAction.class, argument);
    }

}