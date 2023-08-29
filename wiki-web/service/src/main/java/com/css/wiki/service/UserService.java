package com.css.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.wiki.dto.UserLoginDTO;
import com.css.wiki.dto.UserQueryDTO;
import com.css.wiki.dto.UserResetPasswordDTO;
import com.css.wiki.entity.User;
import com.css.wiki.vo.UserLoginVO;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/02/01
 */
public interface UserService extends IService<User> {

    Page<User> findByPage(UserQueryDTO dto);

    User findByLoginName(String loginName);

    boolean resetPassword(UserResetPasswordDTO dto);

    UserLoginVO login(UserLoginDTO dto);
}
