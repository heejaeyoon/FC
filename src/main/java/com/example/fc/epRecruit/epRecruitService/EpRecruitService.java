package com.example.fc.epRecruit.epRecruitService;

import com.example.fc.epRecruit.epRecruitDao.EpRecruitDao;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EpRecruitService {
    private final EpRecruitDao epDao;

    public int epRecruitSave(EpRecruitVO epRecruitVO, HttpSession session) {
        int res = epDao.save(epRecruitVO);
            if (res == 1) {
                session.getAttribute("epLogin");
                System.out.println(session.getAttribute("epLogin"));
            }

        return res;
    }

    public List<EpRecruitVO> epRecruitList() {
        return epDao.epList();
    }

    public EpRecruitVO epRecruitFindOne(Long epBoard) {
        return epDao.epFindById(epBoard);
    }

    public Long epRecruitLastId() {
        return epDao.epRecruitLastId();
    }

}
