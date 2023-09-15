package xyc.xyl.search.model.dto.picture;

import xyc.xyl.search.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class PictureQueryRequest extends PageRequest implements Serializable {

    /**
     * 搜索词
     */
    private String searchText;

    private static final long serialVersionUID = 1L;
}
