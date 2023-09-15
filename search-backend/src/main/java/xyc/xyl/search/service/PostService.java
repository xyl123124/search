package xyc.xyl.search.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyc.xyl.search.model.dto.post.PostQueryRequest;
import xyc.xyl.search.model.entity.Post;
import xyc.xyl.search.model.vo.PostVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author lacy
* @description 针对表【post(帖子)】的数据库操作Service
* @createDate 2023-07-28 22:28:38
*/
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);

    /**
     * 从 ES 查询
     *
     * @param postQueryRequest
     * @return
     */
    Page<Post> searchFromEs(PostQueryRequest postQueryRequest);

    /**
     * 获取帖子封装
     *
     * @param post
     * @param request
     * @return
     */
    PostVO getPostVO(Post post, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param postPage
     * @param request
     * @return
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage, HttpServletRequest request);

    /**
     * 分页查询帖子
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    Page<PostVO> listPostVOByPage(PostQueryRequest postQueryRequest, HttpServletRequest request);
}
