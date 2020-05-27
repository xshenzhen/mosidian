package com.mosidian.web.service.impl.sys;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.common.security.JwtTokenUtil;
import com.mosidian.web.dao.sys.UserMapper;
import com.mosidian.web.model.mms.Member;
import com.mosidian.web.model.sys.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mosidian.web.service.mms.MemberService;
import com.mosidian.web.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public IPage<User> listAndPage(Page<User> page, String username) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(username),"memo",username)
                .eq("recordstatus",true);
        return userMapper.userList(page,wrapper);
    }

    @Override
    public int register(String userNo, String password, String memo) {


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("no", userNo);
        Member member = memberService.getOne(queryWrapper);
        if (!ObjectUtil.isNotNull(member)) {
            return 2;
        }
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",userNo);
        User model = userMapper.selectOne(wrapper);
        if (ObjectUtil.isNotNull(model)){
            return 3;
        }
        User user = new User();
        user.setUsername(userNo);
        user.setPassword(passwordEncoder.encode(password));
        user.setMemo(memo);
        user.setRoleid(2);
        user.setPhone(member.getPhone());
        user.setStatus(0);
        user.setCreatedate(new Date());
        user.setIsLogin(0);
        return userMapper.insert(user);
    }

    @Override
    public boolean checkUserStatus(String id,String message,String status) {
        User user = userMapper.selectById(id);
        user.setStatus(Convert.toInt(status));
        user.setMessage(message);
        int update = userMapper.updateById(user);
        if (update>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean activation(String id) {
        User user = userMapper.selectById(id);
        user.setIsLogin(1);
        /**@Desc: 已激活 */
        user.setStatus(3);
        int flag=userMapper.updateById(user);
        if (flag>0){
            return true;
        }else {
            return false;
        }

    }
}
