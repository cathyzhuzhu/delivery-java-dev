package com.twoyum.commons.service.logInfo;

import com.twoyum.commons.domain.LogInfoInDto;

/**
 * 日志服务层接口
 * @author huangjuan
 *
 */
public interface LogInfoService {

    /**
     * 保存日志
     * @param logInfoInDto 日志对象
     * @return 返回结果
     */
    Integer addLogInfo(LogInfoInDto logInfoInDto);
}
