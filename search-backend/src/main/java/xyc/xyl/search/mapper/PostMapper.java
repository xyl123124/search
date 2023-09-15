package xyc.xyl.search.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyc.xyl.search.model.entity.Post;

import java.util.Date;
import java.util.List;

/**
* @author lacy
* @description 针对表【post(帖子)】的数据库操作Mapper
* @createDate 2023-07-28 22:28:38
* @Entity generator.domain.Post
*/
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}




