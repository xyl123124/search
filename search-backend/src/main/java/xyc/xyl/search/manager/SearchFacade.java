package xyc.xyl.search.manager;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyc.xyl.search.common.ErrorCode;
import xyc.xyl.search.datasource.*;
import xyc.xyl.search.exception.BusinessException;
import xyc.xyl.search.exception.ThrowUtils;
import xyc.xyl.search.model.dto.search.SearchRequest;
import xyc.xyl.search.model.entity.Picture;
import xyc.xyl.search.model.enums.SearchTypeEnum;
import xyc.xyl.search.model.vo.PostVO;
import xyc.xyl.search.model.vo.SearchVO;
import xyc.xyl.search.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class SearchFacade {

    @Resource
    private PostDataSource postDataSource;

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PictureDataSource pictureDataSource;

    @Resource
    private DataSourceRegistry dataSourceRegistry;

    public SearchVO searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        ThrowUtils.throwIf(StringUtils.isBlank(type), ErrorCode.PARAMS_ERROR);
        String searchText = searchRequest.getSearchText();
        long current = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();
        // type为空或者type不在tab范围之内，搜索出所有数据
        if (searchTypeEnum == null) {
            CompletableFuture<Page<UserVO>> userTask = CompletableFuture.supplyAsync(() ->
                    userDataSource.doSearch(searchText, current, pageSize));
            CompletableFuture<Page<PostVO>> postTask = CompletableFuture.supplyAsync(() ->
                    postDataSource.doSearch(searchText, current, pageSize));
            CompletableFuture<Page<Picture>> pictureTask = CompletableFuture.supplyAsync(() ->
                    pictureDataSource.doSearch(searchText, 1, 10));
            CompletableFuture.allOf(userTask, postTask, pictureTask).join();
            try {
                Page<UserVO> userVOPage = userTask.get();
                Page<PostVO> postVOPage = postTask.get();
                Page<Picture> picturePage = pictureTask.get();
                SearchVO searchVO = new SearchVO();
                searchVO.setUserList(userVOPage.getRecords());
                searchVO.setPostList(postVOPage.getRecords());
                searchVO.setPictureList(picturePage.getRecords());
                return searchVO;
            } catch (Exception e) {
                log.error("查询异常", e);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "查询异常");
            }
        } else {
            SearchVO searchVO = new SearchVO();
            DataSource<?> dataSource = dataSourceRegistry.getDataSourceByType(type);
            Page<?> page = dataSource.doSearch(searchText, current, pageSize);
            searchVO.setDataList(page.getRecords());
            System.out.println("运行结果1"+searchVO);
            return searchVO;
        }
    }

}
