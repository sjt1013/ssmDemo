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

/*�ļ�����������*/
public class FileHelper {	
	public static String path="d://upload";//�ϴ�·��
	
	/**
	 * ͨ�����ļ��������µ��ļ���������Ϊ���̶��ַ�f_��ǰʱ���_3λ�����+��׺��
	 * @param oldFileName
	 * @return
	 */
	public static String newFileName(String oldFileName) {		
		String suffix=oldFileName.substring(oldFileName.indexOf("."));//ȡ��׺��
		long now=System.currentTimeMillis();//��ǰʱ��������룩
		int random=(int)(Math.random()*900)+100;//��λ�����
		return "f_"+now+"_"+random+suffix;
	}
	
	/**
	 * �ϴ��ļ�
	 * @param mulFile ԭʼ�ļ�����
	 * @param fileName ���ļ���
	 * @return true | false
	 */
	public static boolean saveFile(MultipartFile mulFile,String fileName){
		if(!mulFile.isEmpty()) {
			try {
				//����Ŀ��Ŀ¼����
				//File fileDir=new File(request.getServletContext().getRealPath("/resources/")+path);				
				File fileDir=new File(path);
				
				//�����ڼ�����
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				
				//����IO�����ļ�����
				File uploadFile=new File(fileDir,fileName);
				
				//�ƶ��ļ�
				mulFile.transferTo(uploadFile);
				
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * ɾ���ļ�
	 * @param fileName �ļ���
	 * @return true || false
	 */
	public static boolean removeFile(String fileName) {
		try {
			//����Ŀ��Ŀ¼����
			File fileDir=new File(path);
			
			//����IO�����ļ�����
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
	 * ��ͨ��ʽ�����ļ�
	 * @param response
	 * @param fileName �������ļ���
	 */
	public static void dowloadFile(HttpServletResponse response,String fileName){
		try {
			//����Ŀ¼����
			//File fileDir=new File(request.getServletContext().getRealPath("/")+path);
			File fileDir=new File(path);
			
			//���������ص��ļ�����
			File f =new File(fileDir,fileName);
			//�����ֽ�������
			InputStream in =new FileInputStream(f);
			
			//��Ӧ��������
			response.reset();
			//������Ӧ��ʽ
			response.setContentType("bin");
			//������Ӧͷ
			response.addHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
			//ͨ����Ӧ����õ���Ӧ���ֽ������
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
	 * spring�ṩ�����ط�ʽ
	 * @param request
	 * @param fileName �������ļ���
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
