package com.example.fc.support.supportService;

import com.example.fc.support.supportDao.EpOneToOneDao;
import com.example.fc.support.supportDao.MemberOneToOneDao;
import com.example.fc.support.supportVo.EpOneToOneVo;
import com.example.fc.support.supportVo.MemberOneToOneVo;
import com.example.fc.support.supportVo.SupportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpOneToOneService {

    @Autowired
    EpOneToOneDao epOneToOneDao;

    //    회사일대일등록
    public void epPersonalInquire(EpOneToOneVo epOneToOneVo) {
        epOneToOneDao.epPersonalInquire(epOneToOneVo);

    }


}
