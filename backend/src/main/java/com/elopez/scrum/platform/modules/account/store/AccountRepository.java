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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elopez.scrum.platform.base.store.BaseRepository;

@Repository
public interface AccountRepository extends BaseRepository<AccountEntity> {

    @Query(value = "SELECT EXISTS(SELECT 1 FROM accounts WHERE principal = :username)", nativeQuery = true)
    boolean existsByPrincipal(String username);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM accounts WHERE email = :email)", nativeQuery = true)
    boolean existsByEmail(String email);

}
