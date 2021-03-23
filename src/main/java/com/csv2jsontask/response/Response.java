package com.csv2jsontask.response;

public class Response {

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [data=" + data + "]";
	}

}
