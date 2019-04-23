/**
 * 
 */
package aos.framework.core.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

/**
 * 文件处理工具类
 * 
 * @author zhangchao
 *
 */
public class FileUtils {

	public static final String DEFAULT_FOLDER = "temp";
	
	
	public static File fileUpload(String savePath,MultipartFile fileUpload) throws IOException{
		File dir = null;
		if (savePath == null) {
            dir = new File(DEFAULT_FOLDER);
        } else {
            dir = new File(savePath);
            if (!dir.exists())
                dir.mkdirs();
        }
        if (!dir.exists()) {
        	dir.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        String fileType = fileUpload.getContentType();
        String saveName = uuid + "." +  fileType.substring(fileType.indexOf("/")+1);
        File serverFile = new File(dir.getAbsolutePath() + File.separator + saveName);
        //或者传统输入输出流
        byte[] bytes = fileUpload.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        return serverFile;
	}
	
	/** 
     * 把base64图片数据转为本地图片 
     * @param base64 
     * @param savePath 
     * @throws IOException 
     */  
    public static File convertBase64DataToImage(String base64,String savePath) throws IOException {  
    	File dir = null;
		if (savePath == null) {
            dir = new File(DEFAULT_FOLDER);
        } else {
            dir = new File(savePath);
            if (!dir.exists())
                dir.mkdirs();
        }
        if (!dir.exists()) {
        	dir.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
    	String ext = StringUtils.substringBetween(base64,"data:image/",";");
    	if("jpeg".equalsIgnoreCase(ext)){//data:image/jpeg;base64,base64编码的jpeg图片数据  
            ext = "jpg";  
        } else if("x-icon".equalsIgnoreCase(ext)){//data:image/x-icon;base64,base64编码的icon图片数据  
            ext = "ico";  
        }
    	String fileName = uuid + "." + ext;
		String base64ImgData = base64.substring(base64.indexOf(",")+1);
		String filePath = savePath + File.separator + fileName;
        BASE64Decoder decoder = new BASE64Decoder();  
        File serverFile = new File(filePath);
        //或者传统输入输出流
        byte[] bytes = decoder.decodeBuffer(base64ImgData);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        return serverFile;
    } 
	
}
