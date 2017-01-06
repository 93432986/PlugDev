package com.peter.plugdev.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.peter.plugdev.mapper.VoltageMapper;
import com.peter.plugdev.model.MsgEntity;
import com.peter.plugdev.model.Voltage;
import com.peter.plugdev.util.BeansFactoryUtil;



public class ServerHandler extends ChannelInboundHandlerAdapter {
	//private final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	private static final InternalLogger logger =InternalLoggerFactory.getInstance(ServerHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		
		//ctx.channel().writeAndFlush("channelActive");
		//logger.info("channelActive11111");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
	}
	
	Timestamp ConvertToTimestamp(String time) throws Exception{
		
		Date date = new Date();//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		date = sdf.parse(time);
		return new Timestamp(date.getTime());
	}
	
	String FormatResult(String cmd, String status){
		
		String strResult=cmd+":status="+status;
		String strValue = new String().format("%08d%s", strResult.length(), strResult);
		return strValue;
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		if (msg != null && msg instanceof MsgEntity) {
			MsgEntity entity = (MsgEntity)msg;
			logger.info("chanelRead:"+entity); 
			
			if(entity.getCmd().equals("1006")){//电压数据上报
				String[] data = entity.getData().split("&");
				
				if(data.length != 4){
					logger.info("data.length != 4"); 
					return;
				}
				
				String [] imei = data[0].split("=");
				String [] state = data[1].split("=");
				String [] voltage = data[2].split("=");
				String [] time = data[3].split("=");
				
				Voltage voltageentity = new Voltage();
				voltageentity.setImei(imei[1]);
				voltageentity.setVoltage(voltage[1]);
				voltageentity.setTime(ConvertToTimestamp(time[1]));
				
				VoltageMapper voltageMapper = (VoltageMapper)BeansFactoryUtil.getBeanObject("voltageMapper");
	            voltageMapper.insertVoltageEntity(voltageentity);
	            
	            ByteBuf resp = Unpooled.copiedBuffer(FormatResult("1007", "1").getBytes());
	            ctx.writeAndFlush(resp);
			}
		}
		
		ReferenceCountUtil.release(msg); // (2)
	}

}
