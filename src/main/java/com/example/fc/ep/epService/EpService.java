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
    
    public void epModify(EpVo epVo){
        epDao.epModify(epVo);
    }

        public  int epDelete(EpVo epVo){
            return epDao.epDelete(epVo);}

    public int idCheck(EpVo epVo) throws Exception{
        System.out.println("epVo service = " + epVo);
        int result = epDao.idCheck(epVo);
        return result;
    }
    public EpVo epPasswordCheck(EpVo epVo){return  epDao.epPasswordCheck(epVo);}
    public EpVo epEmailCheck(EpVo epVo){return  epDao.epEmailCheck(epVo);
    }

}