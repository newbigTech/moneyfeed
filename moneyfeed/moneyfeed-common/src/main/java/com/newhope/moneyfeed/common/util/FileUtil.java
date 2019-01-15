package com.newhope.moneyfeed.common.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作工具类
 *
 */
public class FileUtil {

	private static AtomicInteger atomicInteger=new AtomicInteger(0);

	/**
	 * 上传mutipartFile到指定路径
	 * 上传路径:uploadServer + uploadPath + pathPrefix + filename
	 * @param file
	 * @param uploadServer	图片服务器地址
	 * @param uploadPath	服务器文件路径
	 * @param pathPrefix	文件路径下的子路径，用于划分产品和端
	 * @return	返回访问地址
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String upload(MultipartFile file, String uploadServer, String uploadPath, String pathPrefix) throws Exception {
		String fileName = generateFileName(file);
		File targetFile = new File(uploadPath + pathPrefix + "/" + fileName);
		if(!targetFile.getParentFile().exists()){ //判断文件父目录是否存在
			targetFile.getParentFile().mkdirs();
	    }
		file.transferTo(targetFile);
		return new StringBuilder().append(uploadServer)
				.append(pathPrefix)
				.append("/")
				.append(fileName)
				.toString();
	}

	public static String generateFileName(MultipartFile file) {
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));	//文件后缀
		return UUID.randomUUID().toString().replaceAll("-", "") + suffix;
	}


}
