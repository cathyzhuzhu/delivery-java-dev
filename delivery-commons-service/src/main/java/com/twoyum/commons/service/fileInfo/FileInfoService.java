package com.twoyum.commons.service.fileInfo;

import com.twoyum.commons.domain.FileInfoInDto;
import com.twoyum.commons.domain.FileInfoOutDto;

import java.util.List;

/**
 * 文件服务层接口
 */
public interface FileInfoService {

    /**
     * 文件保存信息
     * @param fileInfoInDto 输入对象
     * @return 返回结果
     */
    int insertFileInfo(FileInfoInDto fileInfoInDto);

    /**
     * 获取单文件信息
     * @param fileInfoInDto 输入对象
     * @return 返回文件对象
     */
    FileInfoOutDto getFileInfo(FileInfoInDto fileInfoInDto);

    /**
     * 获取多个文件信息
     * @param fileInfoInDto 输入对象
     * @return 返回多文件信息
     */
    List<FileInfoOutDto> getFileInfoList(FileInfoInDto fileInfoInDto);
}
