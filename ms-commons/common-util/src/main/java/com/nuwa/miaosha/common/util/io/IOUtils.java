package com.nuwa.miaosha.common.util.io;

import com.nuwa.miaosha.common.util.enums.ErrorCodeEnum;
import com.nuwa.miaosha.common.util.execution.CommonException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @Author jijunhui
 * @Date 2021/1/23 22:01
 * @Version 1.0.0
 * @Description 流工具类
 */
//@Slf4j
public class IOUtils {
    /**
     * inputstream to string
     *
     * @param inputStream
     * @return
     */
    public static final String inputStreamToString(InputStream inputStream) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
//            log.error("inputStream to String error:{}", e.getMessage(), e);
            throw new CommonException(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * str to inputstream
     *
     * @param str
     * @return
     */
    public static final InputStream StringToInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 获取网络文件的输入流
     *
     * @param urlStr
     * @return
     */
    public final static InputStream getInputStreamByUrl(String urlStr) {
        DataInputStream in = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = new DataInputStream(conn.getInputStream());
        } catch (IOException e) {
//            log.error("url转换输入流失败,错误信息{}", e.getMessage(), e);
            throw new CommonException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        return in;
    }
}
