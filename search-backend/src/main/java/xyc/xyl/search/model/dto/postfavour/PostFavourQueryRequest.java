package xyc.xyl.search.model.dto.postfavour;

import xyc.xyl.search.common.PageRequest;
import xyc.xyl.search.model.dto.post.PostQueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 帖子收藏查询请求
 *
 * @author lacy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostFavourQueryRequest extends PageRequest implements Serializable {

    /**
     * 帖子查询请求
     */
    private PostQueryRequest postQueryRequest;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}
