package com.ifpb.pdist.bancoweb.exception;


public class BancoException extends Exception {
	private static final long serialVersionUID = 1L;

	public BancoException() {
		super();
	}

	public BancoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public BancoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BancoException(String arg0) {
		super(arg0);
	}

	public BancoException(Throwable arg0) {
		super(arg0);
	}
	

}
