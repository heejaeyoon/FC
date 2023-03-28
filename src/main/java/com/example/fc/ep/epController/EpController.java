package com.example.fc.ep.epController;

import com.example.fc.ep.epDao.EpDao;
import com.example.fc.ep.epMapper.EpMapper;
import com.example.fc.ep.epVo.EpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ep")
@RequiredArgsConstructor
@Log4j2
public class EpController {
    final private EpDao epDao;
    @GetMapping("/epRecruitForm")
    public String recruitForm() {
        return "/ep/epRecruitForm";
    }

    @PostMapping("/epRecruitAction")
    public String epRecruitAction(EpVO epVO) {
        log.info("구인등록 액션!!" + epVO);
        epDao.save(epVO);
        return "redirect:/ep/epRecruitForm";
    }

    @GetMapping("/epRecruitList")
    public String EpRecruitList(Model model) {
//        epRecruitMapper
        return "";
    }
}
