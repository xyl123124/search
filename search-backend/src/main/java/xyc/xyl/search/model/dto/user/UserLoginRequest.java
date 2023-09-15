package xyc.xyl.search.model.dto.user;

import lombok.Data;

/**
 * 用户登录请求
 * @author lacy
 */
@Data
public class UserLoginRequest {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;
}
