package com.example.fc.epRecruit.epRecruitController;

import com.example.fc.epRecruit.epRecruitDao.EpRecruitDao;
import com.example.fc.epRecruit.epRecruitService.EpRecruitService;
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
    private final EpRecruitService epRecruitService;
    @GetMapping("/epRecruitForm")
    public String recruitForm() {
        return "/epRecruit/epRecruitForm";
    }

    @PostMapping("/epRecruitAction")
    public String epRecruitAction(EpRecruitVO epRecruitVO) {
        int res = epRecruitService.epRecruitSave(epRecruitVO);

        if (res == 1) {
            return "redirect:/epRecruit/epRecruitActionSuccess";
        } else {

        }
        return "redirect:/epRecruit/epRecruitForm";
    }

    @GetMapping("epRecruitActionSuccess")
    public String epRecruitActionSuccess(Model model) {
        Long epRecruitLastId = epRecruitService.epRecruitLastId();
        model.addAttribute("epRecruitLastId", epRecruitLastId);
        return "/epRecruit/epRecruitActionSuccess";
    }

    @GetMapping("/epRecruitList")
    public String EpRecruitList(Model model) {
        List<EpRecruitVO> epRecruitList = epRecruitService.epRecruitList();

        model.addAttribute("epList", epRecruitList);
        return "/epRecruit/epRecruitList";
    }

    @GetMapping("epBoard")
    public String getEpBoard(Model model, Long epBoard) {
        EpRecruitVO epRecruitFindOne = epRecruitService.epRecruitFindOne(epBoard);

        model.addAttribute("epRecruit", epRecruitFindOne);
        return "epRecruit/epRecruitPoster";
    }
}
