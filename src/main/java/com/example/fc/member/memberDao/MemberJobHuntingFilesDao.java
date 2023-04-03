package com.example.fc.member.memberDao;

import com.example.fc.member.memberVo.MemberJobHuntingFilesVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberJobHuntingFilesDao {

    int insertJobHuntingFiles(MemberJobHuntingFilesVo memberJobHuntingFilesVo);
}
