package com.twoyum.commons.mapper;

import com.twoyum.commons.domain.LogInfoInDto;

/**
 * 日志DAO层
 */
public interface LogInfoMapper {
	int addLogInfo(LogInfoInDto logInfoInDto);
}