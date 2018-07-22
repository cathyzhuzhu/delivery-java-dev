package com.twoyum.commons.mapper;

import com.twoyum.commons.domain.FileInfoInDto;
import com.twoyum.commons.domain.FileInfoOutDto;
import java.util.List;

/**
 * 文件DAO层
 */
public interface FileInfoMapper {
    int insertFileInfo(FileInfoInDto record);
    FileInfoOutDto getFileInfo(FileInfoInDto fileInfoInDto);
    List<FileInfoOutDto> getFileInfoList(FileInfoInDto fileInfoInDto);
}