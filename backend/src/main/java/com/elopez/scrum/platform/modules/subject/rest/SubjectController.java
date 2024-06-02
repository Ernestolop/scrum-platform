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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elopez.scrum.platform.base.rest.BaseController;
import com.elopez.scrum.platform.base.rest.ExceptionDto;
import com.elopez.scrum.platform.modules.subject.rest.actions.create.CreateSubjectAction;
import com.elopez.scrum.platform.modules.subject.rest.actions.create.CreateSubjectArgument;
import com.elopez.scrum.platform.modules.subject.store.SubjectEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Subjects", description = "Subject Management API")
@RestController
@RequestMapping("subjects")
public class SubjectController extends BaseController {
    
    @Operation(summary = "Create a new subject")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SubjectEntity.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = ExceptionDto.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody CreateSubjectArgument subject) {
        return handle(CreateSubjectAction.class, subject);
    }

    

}
