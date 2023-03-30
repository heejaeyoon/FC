package com.example.fc.epRecruit.epRecruitDao;

import com.example.fc.epRecruit.epRecruitVo.EpRecruitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EpRecruitDao {

    public List<EpRecruitVO> epList();

    public int save(EpRecruitVO epRecruitVO);

    public EpRecruitVO epFindById(Long epBoard);

    public Long epRecruitLastId();
}
