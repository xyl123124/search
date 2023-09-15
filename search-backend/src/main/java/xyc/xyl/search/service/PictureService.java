package xyc.xyl.search.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyc.xyl.search.model.entity.Picture;

/**
 * 图片服务
 *
 * @author lacy
 */
public interface PictureService {
    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);

}
