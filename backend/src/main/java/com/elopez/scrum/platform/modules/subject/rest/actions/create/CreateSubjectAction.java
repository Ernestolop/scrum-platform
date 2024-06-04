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
package com.elopez.scrum.platform.modules.subject.rest.actions.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elopez.scrum.platform.base.rest.actions.BaseAction;
import com.elopez.scrum.platform.modules.subject.store.SubjectEntity;
import com.elopez.scrum.platform.modules.subject.store.SubjectService;

@Service
public class CreateSubjectAction extends BaseAction<CreateSubjectArgument, SubjectEntity>{

    @Autowired
    private SubjectService entityService;
    private SubjectEntity entity;

    @Override
    protected void checkOnDataBase() {
        System.out.println("Checking on database");
    }

    @Override
    protected void doAction() {
        System.out.println("Doing action");
    }

    @Override
    protected void setActionResult() {
        setResult(entity);
    }
    
}
