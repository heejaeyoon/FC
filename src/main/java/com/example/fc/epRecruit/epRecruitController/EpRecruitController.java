package com.example.fc.epRecruit.epRecruitController;

import com.example.fc.epRecruit.epRecruitDao.EpRecruitDao;
import com.example.fc.epRecruit.epRecruitService.EpRecruitService;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitFilesVo;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitLeftJoinMainThumbnailVO;
import com.example.fc.epRecruit.epRecruitVo.EpRecruitMainThumbnailVo;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    if (session.getAttribute("epLogin") != null) {
      return "/epRecruit/epRecruitForm";
    } else {
      return "redirect:/login";
    }

  }

//  구인 게시판 저장
  @PostMapping("/epRecruitAction")
  @ResponseBody
  public String epRecruitSave(EpRecruitVO epRecruitVO,@RequestParam("file") MultipartFile[] files, HttpSession session) throws IOException {
    int res = epRecruitService.epRecruitSave(epRecruitVO, files, session);



    return "redirect:/epRecruit/epRecruitForm";
  }

//  @PostMapping("/epRecruitActionAjax")
//  @ResponseBody
//  public int epRecruitSave(EpRecruitVO epRecruitVO, HttpSession session) {
//    int res = epRecruitService.epRecruitSave(epRecruitVO, session);
//
//    return res;
//  }

  @GetMapping("epRecruitActionSuccess")
  public String epRecruitActionSuccess(Model model) {
    Long epRecruitLastId = epRecruitService.epRecruitLastId();
    model.addAttribute("epRecruitLastId", epRecruitLastId);
    return "/epRecruit/epRecruitActionSuccess";
  }

  @GetMapping("/epRecruitList")
  public String EpRecruitList(Model model, @PageableDefault(page = 0, size = 6) Pageable pageable) {
//    List<EpRecruitVO> epRecruitList = epRecruitService.epRecruitList();
    List<EpRecruitLeftJoinMainThumbnailVO> epRecruitList = epRecruitService.epRecruitMainList();

//    getOffset은 현제 페이지 넘버를 알려주는 함수
    final int start = (int) pageable.getOffset();
//    getPageSize() 는 화면에 보여줄 리스트 수
    final int end = Math.min(start + pageable.getPageSize(), epRecruitList.size());
//    System.out.println("epRecruitList.size() == " + epRecruitList.size());

    final Page<EpRecruitLeftJoinMainThumbnailVO> page = new PageImpl<>(epRecruitList.subList(start, end), pageable, epRecruitList.size());

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
