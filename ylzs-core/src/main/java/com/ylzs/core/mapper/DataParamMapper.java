package com.ylzs.core.mapper;

import com.ylzs.core.model.DataParam;

public interface DataParamMapper {
    int deleteByPrimaryKey(String id);

    int insert(DataParam record);

    int insertSelective(DataParam record);

    DataParam selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataParam record);

    int updateByPrimaryKey(DataParam record);
}