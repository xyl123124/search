package xyc.xyl.search.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyc.xyl.search.model.dto.user.UserQueryRequest;
import xyc.xyl.search.model.vo.UserVO;
import xyc.xyl.search.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserDataSource implements DataSource<UserVO> {

    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(pageNum);
        userQueryRequest.setPageSize(pageSize);
        return userService.listUserVOByPage(userQueryRequest);
    }

}
