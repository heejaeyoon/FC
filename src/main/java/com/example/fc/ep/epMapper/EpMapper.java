package com.example.fc.ep.epMapper;

import com.example.fc.ep.epVo.EpVO;
import org.apache.ibatis.annotations.Insert;

//@Mapper
public interface EpMapper {

    @Insert("insert into ep_recruit(title, stack, period, recruit, gender, payment, work_day, addr, detail, ep_name, create_date, update_date) values (" +
            "#{title}, #{stack}, #{period}, #{recruit}, #{gender}, #{payment}, #{work_day}, #{addr}, #{detail}, #{ep_name}, now(), now() )")
    void save(EpVO epRecruitVO);



}
