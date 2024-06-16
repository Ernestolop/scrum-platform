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

import com.elopez.scrum.platform.base.store.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class AccountEntity extends BaseEntity {

    @Column(name = "first_name")
    @Size(max = 255, message = "{firstName.size}")
    private String firstName;
    @Column(name = "last_name")
    @Size(max = 255, message = "{lastName.size}")
    private String lastName;
    @NotNull(message = "{email.required}")
    @NotEmpty(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;
    @NotNull(message = "{username.required}")
    @Size(min = 2, max = 255, message = "{username.size}")
    private String username;
    @NotNull
    private String principal;

}
