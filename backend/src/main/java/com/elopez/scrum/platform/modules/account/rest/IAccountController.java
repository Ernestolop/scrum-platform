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

import org.springframework.http.ResponseEntity;

import com.elopez.scrum.platform.base.rest.ExceptionDto;
import com.elopez.scrum.platform.modules.account.rest.actions.create.CreateAccountArgument;
import com.elopez.scrum.platform.modules.account.store.AccountEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Accounts", description = "Accounts Management API")
public interface IAccountController {

    @Operation(summary = "Create a new account", description = "This operation creates a new account with the provided data.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Indicates that the account was successfully created.", content = {
                    @Content(schema = @Schema(implementation = AccountEntity.class, description = "The created account."), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Indicates that there was a problem with the provided data.", content = {
                    @Content(schema = @Schema(implementation = ExceptionDto.class, description = "Details about the error."), mediaType = "application/json") }),
    })
    public ResponseEntity<?> createAccount(CreateAccountArgument account);

}
