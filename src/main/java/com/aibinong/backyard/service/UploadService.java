package com.aibinong.backyard.service;

import java.io.File;

public interface UploadService {

	public String uploadFile(File f) throws Exception;

	public String uploadMaterial(File f, String filename) throws Exception;
}
