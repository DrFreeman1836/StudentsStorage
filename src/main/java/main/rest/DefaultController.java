package main.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import main.service.impl.StudentManagerServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class DefaultController {

  StudentManagerServiceImpl studentManagerService;

  @RequestMapping()
  public String index(Model model) {
    model.addAttribute("listStudent", studentManagerService.getListStudents());
    return "index";
  }

}
