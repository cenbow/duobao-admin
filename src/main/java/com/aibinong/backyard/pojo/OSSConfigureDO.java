package com.aibinong.backyard.pojo;

import com.aibinong.backyard.Constants;

public class OSSConfigureDO {
	private String endpoint;  
    private String accessKeyId;  
    private String accessKeySecret;  
    private String bucketName;  
    private String accessUrl;
    public OSSConfigureDO() {  
  
        this.endpoint = Constants.MEMORY_SERVER_ENDPOINT;  
        this.accessKeyId = Constants.MEMORY_SERVER_ACCESSKEYID;  
        this.accessKeySecret = Constants.MEMORY_SERVER_ACCESSKEYSECRET;  
        this.bucketName = Constants.MEMORY_SERVER_BUCKETNAME;  
        this.accessUrl = "http://new-duobao-oss.oss-cn-beijing.aliyuncs.com";  
        
        
    } 
    
    
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
}
