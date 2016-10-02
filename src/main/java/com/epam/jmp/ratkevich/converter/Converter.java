package com.epam.jmp.ratkevich.converter;

import com.epam.jmp.ratkevich.dto.AbstractDomainDTO;
import com.epam.jmp.ratkevich.entity.AbstractDomainEntity;

public interface Converter<T extends AbstractDomainDTO<Long>, S extends AbstractDomainEntity<Long>> {

	T convert(S entity);
}
