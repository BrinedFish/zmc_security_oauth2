package cn.com.megalith.auth;

import cn.com.megalith.domain.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 用户信息的实体类
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 15:46
 */
public class UserDetail implements UserDetails {

    private User user;

    private String id;
    /**
     *通过构造方法在UserDetailsService的方法中将查到的user注入进去
     */
    public UserDetail(User user) {
        this.user = user;
        if(user != null){
            this.id = user.getId();
        }
    }
    /**
     *对当前的用户赋予其应有的权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //这儿需要将当前用户的权限以及角色加入集合并返回，
        //注意添加权限可以只加权限点  添加角色需要在角色点前面加上role_前缀
        //这儿因为属于实体类的缘故  所以在查询用户的角色及权限时  不能自动注入  手动获得spring容器中的bean
        //目前先手打
        List authorisList = new ArrayList();
        authorisList.add(new SimpleGrantedAuthority("aa"));
        authorisList.add(new SimpleGrantedAuthority("bb"));
        authorisList.add(new SimpleGrantedAuthority("cc"));
        authorisList.add(new SimpleGrantedAuthority("dd"));
        authorisList.add(new SimpleGrantedAuthority("ee"));

        authorisList.add(new SimpleGrantedAuthority("ROLE_AA"));
        authorisList.add(new SimpleGrantedAuthority("ROLE_BB"));
        authorisList.add(new SimpleGrantedAuthority("ROLE_CC"));
        authorisList.add(new SimpleGrantedAuthority("ROLE_DD"));

        //authorisList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return authorisList;
    }
    /**
     *获取密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    /**
     *获取用户名
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    /**
     *账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     *账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     *证书是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     *是否有效   可对应数据库中的delete_flag字段
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
