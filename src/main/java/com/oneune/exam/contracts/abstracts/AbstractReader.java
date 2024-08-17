package com.oneune.exam.contracts.abstracts;

import com.oneune.exam.contracts.interfaces.Readable;
import com.oneune.exam.store.dtos.AbstractDto;
import com.oneune.exam.store.entities.AbstractEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @param <E> entity
 * @param <D> dto
 */
public abstract class AbstractReader<E extends AbstractEntity, D extends AbstractDto> implements Readable<D> {
    protected abstract <PARAM> List<D> readDtos(Function<PARAM, List<E>> query, PARAM param);
    protected abstract <PARAM> List<E> readEntities(Function<PARAM, List<E>> query, PARAM param);
    protected abstract <PARAM> D readDto(Function<PARAM, Optional<E>> query, PARAM param);
    protected abstract <PARAM> E readEntity(Function<PARAM, Optional<E>> query, PARAM param);
}
