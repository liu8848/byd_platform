package com.platform.utils;

import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class ExcelUtil {
    public static <T> List<T> read(MultipartFile file,Class<T> head)throws IOException{
        log.info("----------------开始读取数据：{}  ----------------------------",file.getName());
        List<T> results = EasyExcel.read(file.getInputStream(), head, null)
                .autoCloseStream(false)
                .doReadAllSync();
        log.info("----------------读取 {} 条数据-------------------------------",results.size());
        return results;
    }

    /***
     *
     * @param response 响应结果对象
     * @param rawFileName 文件名
     * @throws UnsupportedEncodingException 不支持异常编码
     */
    public static void setExcelResponseProp(HttpServletResponse response,
                                            String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName= URLEncoder.encode(rawFileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition","attachment;filename*=utf-8''" + fileName + ".xlsx");
    }
}
