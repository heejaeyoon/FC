package com.example.fc.support.supportDao;


import com.example.fc.support.supportVo.EpOneToOneVo;
import com.example.fc.support.supportVo.SupportVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EpOneToOneDao {

    //    회사 일대일 등록
    public int epPersonalInquire(EpOneToOneVo epOneToOneVo);
    //내가 문의한 1대1문의 숫자 조회
    public  int epOneToOneCount(String epOneToOneVo);
    public  List<EpOneToOneVo> epOneToOneFindEmail(String epOneToOneVo);

    EpOneToOneVo oneToOneList(int id);
}
