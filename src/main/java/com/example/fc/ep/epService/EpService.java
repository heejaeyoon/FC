package com.example.fc.ep.epService;

import com.example.fc.ep.epDao.EpDao;
import com.example.fc.ep.epVo.EpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpService {

    @Autowired
    EpDao epDao;

    public void  epJoin(EpVo epVo){
        epDao.epJoin(epVo);
    }

    public  EpVo epLogin(EpVo epVo){
        return  epDao.epLogin(epVo);
    }
    
    public  int epUpdate(EpVo epVo){
        return epDao.epUpdate(epVo);
    }

        public  int epDelete(EpVo epVo){
            return epDao.epDelete(epVo);}

}