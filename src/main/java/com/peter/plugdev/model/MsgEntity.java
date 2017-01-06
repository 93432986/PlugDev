package com.peter.plugdev.model;

public class MsgEntity {
	String length;

	String cmd;
	String data;	
	
	public MsgEntity(String cmd, String data) {
		super();
		this.cmd = cmd;
		this.data = data;
	}
	public MsgEntity(String length, String cmd, String data) {
		super();
		this.length = length;
		this.cmd = cmd;
		this.data = data;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "MsgEntity [length=" + length + ", cmd=" + cmd + ", data=" + data + "]";
	}
	
	
}
