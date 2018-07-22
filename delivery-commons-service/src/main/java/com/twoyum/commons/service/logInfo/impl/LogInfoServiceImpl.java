package com.twoyum.commons.service.logInfo.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twoyum.commons.domain.LogInfoInDto;
import com.twoyum.commons.mapper.LogInfoMapper;
import com.twoyum.commons.service.logInfo.LogInfoService;

/**
 * 日志服务层接口实现
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {

    public static Logger logger = LoggerFactory.getLogger(LogInfoServiceImpl.class);

    @Autowired
    private LogInfoMapper logInfoMapper;

	@Override
	public Integer addLogInfo(LogInfoInDto logInfoInDto) {
		logger.info("调用保存日志：operator="+logInfoInDto.getOperator());
		return logInfoMapper.addLogInfo(logInfoInDto);
	}

}
