package xyc.xyl.search.model.dto.post;

import xyc.xyl.search.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 *
 * @author lacy
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long notId;

    /**
     * 搜索词
     */
    @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String searchText;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 至少有一个标签
     */
    private List<String> orTags;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 收藏用户 id
     */
    private Long favourUserId;

    private static final long serialVersionUID = 1L;
}
