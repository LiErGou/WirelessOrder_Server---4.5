package com.amaker.dao;

import com.amaker.entity.User;
// UesrDao �ӿ�
public interface UserRegister {
	// ע�᷽��
	public User register(String account, String password,int[] lf,int[] df,int[] lm,int[] dm,int gender);
}
