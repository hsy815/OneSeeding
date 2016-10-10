package com.hsy.directseeding.entity;

/**
 * Created by hsy on 16/4/25.
 */
public class LRRmodel {

    private String accessToken;//获取验证码成功
    private String[] mobile;//失败
    private String[] password;//登录失败
    private String[] code;//验证码过期
    private String user_type;//用户类型

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String[] getMobile() {
        return mobile;
    }

    public void setMobile(String[] mobile) {
        this.mobile = mobile;
    }

    public String[] getPassword() {
        return password;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    public String[] getCode() {
        return code;
    }

    public void setCode(String[] code) {
        this.code = code;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public int ismobile() {
        int a = 0;
        if(mobile!=null && mobile.length>0) {
            for (int i = 0; i < mobile.length; i++) {
                if (mobile[i] != null && mobile[i] != " ")
                    a++;
            }
        }
        return a;
    }

    public int ispassword() {
        int a = 0;
        if(password!=null && password.length>0) {
            for (int i = 0; i < password.length; i++) {
                if (password[i] != null && password[i] != " ")
                    a++;
            }
        }
        return a;
    }

    public int iscode(){
        int a = 0;
        if(code!=null&& code.length>0) {
            for (int i = 0; i < code.length; i++) {
                if (code[i] != null && code[i] != " ")
                    a++;
            }
        }
        return a;
    }
}
