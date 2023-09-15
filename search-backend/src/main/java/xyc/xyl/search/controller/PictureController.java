package xyc.xyl.search.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyc.xyl.search.common.BaseResponse;
import xyc.xyl.search.common.ErrorCode;
import xyc.xyl.search.common.Result;
import xyc.xyl.search.exception.ThrowUtils;
import xyc.xyl.search.model.dto.picture.PictureQueryRequest;
import xyc.xyl.search.model.entity.Picture;
import xyc.xyl.search.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子接口
 *
 * @author lacy
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;


    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                         HttpServletRequest request) {
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        String searchText = pictureQueryRequest.getSearchText();
        Page<Picture> picturePage = pictureService.searchPicture(searchText, current, size);
        return Result.success(picturePage);
    }




}
