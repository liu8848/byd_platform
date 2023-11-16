package com.platform.utils;

import com.mysql.cj.util.StringUtils;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/***
 * 文件上传工具类
 */
@Component
@Slf4j
@Data
public class FileUploadUtils {
    /**
     * 默认大小50M
     */
    public static final long DEFAULT_MAX_SIZE=20*1024*1024;

    /**
     * 默认文件名最大长度100
     */
    public static final int FILE_NAME_MAX=100;

    /**
     * 默认上传路径
     */
    @Value("${upload-path.default-path}")
    private static String DEFAULT_BASE_FILE="./";

    /**
     * 按默认配置上传文件
     * @param file 文件
     * @return 文件名
     * @throws IOException IO错误
     */
    public static String upload(MultipartFile file) throws IOException{
        try {
            return upload(FileUploadUtils.DEFAULT_BASE_FILE,file,MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }catch (Exception e){
            throw new IOException(e.getMessage(),e);
        }
    }

    /**
     * 根据文件路径上传
     * @param baseDir 相应的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException IO错误
     */
    public static String upload(String baseDir,MultipartFile file) throws IOException{
        try {
            log.info(DEFAULT_BASE_FILE);
            return upload(baseDir,file,MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }catch (Exception e){
            throw new IOException(e.getMessage(),e);
        }
    }

    /***
     * 文件上传
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param allowedExtension 上传的文件类型
     * @return 返回上传成功的文件名
     * @throws Exception IO错误
     */
    public static String upload(String baseDir,MultipartFile file,String[] allowedExtension)throws Exception{
        //合法性校验
        assertAllowed(file,allowedExtension);
        //重命名文件
        String fileName=encodingFileName(file);
        //获取或创建文件资源
        File desc=getAbsoluteFile(baseDir,fileName);

        //以绝对路径获取该文件资源
        File writeFile = new File(desc.getAbsolutePath());
        //写入内容
        file.transferTo(writeFile);

        return desc.getAbsolutePath();
    }


    /**
     * 根据路径创建文件
     * @param uploadDir 文件上传路径
     * @param fileName 文件名
     * @return 文件资源
     * @throws IOException IO错误
     */
    private static  File getAbsoluteFile(String uploadDir,String fileName)throws IOException{
        File desc=new File(uploadDir+File.separator+fileName);

        //父文件夹不存在则创建
        if(!desc.getParentFile().exists()){
            desc.getParentFile().mkdirs();
        }
        //文件不存在则创建
        if (!desc.exists()){
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 对文件名进行特殊处理
     * @param file 文件
     * @return 处理后的文件名
     */
    private static String encodingFileName(MultipartFile file){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dataPath=simpleDateFormat.format(new Date());
        return dataPath+"-"+ UUID.randomUUID().toString()+"."+getExtension(file);
    }

    /**
     * 文件合法性校验
     *
     * @param file 上传的文件
     * @return
     */
    public static void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws Exception {

        if (file.getOriginalFilename() != null) {
            int fileNamelength = file.getOriginalFilename().length();

            if (fileNamelength > FILE_NAME_MAX) {
                throw new Exception("文件名过长");
            }
        }

        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE) {
            throw new Exception("文件过大");
        }

        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            throw new Exception("请上传指定类型的文件！");
        }
    }


    /***
     * 判断MIME类型是否允许的MIME累死你个
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static boolean isAllowedExtension(String extension,String[] allowedExtension){
        for (String str:allowedExtension){
            if(str.equalsIgnoreCase(extension)){
                return true;
            }
        }
        return false;
    }

    /***
     * 获取文件名后缀
     * @param file 表单文件
     * @return 后缀名
     */
    public static String getExtension(MultipartFile file){
        String fileName= file.getOriginalFilename();
        String extension=null;
        if(fileName==null){
            return null;
        }else {
            int index=indexOfExtension(fileName);
            extension=index==-1?"":fileName.substring(index+1);
        }
        if (StringUtils.isNullOrEmpty(extension)) {

            extension=MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }

    private static int indexOfExtension(String fileName) {
        if(fileName==null){
            return -1;
        }else{
            int extensionPos=fileName.lastIndexOf(46);
            int laseSeparator=indexOfLastSeparator(fileName);
            return laseSeparator>extensionPos?-1:extensionPos;
        }
    }

    private static int indexOfLastSeparator(String fileName) {
        if(fileName==null){
            return -1;
        }else {
            int lastUniPos=fileName.lastIndexOf(47);
            int lastWindowsPos=fileName.lastIndexOf(92);
            return Math.max(lastUniPos,lastWindowsPos);
        }
    }

}