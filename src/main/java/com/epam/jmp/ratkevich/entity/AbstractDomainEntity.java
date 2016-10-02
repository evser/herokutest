package com.epam.jmp.ratkevich.entity;

import java.io.Serializable;

/**
 * Parent class of all domain entities.
 *
 * @param <T> - id class
 */
public abstract class AbstractDomainEntity<T extends Serializable> {

	private T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}
