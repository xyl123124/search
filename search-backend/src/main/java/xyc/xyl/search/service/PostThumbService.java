package xyc.xyl.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyc.xyl.search.model.entity.PostThumb;
import xyc.xyl.search.model.entity.User;

/**
* @author lacy
* @description 针对表【post_thumb(帖子点赞)】的数据库操作Service
* @createDate 2023-07-28 22:28:39
*/
public interface PostThumbService extends IService<PostThumb> {

    /**
     * 点赞
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostThumb(long postId, User loginUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostThumbInner(long userId, long postId);

}
