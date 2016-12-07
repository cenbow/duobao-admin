package com.aibinong.backyard.web.Excel;

public class EntityModel {
	@ExcelCell(index = 0)
	private String periodId;
	@ExcelCell(index = 1)
	private String productId;
	@ExcelCell(index = 2)
	private String productName;
	@ExcelCell(index = 3)
	private String luckNum;
	@ExcelCell(index = 4)
	private String mobile;
	@ExcelCell(index = 5)
	private String nickName;
	@ExcelCell(index = 6)
	private String address;
	@ExcelCell(index = 7)
	private String openDrawTime;
	@ExcelCell(index = 8)
	private String commitAddressTime;
	public EntityModel(String periodId, String productId, String productName,String luckNum,String mobile,String nickName,String address,String openDrawTime,String commitAddressTime) {
		this.periodId = periodId;
		this.productId = productId;
		this.productName = productName;
		this.luckNum=luckNum;
		this.mobile=mobile;
		this.nickName=nickName;
		this.address=address;
		this.openDrawTime=openDrawTime;
		this.commitAddressTime=commitAddressTime;
	}

	public String getPeriodId() {
		return periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLuckNum() {
		return luckNum;
	}

	public void setLuckNum(String luckNum) {
		this.luckNum = luckNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpenDrawTime() {
		return openDrawTime;
	}

	public void setOpenDrawTime(String openDrawTime) {
		this.openDrawTime = openDrawTime;
	}

	public String getCommitAddressTime() {
		return commitAddressTime;
	}

	public void setCommitAddressTime(String commitAddressTime) {
		this.commitAddressTime = commitAddressTime;
	}

	
}
