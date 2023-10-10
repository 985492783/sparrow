package com.sparrow.core.auth;

import com.sparrow.core.utils.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 985492783@qq.com
 * @date 2023/10/10 23:28
 */
@ConfigurationProperties(prefix = "sparrow.auth")
public class AuthConfig {
    
    private boolean enabled = false;
    
    private String username = "sparrow";
    
    private String password = "sparrow";
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean login(HttpServletRequest req) {
        String user = req.getParameter("username");
        String pwd = req.getParameter("password");
        if (StringUtils.isEmpty(user) || StringUtils.isEmpty(pwd)) {
            return false;
        }
        return user.equals(this.username) && pwd.equals(this.password);
    }
}
