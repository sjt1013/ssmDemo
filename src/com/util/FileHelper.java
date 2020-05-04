package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/*文件操作帮助类*/
public class FileHelper {	
	public static String path="d://upload";//上传路径
	
	/**
	 * 通过旧文件名生成新的文件名，策略为【固定字符f_当前时间戳_3位随机码+后缀】
	 * @param oldFileName
	 * @return
	 */
	public static String newFileName(String oldFileName) {		
		String suffix=oldFileName.substring(oldFileName.indexOf("."));//取后缀名
		long now=System.currentTimeMillis();//当前时间戳（毫秒）
		int random=(int)(Math.random()*900)+100;//三位随机码
		return "f_"+now+"_"+random+suffix;
	}
	
	/**
	 * 上传文件
	 * @param mulFile 原始文件对象
	 * @param fileName 新文件名
	 * @return true | false
	 */
	public static boolean saveFile(MultipartFile mulFile,String fileName){
		if(!mulFile.isEmpty()) {
			try {
				//创建目标目录对象
				//File fileDir=new File(request.getServletContext().getRealPath("/resources/")+path);				
				File fileDir=new File(path);
				
				//不存在即创建
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				
				//构建IO操作文件对象
				File uploadFile=new File(fileDir,fileName);
				
				//移动文件
				mulFile.transferTo(uploadFile);
				
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * 删除文件
	 * @param fileName 文件名
	 * @return true || false
	 */
	public static boolean removeFile(String fileName) {
		try {
			//创建目标目录对象
			File fileDir=new File(path);
			
			//构建IO操作文件对象
			File targetFile=new File(fileDir,fileName);
			
			if(targetFile.exists()) {
				targetFile.delete();
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/***
	 * 普通方式下载文件
	 * @param response
	 * @param fileName 待下载文件名
	 */
	public static void dowloadFile(HttpServletResponse response,String fileName){
		try {
			//构建目录对象
			//File fileDir=new File(request.getServletContext().getRealPath("/")+path);
			File fileDir=new File(path);
			
			//构建待下载的文件对象
			File f =new File(fileDir,fileName);
			//构建字节输入流
			InputStream in =new FileInputStream(f);
			
			//响应对象重置
			response.reset();
			//设置响应格式
			response.setContentType("bin");
			//设置响应头
			response.addHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
			//通过响应对象得到对应的字节输出流
			OutputStream out=response.getOutputStream();
			
			int len=0;
			byte[] b=new byte[1024];
			
			while((len=in.read(b))!=-1){
				out.write(b,0,len);
			}
			
			in.close();
			out.close();
			
		} catch (Exception e) {
			
		}
		
	}
	
	/***
	 * spring提供的下载方式
	 * @param request
	 * @param fileName 待下载文件名
	 * @return ResponseEntity<byte[]>
	 */
	public static ResponseEntity<byte[]> dowloadFileSpring(String fileName){
		try {
			//File fileDir=new File(request.getServletContext().getRealPath("/")+path);
			File fileDir=new File(path);
			File f =new File(fileDir,fileName);
			
			HttpHeaders headers = new HttpHeaders(); 
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", fileName); 
			
			ResponseEntity<byte[]> re = new ResponseEntity<>(FileUtils.readFileToByteArray(f), headers, HttpStatus.CREATED);
			
			return re;
		} catch (Exception e) {
			
		}
		
		return null;
		
	}
	
}
