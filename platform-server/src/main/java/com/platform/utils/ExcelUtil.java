package com.platform.utils;

import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExcelUtil {
    public static <T> List<T> read(MultipartFile file,Class<T> head)throws IOException{
        return EasyExcel.read(file.getInputStream(),head,null)
                .autoCloseStream(false)
                .doReadAllSync();
    }

    /***
     *
     * @param response 响应结果对象
     * @param rawFileName 文件名
     */
    public static void setExcelResponseProp(HttpServletResponse response,
                                            String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName= URLEncoder.encode(rawFileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition","attachment;filename*=utf-8''" + fileName + ".xlsx");
    }
}
