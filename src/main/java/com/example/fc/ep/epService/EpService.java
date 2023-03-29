package com.example.fc.ep.epService;

import com.example.fc.ep.epDao.EPDao;
import com.example.fc.ep.epVo.EpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpService {

    @Autowired
    EPDao epDao;

    public List<EpVo>  doEPList(){
    List<EpVo> list= new ArrayList<>();
    list =epDao.doEPList();
    return list;
    }
    public void  EPjoin(EpVo epVo){
        epDao.epJoin(epVo);
    }

    public  EpVo Login(EpVo epVo){
        return  epDao.epLogin(epVo);
    }
}
