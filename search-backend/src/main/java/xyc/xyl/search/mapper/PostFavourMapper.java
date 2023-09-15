package xyc.xyl.search.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyc.xyl.search.model.entity.Post;
import xyc.xyl.search.model.entity.PostFavour;
import org.apache.ibatis.annotations.Param;


/**
* @author lacy
* @description 针对表【post_favour(帖子收藏)】的数据库操作Mapper
* @createDate 2023-07-28 22:28:39
* @Entity generator.domain.PostFavour
*/
public interface PostFavourMapper extends BaseMapper<PostFavour> {
    /**
     * 分页查询收藏帖子列表
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<Post> listFavourPostByPage(IPage<Post> page, @Param(Constants.WRAPPER) Wrapper<Post> queryWrapper,
                                    long favourUserId);

}




