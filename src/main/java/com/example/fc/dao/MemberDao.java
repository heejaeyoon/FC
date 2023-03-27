package com.example.fc.dao;

import com.example.fc.vo.Vo_member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    public List<Vo_member> doMemberList();

    public int memberJoin(Vo_member vo_member);
}
