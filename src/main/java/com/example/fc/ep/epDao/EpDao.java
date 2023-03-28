package com.example.fc.ep.epDao;

import com.example.fc.ep.epVo.EpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EpDao {

    public List<EpVO> epList();

    public int save(EpVO epRecruitVO);
}
