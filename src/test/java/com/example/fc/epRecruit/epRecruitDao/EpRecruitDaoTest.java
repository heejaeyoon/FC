package com.example.fc.epRecruit.epRecruitDao;

import com.example.fc.epRecruit.epRecruitVo.EpRecruitLeftJoinMainThumbnailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EpRecruitDaoTest {

    @Autowired
    EpRecruitDao epRecruitDao;

    @Test
    public void LastIdTest() {
        Long res = epRecruitDao.epRecruitLastId();
        System.out.println(res);
    }

    @Test
    void epListTest() {
        List<EpRecruitLeftJoinMainThumbnailVO> lst = epRecruitDao.epRecruitMainList();
        for ( EpRecruitLeftJoinMainThumbnailVO ep : lst.subList(1, 20)) {
            System.out.println(ep);
        }
    }
}