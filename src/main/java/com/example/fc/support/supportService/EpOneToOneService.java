package com.example.fc.support.supportService;

import com.example.fc.support.supportDao.EpOneToOneDao;
import com.example.fc.support.supportVo.EpOneToOneVo;
import com.example.fc.support.supportVo.SupportVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpOneToOneService {

    private final EpOneToOneDao epOneToOneDao;


    //    회사 일대일등록
    public void epPersonalInquire(EpOneToOneVo epOneToOneVo) {
        epOneToOneDao.epPersonalInquire(epOneToOneVo);
    }
    public int epOneToOneCount(String epOneToOneVo){
        int count = epOneToOneDao.epOneToOneCount(epOneToOneVo);
      return count;
    }
   public List<EpOneToOneVo> epOneToOneFindEmail(String epOneToOneVo){
        List<EpOneToOneVo> list = epOneToOneDao.epOneToOneFindEmail(epOneToOneVo);
        return list;
    }
    //    1대1문의 상세 조회
    public EpOneToOneVo noticeOneList(int id) {
        return epOneToOneDao.oneToOneList(id);
    }

}
