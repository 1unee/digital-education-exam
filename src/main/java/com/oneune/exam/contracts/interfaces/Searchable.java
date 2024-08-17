package com.oneune.exam.contracts.interfaces;

import com.oneune.exam.store.dtos.AbstractDto;

import java.util.List;

@FunctionalInterface
public interface Searchable<D extends AbstractDto> {
    List<D> search(int page, int size);
}
