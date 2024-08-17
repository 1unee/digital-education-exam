package com.oneune.exam.contracts.interfaces;

import com.oneune.exam.store.dtos.AbstractDto;

import java.util.List;

public interface Readable<D extends AbstractDto> extends Searchable<D> {

    D getById(Long dtoId);

    /**
     * Use search method instead.
     */
    @Deprecated(forRemoval = true)
    List<D> getAll();
}
