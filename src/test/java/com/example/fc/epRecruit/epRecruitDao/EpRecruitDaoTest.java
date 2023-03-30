package com.example.fc.epRecruit.epRecruitDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EpRecruitDaoTest {

    @Autowired
    EpRecruitDao recruitDao;

    @Test
    public void LastIdTest() {
        Long res = recruitDao.epRecruitLastId();
        System.out.println(res);
    }

}