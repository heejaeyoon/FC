package com.example.fc.support.supportDao;


import com.example.fc.support.supportVo.EpOneToOneVo;
import com.example.fc.support.supportVo.MemberOneToOneVo;
import com.example.fc.support.supportVo.SupportVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EpOneToOneDao {

    //    회사 일대일 등록
    int epPersonalInquire(EpOneToOneVo epOneToOneVo);

}
