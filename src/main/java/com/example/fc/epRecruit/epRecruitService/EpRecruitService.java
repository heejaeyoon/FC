package com.example.fc.epRecruit.epRecruitService;

import com.example.fc.epRecruit.epRecruitDao.EpRecruitDao;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpRecruitService {
    private final EpRecruitDao epDao;

    public int epRecruitSave(EpRecruitVO epRecruitVO) {
        return epDao.save(epRecruitVO);
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
