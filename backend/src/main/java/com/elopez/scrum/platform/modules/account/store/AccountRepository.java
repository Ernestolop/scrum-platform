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

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elopez.scrum.platform.base.store.BaseRepository;

@Repository
public interface AccountRepository extends BaseRepository<AccountEntity> {

    @Query(value = "SELECT EXISTS(SELECT 1 FROM accounts WHERE username = :username)", nativeQuery = true)
    boolean existsByUsername(String username);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM accounts WHERE email = :email)", nativeQuery = true)
    boolean existsByEmail(String email);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM accounts WHERE username = :username AND id != :id)", nativeQuery = true)
    boolean existsByUsernameExceptId(String username, String id);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM accounts WHERE username = :newUsername AND username != :username)", nativeQuery = true)
    boolean existsByUsernameExceptUsername(String newUsername, String username);

    @Query(value = "SELECT * FROM accounts WHERE CONCAT(UNACCENT(first_name || ' ' || last_name || ' ' || username || ' ' || email) LIKE CONCAT('%', UNACCENT(:searchParam), '%')", nativeQuery = true)
    Page<AccountEntity> findAllBySearchParam(Pageable pageable, String searchParam);

    Optional<AccountEntity> findByUsername(String username);

}
