package com.epam.jmp.ratkevich.dto;

import java.io.Serializable;

/**
 * Parent class of all domain dtos.
 *
 * @param <T> id class
 */
public abstract class AbstractDomainDTO<T extends Serializable> {

	private T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}
