package com.briup.product_source.dao.basic;

import com.briup.product_source.bean.basic.IssueRecord;

public interface IssueRecordMapper {
    int deleteByPrimaryKey(Integer isrId);

    int insert(IssueRecord record);

    int insertSelective(IssueRecord record);

    IssueRecord selectByPrimaryKey(Integer isrId);

    int updateByPrimaryKeySelective(IssueRecord record);

    int updateByPrimaryKey(IssueRecord record);
}