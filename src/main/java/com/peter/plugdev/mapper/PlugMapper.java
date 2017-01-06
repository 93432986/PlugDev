package com.peter.plugdev.mapper;

import com.peter.plugdev.model.Voltage;

public interface PlugMapper {
	Voltage selectVoltage(int id);
}
