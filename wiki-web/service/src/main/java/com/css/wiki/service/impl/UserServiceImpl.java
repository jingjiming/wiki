package com.css.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.common.beans.enums.ResultCode;
import com.css.common.exceptions.GenericBusinessException;
import com.css.common.util.CopyUtil;
import com.css.common.util.StringUtils;
import com.css.wiki.dto.EbookQueryDTO;
import com.css.wiki.dto.UserLoginDTO;
import com.css.wiki.dto.UserQueryDTO;
import com.css.wiki.dto.UserResetPasswordDTO;
import com.css.wiki.entity.Ebook;
import com.css.wiki.entity.User;
import com.css.wiki.mapper.UserMapper;
import com.css.wiki.service.UserService;
import com.css.wiki.vo.UserLoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author jiming.jing
 * @since 2023/02/01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public Page<User> findByPage(UserQueryDTO dto) {
        QueryWrapper<User> qw = new QueryWrapper<User>()
                .like(StringUtils.isNotBlank(dto.getLoginName()), "login_name", dto.getLoginName());
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        return this.page(page, qw);
    }

    @Override
    public User findByLoginName(String loginName) {
        QueryWrapper<User> qw = new QueryWrapper<User>()
                .eq("login_name", loginName);

        return this.getOne(qw);
    }

    @Override
    public boolean resetPassword(UserResetPasswordDTO dto) {
        User user = CopyUtil.copy(dto, User.class);
        UpdateWrapper<User> uw = new UpdateWrapper<User>()
                .set("password", user.getPassword())
                .eq("id", user.getId());
        return this.update(uw);
    }

    @Override
    public UserLoginVO login(UserLoginDTO dto) {
        User po = findByLoginName(dto.getLoginName());
        if (ObjectUtils.isEmpty(po)) {
            // 用户名不存在
            this.log.info("用户名不存在,{}", dto.getLoginName());
            throw new GenericBusinessException(ResultCode.LOGIN_USER_ERROR);
        } else {
            if (po.getPassword().equals(dto.getPassword())) {
                // 登录成功
                UserLoginVO vo = CopyUtil.copy(po, UserLoginVO.class);
                return vo;
            } else {
                // 密码不对
                this.log.info("密码不对,输入密码:{}, 数据库密码:{}", dto.getLoginName());
                throw new GenericBusinessException(ResultCode.LOGIN_USER_ERROR);
            }
        }
    }
}
