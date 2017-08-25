package io.github.mysar.blog.service;


import io.github.mysar.blog.common.entity.RespError;
import io.github.mysar.blog.vo.Pager;
import io.github.mysar.blog.vo.Partner;
import io.github.mysar.blog.vo.WebDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Im.Yan on 2017/8/24.
 * 描述:
 */
public interface WebDtoService {

    List<WebDto> getAll();

    RespError putExcel(HttpServletRequest request, HttpServletResponse response);

    void initPage(Pager pager);

    List<WebDto> loadWebDto(Pager pager, String param);
}
