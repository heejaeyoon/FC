package com.example.fc.epRecruit.epRecruitController;

import com.example.fc.epRecruit.epRecruitDao.EpRecruitDao;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/epRecruit")
@RequiredArgsConstructor
@Log4j2
public class EpRecruitController {
    final private EpRecruitDao epDao;
    @GetMapping("/epRecruitForm")
    public String recruitForm() {
        return "/epRecruit/epRecruitForm";
    }

    @PostMapping("/epRecruitAction")
    public String epRecruitAction(EpRecruitVO epVO) {
        log.info("구인등록 액션!!" + epVO);
        epDao.save(epVO);
        return "redirect:/epRecruit/epRecruitForm";
    }

    @GetMapping("/epRecruitList")
    public String EpRecruitList(Model model) {
        List<EpRecruitVO> epVOList = epDao.epList();
        epVOList.forEach(i -> {
            log.info("--------->" + i);
        });

        model.addAttribute("epList", epVOList);
//        epRecruitMapper
        return "/epRecruit/epRecruitList";
    }

    @GetMapping("epBoard")
    public String getEpBoard(Model model, Long epBoard) {
        System.out.println("epBoard>>>>" + epBoard);
        EpRecruitVO epRecruitOne = epDao.epFindById(epBoard);

        model.addAttribute("epRecruit", epRecruitOne);
        return "epRecruit/epRecruitPoster";
    }
}
