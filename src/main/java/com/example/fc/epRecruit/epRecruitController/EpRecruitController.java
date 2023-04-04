package com.example.fc.epRecruit.epRecruitController;

import com.example.fc.epRecruit.epRecruitDao.EpRecruitDao;
import com.example.fc.epRecruit.epRecruitService.EpRecruitService;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitVO;
import com.example.fc.pageNation.pageVo.PageNationVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/epRecruit")
@RequiredArgsConstructor
@Log4j2
public class EpRecruitController {
  private final EpRecruitService epRecruitService;

  @GetMapping("/epRecruitForm")
  public String recruitForm(HttpSession session) {
    if (session.getAttribute("epLogin") == null) {
      return "redirect:/login";
    } else {
      return "/epRecruit/epRecruitForm";
    }

  }

  @PostMapping("/epRecruitActionAjax")
  @ResponseBody
  public int epRecruitSave(EpRecruitVO epRecruitVO, HttpSession session) {
    int res = epRecruitService.epRecruitSave(epRecruitVO, session);

    return res;
  }

  @GetMapping("epRecruitActionSuccess")
  public String epRecruitActionSuccess(Model model) {
    Long epRecruitLastId = epRecruitService.epRecruitLastId();
    model.addAttribute("epRecruitLastId", epRecruitLastId);
    return "/epRecruit/epRecruitActionSuccess";
  }

  @GetMapping("/epRecruitList")
  public String EpRecruitList(Model model, @PageableDefault(page = 1, size = 10) Pageable pageable) {
    List<EpRecruitVO> epRecruitList = epRecruitService.epRecruitList();

    final int start = (int) pageable.getOffset();
    final int end = Math.min((start + pageable.getPageSize()), epRecruitList.size());
    System.out.println(pageable.next());
    System.out.println(pageable.previousOrFirst());

    final Page<EpRecruitVO> page = new PageImpl<>(epRecruitList.subList(start, end), pageable, epRecruitList.size());

    model.addAttribute("epList", page);
    return "/epRecruit/epRecruitList";
  }

  @GetMapping("epBoard")
  public String getEpBoard(Model model, Long epBoard) {
    EpRecruitVO epRecruitFindOne = epRecruitService.epRecruitFindOne(epBoard);

    model.addAttribute("epRecruit", epRecruitFindOne);
    return "epRecruit/epRecruitPoster";
  }
}
