package com.amaker.dao;

import com.amaker.entity.User;
// UesrDao 接口
public interface UserRegister {
	// 注册方法
	public User register(String account, String password,int[] lf,int[] df,int[] lm,int[] dm,int gender);
}
