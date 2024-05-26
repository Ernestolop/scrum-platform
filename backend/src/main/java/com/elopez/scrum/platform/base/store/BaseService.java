package com.elopez.scrum.platform.base.store;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class BaseService<E extends BaseEntity> {

  public E add(E entity) {
    String id = UUID.randomUUID().toString();

    entity.setId(id);
    entity.setEnabled(true);
    entity.setCreatedAt(new Date());
    entity.setCreatedBy("system");

    return save(entity);
  }

  public boolean deleteById(String id) {
    getRepository().deleteById(id);
    return true;
  }

  public boolean delete(E entity) {
    getRepository().delete(entity);
    return true;
  }

  public E enableById(String id) {
    return enable(findById(id));
  }

  public E enable(E entity) {
    if (entity != null && !entity.isEnabled()) {
      entity.setEnabled(true);
      entity = edit(entity);
    }

    return entity;
  }

  public E disableById(String id) {
    return disable(findById(id));
  }

  public E disable(E entity) {
    if (entity != null && entity.isEnabled()) {
      entity.setEnabled(false);
      entity = edit(entity);
    }

    return entity;
  }

  public E editById(String id, E draft) {
    return edit(findById(id), draft);
  }

  public E edit(E entity, E draft) {
    if (entity == null) {
      return null;
    }

    return edit(entity);
  }

  public E edit(E entity) {
    
    entity.setUpdatedAt(new Date());
    entity.setUpdatedBy("system");

    return save(entity);
  }

  public E findById(String id) {
    return getRepository().findById(id).orElse(null);
  }

  public Page<E> findAll(Pageable pageable) {
    return getRepository().findAll(pageable);
  }

  public E save(E entity) {
    return getRepository().save(entity);
  }

  protected abstract BaseRepository<E> getRepository();
  
}
