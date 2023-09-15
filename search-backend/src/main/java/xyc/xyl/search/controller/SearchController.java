package xyc.xyl.search.controller;

import xyc.xyl.search.common.BaseResponse;
import xyc.xyl.search.common.Result;
import xyc.xyl.search.manager.SearchFacade;
import xyc.xyl.search.model.dto.search.SearchRequest;
import xyc.xyl.search.model.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        System.out.println("运行结果" + Result.success(searchFacade.searchAll(searchRequest, request)));
        return Result.success(searchFacade.searchAll(searchRequest, request));
    }

}
