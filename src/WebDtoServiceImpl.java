package io.github.mysar.blog.service.impl;

import io.github.mysar.blog.common.entity.RespError;
import io.github.mysar.blog.common.error.ErrorRespGenUtil;
import io.github.mysar.blog.common.errorcode.ErrorCode;
import io.github.mysar.blog.common.errorcode.ParamCode;
import io.github.mysar.blog.mapper.WebDtoMapper;
import io.github.mysar.blog.service.WebDtoService;
import io.github.mysar.blog.common.excel.PoiExcelExport;
import io.github.mysar.blog.vo.Pager;
import io.github.mysar.blog.vo.WebDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Im.Yan on 2017/8/24.
 * 描述:
 */
@Service
public class WebDtoServiceImpl implements WebDtoService {


    @Resource
    private WebDtoMapper webDtoMapper;

    @Override
    public List<WebDto> getAll() {
        return webDtoMapper.getAll();
    }

    @Override
    public RespError putExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            /**获取参数,定义文件名称*/

         //   int isPay = Integer.parseInt(request.getParameter("isPay"));
         //   String fileName = isPay == 1 ? "已缴费学生信息" : "未缴费学生信息";
            String fileName = "WebDto统计信息";  // 定义xls文件名
            //   Criteria criteria = new Criteria();
         //   criteria.put("isPay", isPay);
            /**查询数据库*/


            List<WebDto>  webDtoList = webDtoMapper.getAll();
          //  List<WebDto> list = new ArrayList<WebDto>(webDto);

            /**设置属性*/
            PoiExcelExport excelExport = new PoiExcelExport(response, fileName, "sheet1");
            String titleColumn[] = {"name", "url", "username", "password", "readCount"};
            String titleName[] = {"姓名", "url", "用户名", "密码", "点击数"};
            int titleSize[] = {10, 10, 20, 20, 20};
            excelExport.wirteExcel(titleColumn, titleName, titleSize, webDtoList);
            System.out.println(webDtoList);
        } catch (Exception e) {
            return ErrorRespGenUtil.generateErrorResp(ParamCode.PARAM_ERR,"导出失败-检查参数名是否正确");
           // System.out.println("导出失败!");
        }
        return ErrorRespGenUtil.generateErrorResp(ErrorCode.NORMAL_CODE,"success");
    }


    @Override
    public void initPage(Pager pager) {
        int count = webDtoMapper.initPage(pager);
        pager.setTotalCount(count);
    }

    @Override
    public List<WebDto> loadWebDto(Pager pager, String param) {
        pager.setStart(pager.getStart());
        return webDtoMapper.loadWebDto(pager,param);
    }
}


