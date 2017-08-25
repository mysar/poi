package io.github.mysar.blog.controller;

import io.github.mysar.blog.service.*;
import io.github.mysar.blog.vo.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WebDtoController {

    @Resource
    private WebDtoService webDtoService;

    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager){
        webDtoService.initPage(pager);
        return pager;

    }

    @RequestMapping(value = "/admin/putExcel", method = RequestMethod.GET)
    public void putOutFinanceExcel(HttpServletRequest request, HttpServletResponse response) {
        webDtoService.putExcel(request, response);
    }

}
