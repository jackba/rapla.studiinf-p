package com.google.gwtjsonrpc.client.impl;

import com.google.gwtjsonrpc.common.AsyncCallback;
import com.google.gwtjsonrpc.common.FutureResult;

public class FutureResultImpl<T> implements FutureResult<T> {
	
	JsonCall call;
	
	public T get() throws Exception
	{
		throw new UnsupportedOperationException();
	}
	public T get(long wait) throws Exception
	{
		throw new UnsupportedOperationException();
	}

	public void get(AsyncCallback<T> callback)
	{
		call.send(callback);
	}

//	public void onFailure(Throwable invocationException) {
//		if ( invocationException instanceof Exception)
//		{
//			ex = (Exception) invocationException;
//		}
//		else
//		{
//			ex = new Exception( invocationException);
//		}
//	}
//	
//	public void onSuccess(T result)
//	{
//		this.result = result;
//	}
	
	public void setCall( JsonCall call)
	{
		this.call = call;
	}
}
