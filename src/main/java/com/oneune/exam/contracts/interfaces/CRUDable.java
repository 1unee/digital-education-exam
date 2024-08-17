package com.oneune.exam.contracts.interfaces;

import com.oneune.exam.store.dtos.AbstractDto;

/**
 * Common CRUD contract
 * @param <D> dto
 */
public interface CRUDable<D extends AbstractDto> extends Readable<D> {
    D post(D dto);
    D put(Long dtoId, D dto);
    D deleteById(Long dtoId);
}
