package com.vrs.exceptions;

public class VRSDaoException extends Exception {

	private static final long serialVersionUID = -5555771695889281477L;

	public VRSDaoException()
	{
		super("VRSDaoException Occured");
	}
	
	public VRSDaoException(String msg)
	{
		super(msg);
	}
	
}
