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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elopez.scrum.platform.base.rest.BaseController;
import com.elopez.scrum.platform.modules.subject.rest.actions.create.CreateSubjectAction;
import com.elopez.scrum.platform.modules.subject.rest.actions.create.CreateSubjectArgument;

@RestController
@RequestMapping("subjects")
public class SubjectController extends BaseController implements ISubjectController {

    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody CreateSubjectArgument subject) {
        return handle(CreateSubjectAction.class, subject);
    }

}