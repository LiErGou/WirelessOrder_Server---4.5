package com.amaker.entity;

public class Order {
	// ���
	private int id;
	// �µ�ʱ��
	private String orderTime;
	// �µ��û�
	private int userId;
	// ����
	private int tableId;
	// ����
	private int personNum;
	// �Ƿ����
	private int isPay;
	// ��ע
	private String remark;
	private int isGuest;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getPersonNum() {
		return personNum;
	}
	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int isGuest() {
		return isGuest;
	}
	public void setGuest(int isGuest) {
		this.isGuest = isGuest;
	}
	
	
	
}
