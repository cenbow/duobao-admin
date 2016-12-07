package com.aibinong.backyard.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.time.DateFormatUtils;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.pojo.OSSConfigureDO;
import com.aibinong.backyard.service.UploadService;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

@IocBean(name = "uploadService")
public class UploadFileImpl implements UploadService {
	@Override
	public String uploadFile(File f) throws Exception {

		OSSConfigureDO ossConfigure = new OSSConfigureDO();
		InputStream fileContent = null;
		fileContent = new FileInputStream(f);
		String fileDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
		StringBuffer lujing = new StringBuffer(fileDate.substring(0, 8));
		String fileFinalName = lujing + randStr(8) + "." + getExtName(f.getName());
		OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(),
				ossConfigure.getAccessKeySecret());
		// 创建上传Object的Metadata
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(fileContent.available());
		objectMetadata.setCacheControl("no-cache");
		objectMetadata.setHeader("Pragma", "no-cache");
		objectMetadata.setContentType(contentType(getExtName(f.getName())));
		objectMetadata.setContentDisposition("inline;filename=" + fileFinalName);
		// 上传文件
		PutObjectResult result = ossClient.putObject(ossConfigure.getBucketName(), fileFinalName, fileContent,
				objectMetadata);
		return ossConfigure.getAccessUrl() + "/" + fileFinalName;
	}

	@Override
	public String uploadMaterial(File f, String filename) throws Exception {
		OSSConfigureDO ossConfigure = new OSSConfigureDO();
		InputStream fileContent = null;
		fileContent = new FileInputStream(f);
		String fileDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
		StringBuffer lujing = new StringBuffer(fileDate.substring(0, 12));
		String fileFinalName = filename + "_" + lujing + "." + getExtName(f.getName());
		OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(),
				ossConfigure.getAccessKeySecret());
		// 创建上传Object的Metadata
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(fileContent.available());
		objectMetadata.setCacheControl("no-cache");
		objectMetadata.setHeader("Pragma", "no-cache");
		objectMetadata.setContentType(contentType(getExtName(f.getName())));
		objectMetadata.setContentDisposition("inline;filename=" + fileFinalName);
		// 上传文件
		PutObjectResult result = ossClient.putObject(ossConfigure.getBucketName(), fileFinalName, fileContent,
				objectMetadata);
		return ossConfigure.getAccessUrl() + "/" + fileFinalName;
	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType @Version1.0
	 * 
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String contentType(String FilenameExtension) {
		if (FilenameExtension.equals("BMP") || FilenameExtension.equals("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equals("GIF") || FilenameExtension.equals("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equals("JPEG") || FilenameExtension.equals("jpeg") || FilenameExtension.equals("JPG")
				|| FilenameExtension.equals("jpg") || FilenameExtension.equals("PNG")
				|| FilenameExtension.equals("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equals("HTML") || FilenameExtension.equals("html")) {
			return "text/html";
		}
		if (FilenameExtension.equals("TXT") || FilenameExtension.equals("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equals("VSD") || FilenameExtension.equals("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equals("PPTX") || FilenameExtension.equals("pptx") || FilenameExtension.equals("PPT")
				|| FilenameExtension.equals("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equals("DOCX") || FilenameExtension.equals("docx") || FilenameExtension.equals("DOC")
				|| FilenameExtension.equals("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equals("XML") || FilenameExtension.equals("xml")) {
			return "text/xml";
		}
		if (FilenameExtension.equals("APK") || FilenameExtension.equals("apk")) {
			return "application/vnd.android.package-archive";
		}
		if (FilenameExtension.equals("ZIP") || FilenameExtension.equals("zip")) {
			return "application/x-zip-compressed"; 
		}

		return "text/html";
	}

	/**
	 * 文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtName(String fileName) {
		int index = fileName.lastIndexOf(".");

		if (index == 0) {
			return "";
		}
		return fileName.substring(index + 1);
	}

	/**
	 * 随机文件名
	 * 
	 * @param n
	 * @return
	 */
	public static String randStr(int n) {
		char[] ca = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] cr = new char[n];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < n; i++) {
			int x = random.nextInt(36);
			cr[i] = ca[x];
		}
		return (new String(cr));
	}

}
