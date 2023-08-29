package com.css.wiki.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.common.beans.enums.ResultCode;
import com.css.common.beans.response.JsonResult;
import com.css.common.exceptions.GenericBusinessException;
import com.css.common.util.CopyUtil;
import com.css.common.util.SnowFlake;
import com.css.common.util.redis.JedisUtil;
import com.css.wiki.dto.UserAddDTO;
import com.css.wiki.dto.UserLoginDTO;
import com.css.wiki.dto.UserQueryDTO;
import com.css.wiki.dto.UserResetPasswordDTO;
import com.css.wiki.entity.User;
import com.css.wiki.service.UserService;
import com.css.wiki.vo.UserLoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author jiming.jing
 * @since 2023/02/01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    SnowFlake snowFlake;
    @Autowired
    JedisUtil jedisUtil;

    @GetMapping("/list")
    public JsonResult<User> list(@Valid UserQueryDTO dto) {
        Page<User> list = this.userService.findByPage(dto);
        return JsonResult.ok().data(list);
    }

    @PostMapping("/add")
    public JsonResult add(@Valid @RequestBody UserAddDTO dto) {
        boolean flag = false;
        User user = CopyUtil.copy(dto, User.class);
        if (user.getId() == null) {
            User po = this.userService.findByLoginName(user.getLoginName());
            if (ObjectUtils.isEmpty(po)) {
                user.setId(this.snowFlake.nextId());
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                flag = this.userService.save(user);
            } else {
                throw new GenericBusinessException(ResultCode.USER_EXISTS);
            }
        } else {
            user.setLoginName(null);
            flag = this.userService.updateById(user);
        }
        if (flag) {
            return JsonResult.ok();
        }
        return JsonResult.badRequest();
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult delete(@PathVariable Long id) {
        this.userService.removeById(id);
        return JsonResult.ok();
    }

    @PostMapping("/reset-password")
    public JsonResult resetPassword(@Valid @RequestBody UserResetPasswordDTO dto) {
        boolean flag = false;
        dto.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        flag = this.userService.resetPassword(dto);
        if (flag) {
            return JsonResult.ok();
        }
        return JsonResult.badRequest();
    }

    @PostMapping("/login")
    public JsonResult<UserLoginVO> login(@Valid @RequestBody UserLoginDTO dto) {
        dto.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        UserLoginVO loginVO = this.userService.login(dto);
        if (!ObjectUtils.isEmpty(loginVO)) {
            Long token = this.snowFlake.nextId();
            this.logger.info("生成单点登录token：{},放入redis中", token);
            loginVO.setToken(token.toString());
            this.jedisUtil.addObject("token", token.toString(), 3600 * 24, loginVO);
            return JsonResult.ok().data(loginVO);
        }
        return JsonResult.badRequest();
    }

    @GetMapping("/logout/{token}")
    public JsonResult logout(@PathVariable String token) {
        boolean flag = this.jedisUtil.del("token", token);
        this.logger.info("从redis中删除token:{}", token);
        return JsonResult.ok(flag);
    }
}
