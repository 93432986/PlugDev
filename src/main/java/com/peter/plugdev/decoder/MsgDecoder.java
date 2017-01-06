package com.peter.plugdev.decoder;

import com.peter.plugdev.model.MsgEntity;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MsgDecoder extends LengthFieldBasedFrameDecoder{

	//判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是 byte+byte+int = 1+1+4 = 6
	private static final int HEADER_SIZE = 8;
	private static final int CMD_SIZE = 4;
	
	private String length;
	private String cmd;
	private String data;
	
	/**
	 * 
	 * @param maxFrameLength 解码时，处理每个帧数据的最大长度
	 * @param lengthFieldOffset 该帧数据中，存放该帧数据的长度的起始位置
	 * @param lengthFieldLength 记录该帧数据长度的字段本身的长度
	 * @param lengthAdjustment 修改帧数据长度字段中定义的值，可以为负数 
	 * @param initialBytesToStrip 解析的时候需要跳过的字节数
	 * @param failFast 为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常
	 */
	public MsgDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
			int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
	}
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		if (in == null) {
			return null;
		}
		
		if (in.readableBytes() < HEADER_SIZE) {
			throw new Exception("可读信息段比头部信息都小，你在逗我？");

		}
		
		ByteBuf buf0 = in.readBytes(8);
		byte[] req0 = new byte[buf0.readableBytes()];
		buf0.readBytes(req0);
		length = new String(req0, "UTF-8");
		int len = Integer.valueOf(length);
		
		if(len <= CMD_SIZE){
			throw new Exception("body长度小于命令头长度大小");
		}
		
		if ( in.readableBytes() < len) {
			throw new Exception("body字段你告诉我长度是"+length+",但是真实情况是没有这么多，你又逗我？"); 
		}
		
		ByteBuf bufcmd = in.readBytes(CMD_SIZE);
		byte[] reqcmd = new byte[bufcmd.readableBytes()];
		bufcmd.readBytes(reqcmd);
		cmd = new String(reqcmd, "UTF-8");
		
		Byte flag = in.readByte();
		if(flag !=':'){
			throw new Exception("数据格式不正确");
		}
		
		ByteBuf bufdata = in.readBytes(len-CMD_SIZE-1);
		byte[] reqdata = new byte[bufdata.readableBytes()];
		bufdata.readBytes(reqdata);
		data = new String(reqdata, "UTF-8");
		
		MsgEntity Msg = new MsgEntity(length, cmd, data);
		return Msg;
	}
}
