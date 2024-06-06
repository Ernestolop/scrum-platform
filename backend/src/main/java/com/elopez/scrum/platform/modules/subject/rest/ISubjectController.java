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
package com.elopez.scrum.platform.modules.subject.rest;

import org.springframework.http.ResponseEntity;

import com.elopez.scrum.platform.base.rest.ExceptionDto;
import com.elopez.scrum.platform.modules.subject.rest.actions.create.CreateSubjectArgument;
import com.elopez.scrum.platform.modules.subject.store.SubjectEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Subjects", description = "Subject Management API")
public interface ISubjectController {

    @Operation(summary = "Create a new subject", description = "This operation creates a new subject with the provided data.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Indicates that the subject was successfully created.", content = {
                    @Content(schema = @Schema(implementation = SubjectEntity.class, description = "The created subject."), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Indicates that there was a problem with the provided data.", content = {
                    @Content(schema = @Schema(implementation = ExceptionDto.class, description = "Details about the error."), mediaType = "application/json") }),
    })
    public ResponseEntity<?> createSubject(CreateSubjectArgument subject);

}
