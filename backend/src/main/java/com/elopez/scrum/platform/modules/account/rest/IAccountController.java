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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.elopez.scrum.platform.base.rest.ExceptionDto;
import com.elopez.scrum.platform.modules.account.rest.actions.create.CreateAccountArgument;
import com.elopez.scrum.platform.modules.account.rest.actions.update.UpdateAccountArgument;
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

        @Operation(summary = "Get all accounts", description = "The search parameter is optional and can be used to search by first name, last name, email, or username.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Indicates that the accounts were successfully retrieved.", content = {
                                        @Content(schema = @Schema(implementation = Page.class, description = "The retrieved accounts."), mediaType = "application/json") }),
        })
        public ResponseEntity<?> getAccounts(Pageable pageable, String searchParam);

        @Operation(summary = "Get an account by its username", description = "This operation retrieves an account by its username.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Indicates that the account was successfully retrieved.", content = {
                                        @Content(schema = @Schema(implementation = AccountEntity.class, description = "The retrieved account."), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "404", description = "Indicates that the account was not found.", content = {
                                        @Content(schema = @Schema(implementation = ExceptionDto.class, description = "Details about the error."), mediaType = "application/json") }),
        })
        public ResponseEntity<?> getAccount(String username);

        @Operation(summary = "Update an account by its username", description = "This operation updates an account by its username. The firstName and lastName can be updated.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Indicates that the account was successfully updated.", content = {
                                        @Content(schema = @Schema(implementation = AccountEntity.class, description = "The updated account."), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "400", description = "Indicates that there was a problem with the provided data.", content = {
                                        @Content(schema = @Schema(implementation = ExceptionDto.class, description = "Details about the error."), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "404", description = "Indicates that the account was not found.", content = {
                                        @Content(schema = @Schema(implementation = ExceptionDto.class, description = "Details about the error."), mediaType = "application/json") }),
        })
        public ResponseEntity<?> updateAccount(String username, UpdateAccountArgument account);

}
