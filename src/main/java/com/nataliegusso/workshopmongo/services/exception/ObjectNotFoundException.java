package com.nataliegusso.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {  //Exception: exige tratamento  //RuntimeException: não exige tratamento

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
