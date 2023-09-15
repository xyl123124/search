package xyc.xyl.search.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyc.xyl.search.model.dto.post.PostQueryRequest;
import xyc.xyl.search.model.entity.Post;
import xyc.xyl.search.model.vo.PostVO;
import xyc.xyl.search.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(pageNum);
        postQueryRequest.setPageSize(pageSize);
//        todo： 目前状态无法获取用户点赞、收藏数 不过暂时不需要
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
//        return postService.getPostVOPage(postPage, request);
        return postService.getPostVOPage(postPage, null);
    }

}
