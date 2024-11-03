/*
 * BusinessException.java
 * Copyright (c) UEG.
 */
package br.ueg.progweb2.arquitetura.exceptions;


import br.ueg.progweb2.arquitetura.util.Util;
import br.ueg.progweb2.exampleuse.exceptions.ErrorValidation;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Exceção a ser lançada na ocorrência de falhas no fluxo de negócio.
 * 
 * @author UEG
 */
@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 7986864620634914985L;

    /**
     * -- GETTER --
     *
     * @return the concat
     */
    private boolean concat;
    /**
     * -- GETTER --
     *
     * @return the code
     */
    private MessageCode code;
    /**
     * -- GETTER --
     *
     * @return the parameters
     */
    private Object[] parameters;
    /**
     * -- GETTER --
     *
     * @return the response
     */
    private MessageResponse response;

	/**
	 * Construtor da classe.
	 * 
	 * @param code - código do erro
	 * @param concat - concatenar mensagen
	 * @param parameters - parametros do erro
	 */
	public BusinessException(final MessageCode code, Boolean concat, final Object... parameters) {
		this.code = code;
		this.concat = concat;
		this.parameters = parameters;
	}

	/**
	 * Construtor da classe.
	 *
	 * @param code -
	 * @param parameters -
	 */
	public BusinessException(final MessageCode code, final Object... parameters) {
		this(code, Boolean.TRUE, parameters);
	}

	/**
	 * Construtor da classe.
	 *
	 * @param code -
	 */
	public BusinessException(final MessageCode code) {
		this.code = code;
	}

	/**
	 * Construtor da classe.
	 *
	 * @param e -
	 */
	public BusinessException(final Throwable e) {
		super(e);
	}

	/**
	 * Construtor a classe.
	 * 
	 * @param response -
	 */
	public BusinessException(MessageResponse response) {
		this.response = response;
	}

    /**
	 * @see Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String message = super.getMessage();

		if (Strings.isEmpty(super.getMessage())) {
			List<String> params = new ArrayList<>();

			if (code != null) {
				params.add("code: " + code);
			}

			params.add("concat: " + concat);

			if (hasParameters()) {
				String paramsConcat = Util.getConcatValues(", ", parameters);
				params.add("parameters: [" + paramsConcat + "]");
			}

			message = "{";
			message += Util.getConcatValues(", ", params.toArray());
			message += "}";
		}

		return message;
	}

    /**
	 * @return the hasParameters
	 */
	public boolean hasParameters() {
		return parameters != null && parameters.length > 0;
	}

}
