package com.nuwa.miaosha.common.util.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jijunhui
 * @date 2020/6/20
 * @desc
 */
//@Slf4j
public class BeanUtils {

    public static <T> T conversion(Object o, Class<T> c) {
        if (!(o instanceof String)) {
            o = JSON.toJSONString(o);
        }
        return JSONObject.parseObject((String) o, c);
    }

    public static <T> T conversion(Object o, TypeReference<T> tTypeReference) {
        if (!(o instanceof String)) {
            o = JSON.toJSONString(o);
        }
        return JSONObject.parseObject((String) o, tTypeReference);
    }

    /**
     * 多线程list拷贝
     *
     * @param fromList  拷贝前的值
     * @param toClass   拷贝类型
     * @param threshold 开启多线程的阈值 默认是100
     * @return 拷贝后的值
     */
    public static <T> List<T> parallelConversionList(List fromList, Class<T> toClass, Integer threshold) {
        if (null == fromList || fromList.size() == 0) {
            return null;
        }
        if (null == threshold || threshold < 100) {
            threshold = 100;
        }
        try {
            if (fromList.size() < threshold) {
                List toList = new ArrayList();
                for (Object aFromList : fromList) {
                    toList.add(conversion(aFromList, toClass));
                }
                return toList;
            } else {
                List syncList = Collections.synchronizedList(new ArrayList<>());
                fromList.parallelStream().forEach(from -> {
                    syncList.add(conversion(from, toClass));
                });
                return syncList;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 多线程list拷贝
     *
     * @param fromList 拷贝前的值
     * @param toClass  拷贝类型
     * @return 拷贝后的值
     */
    public static <T> List<T> parallelConversionList(List fromList, Class<T> toClass) {
        return parallelConversionList(fromList, toClass, null);
    }

    /**
     * 单线程list转换
     *
     * @param fromList
     * @param toClass
     * @param <T>
     * @return
     */
    public static <T> List<T> conversionList(List fromList, Class<T> toClass) {
        if (null == fromList || fromList.size() == 0) {
            return null;
        }
        try {
            List toList = new ArrayList();
            for (Object aFromList : fromList) {
                toList.add(conversion(aFromList, toClass));
            }
            return toList;
        } catch (Exception e) {
            return null;
        }
    }

//    /**
//     * 获取对象中值为空的属性
//     *
//     * @param source
//     * @return
//     */
//    public static String[] getNullPropertyNames(Object source) {
//        final BeanWrapper src = new BeanWrapperImpl(source);
//        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
//
//        Set<String> emptyNames = new HashSet<>();
//        for (java.beans.PropertyDescriptor pd : pds) {
//            Object srcValue = src.getPropertyValue(pd.getName());
//            if (srcValue == null) emptyNames.add(pd.getName());
//        }
//        String[] result = new String[emptyNames.size()];
//        return emptyNames.toArray(result);
//    }

}
