package com.epam.jmp.ratkevich.service;

import java.util.List;

import com.epam.jmp.ratkevich.dto.AbstractDomainDTO;
import com.epam.jmp.ratkevich.dto.FileInfoDTO;

/**
 * Contains CRUD operations for {@link AbstractDomainDTO}
 *
 * @param <T> - real class representative of {@link AbstractDomainDTO}.
 */
public interface CrudService<T extends AbstractDomainDTO<?>> {

	default List<T> getAll() {
		throw new UnsupportedOperationException();
	}

	default T getById(Long id) {
		throw new UnsupportedOperationException();
	}

	default T update(T t) {
		throw new UnsupportedOperationException();
	}

	default void save(T t) {
		throw new UnsupportedOperationException();
	}

	default T delete(T t) {
		throw new UnsupportedOperationException();
	}

	FileInfoDTO findOne(Long id);
}
