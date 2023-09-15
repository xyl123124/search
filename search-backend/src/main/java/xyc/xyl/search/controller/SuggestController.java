package xyc.xyl.search.controller;

import xyc.xyl.search.common.BaseResponse;
import xyc.xyl.search.common.Result;
import xyc.xyl.search.service.SuggestService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 搜索建议
 *
 * @author lacy
 */
@Slf4j
@RestController
@RequestMapping("/suggest")
public class SuggestController {
    @Resource
    private SuggestService suggestService;

    @GetMapping("/post")
    public BaseResponse<List<String>> searchAll(String pre) throws IOException {

        return Result.success(suggestService.suggest(pre));
    }
    }
