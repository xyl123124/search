package xyc.xyl.search.model.dto.search;

import xyc.xyl.search.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchRequest extends PageRequest implements Serializable {
    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 搜索类型
     */
    private String type;
    private static final long serialVersionUID = 1L;
}
