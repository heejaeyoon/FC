package com.example.fc.service;

import com.example.fc.dao.MemberDao;
import com.example.fc.vo.Vo_member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;

    public List<Vo_member> doMemberList() {
        List<Vo_member> list = new ArrayList<>();
        list = memberDao.doMemberList();
        return list;
    }
}
