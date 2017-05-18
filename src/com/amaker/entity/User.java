package com.amaker.entity;

/**
 * 
 * @author hz.guo
 *	用于封装UserTbl表的实体类
 */
public class User {
	// 编号
	private int id;
	// 账号
	private String account;
	// 密码
	private String password;
	// 用户名称
	private String name;
	// 性别
	private String gender;
	// 权限
	private int permission;
	// 备注
	private String remark;
	private boolean isGuest;
	private int[] lf;   //喜欢口味数组
	private int[] df;   //讨厌口味数组
	private int[] lm;   //喜欢材料数组
	private int[] dm;   //讨厌材料数组
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int[] getDf() {
		return df;
	}
	public void setDf(int[] df) {
		this.df = df;
	}
	public int[] getLf() {
		return lf;
	}
	public void setLf(int[] lf) {
		this.lf = lf;
	}
	public int[] getLm() {
		return lm;
	}
	public void setLm(int[] lm) {
		this.lm = lm;
	}
	public int[] getDm() {
		return dm;
	}
	public void setDm(int[] dm) {
		this.dm = dm;
	}
	public boolean isGuest() {
		return isGuest;
	}
	public void setGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}
}
