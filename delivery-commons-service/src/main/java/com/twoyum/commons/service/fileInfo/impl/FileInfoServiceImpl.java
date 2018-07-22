package com.twoyum.commons.service.fileInfo.impl;

import com.twoyum.commons.domain.FileInfoInDto;
import com.twoyum.commons.domain.FileInfoOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twoyum.commons.mapper.FileInfoMapper;
import com.twoyum.commons.service.fileInfo.FileInfoService;

import java.util.List;

/**
 * 文件服务层接口实现
 */
@Service(value = "fileInfoService")
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public int insertFileInfo(FileInfoInDto fileInfoInDto) {
        return fileInfoMapper.insertFileInfo(fileInfoInDto);
    }

    @Override
    public FileInfoOutDto getFileInfo(FileInfoInDto fileInfoInDto) {
        return fileInfoMapper.getFileInfo(fileInfoInDto);
    }

    @Override
    public List<FileInfoOutDto> getFileInfoList(FileInfoInDto fileInfoInDto) {
        return fileInfoMapper.getFileInfoList(fileInfoInDto);
    }

  /*  *//*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * *//*
    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }*/
}
