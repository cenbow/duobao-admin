package com.aibinong.backyard.web.Excel;

public class RechargeModel {

	@ExcelCell(index = 0)
	private String periodId;
	@ExcelCell(index = 1)
	private String productName;
	@ExcelCell(index = 2)
	private String amount;
	@ExcelCell(index = 3)
	private String mobile;
	@ExcelCell(index = 4)
	private String nickName;
	@ExcelCell(index = 5)
	private String status;
	@ExcelCell(index = 6)
	private String openDrawTime;
	public RechargeModel(String periodId,  String productName, String amount,String mobile,String nickName,String status,String openDrawTime) {
		this.periodId = periodId;
		this.productName = productName;
		this.amount=amount;
		this.mobile=mobile;
		this.nickName=nickName;
		this.openDrawTime=openDrawTime;
		this.status=status;
	}
	public String getPeriodId() {
		return periodId;
	}
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOpenDrawTime() {
		return openDrawTime;
	}
	public void setOpenDrawTime(String openDrawTime) {
		this.openDrawTime = openDrawTime;
	}
	
}
