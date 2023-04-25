package com.example.fc.epRecruit.epRecruitDao;

import com.example.fc.epRecruit.epRecruitVo.EpRecruitFilesVo;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitLeftJoinMainThumbnailVO;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface EpRecruitDao {

//  기업게시판 리스트
    public List<EpRecruitVO> epList();

//    기업 구인 등록
    public int epRecruitSave(EpRecruitVO epRecruitVO);

//    기업 구인 상세페이지
    public EpRecruitVO epRecruitFindById(Long epBoard);

//   기업 구인게시판 글쓴이 정보
    public HashMap<String, Object> epFindById(Long epId);

//    기업 > 유저가 마지막으로 등록한 모집 공고인데 유저정보를 받아와야해서 현재 반쪽짜리 기능이다
    public Long epRecruitLastId();

//    기업게시판 + 썸네일 리스트
    public List<EpRecruitLeftJoinMainThumbnailVO> epRecruitMainList();

}
