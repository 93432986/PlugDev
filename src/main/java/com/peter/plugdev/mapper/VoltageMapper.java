package com.peter.plugdev.mapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.peter.plugdev.model.Voltage;


public interface VoltageMapper {
	void insertVoltage(@Param("imei")String imei,@Param("voltage")String voltage,@Param("time")Timestamp time);
	void insertVoltageEntity(@Param("voltage")Voltage voltage);
}
