package com.nuwa.miaosha.common.db.util;//package com.nuwa.common.db.util;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
//import com.github.pagehelper.PageHelper;
//import com.sinoiov.eimp.common.enums.DeletedEnum;
//import com.sinoiov.eimp.common.enums.page.QueryEnum;
//import com.sinoiov.eimp.common.enums.page.QueryOrderEnum;
//import com.sinoiov.eimp.common.vo.page.DynamicSqlParameter;
//import com.sinoiov.eimp.common.vo.page.Rule;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * QueryWrapper 工具类
// * @author houjianwei
// * @date 2020/6/30
// * @desc
// */
//@Slf4j
//public class SqlConverterUtil {
//    private final static ConcurrentHashMap<String, Map<String, ColumnCache>> clazzColumnMap = new ConcurrentHashMap<>();
//
//    public static <E> QueryWrapper<E> paramToWrapper(DynamicSqlParameter parameter, Class<E> clazz) {
//        return paramToWrapper(parameter, clazz, null);
//    }
//
//    public static <E> QueryWrapper<E> paramToWrapper(DynamicSqlParameter parameter, Class<E> clazz, Map<String, String> propertyChangeMap) {
//        Integer pageNum = parameter.getPageNum();
//        Integer pageSize = parameter.getPageSize();
//        if (pageNum != null && pageSize != null) {
//            PageHelper.startPage(pageNum, pageSize);
//        }
//        QueryWrapper<E> query = Wrappers.<E>query();
//        if (parameter.getRules() != null && parameter.getRules().size() > 0) {
//            addQueryWrapperRule(query, clazz, parameter.getRules(), propertyChangeMap);
//        }
//        addQueryWrapperMap(query, clazz, QueryEnum.EQ, parameter.getEqual(), propertyChangeMap);
//        addQueryWrapperMap(query, clazz, QueryEnum.NE, parameter.getNotequal(), propertyChangeMap);
//        addQueryWrapperMap(query, clazz, QueryEnum.LIKE, parameter.getLike(), propertyChangeMap);
//        addQueryWrapperMap(query, clazz, QueryEnum.IN, parameter.getInMap(), propertyChangeMap);
//        addQueryWrapperMap(query, clazz, QueryEnum.NOT, parameter.getNotInMap(), propertyChangeMap);
//        addQueryWrapperList(query, clazz, QueryEnum.IS_NULL, parameter.getIsNull(), propertyChangeMap);
//        addQueryWrapperList(query, clazz, QueryEnum.IS_NOT_NULL, parameter.getIsNotNull(), propertyChangeMap);
//        addQueryWrapperOrder(query, parameter.getOrder(), parameter.getSort());
//        //默认查询正常状态数据
//        addQueryWrapper(query, clazz, QueryEnum.EQ, "delFlag", DeletedEnum.ENABLE.getCode(), propertyChangeMap);
//        return query;
//    }
//
//    public static void convertLongDate(List<Rule> ruleList, List<String> keyList, SimpleDateFormat simpleDateFormat) {
//        if (CollectionUtils.isEmpty(ruleList) || CollectionUtils.isEmpty(keyList) || simpleDateFormat == null) {
//            return;
//        }
//        Calendar calendar = Calendar.getInstance();
//        Iterator<Rule> iterator = ruleList.iterator();
//        while (iterator.hasNext()) {
//            Rule rule = iterator.next();
//            String field = rule.getField();
//            if (keyList.contains(field)) {
//                Object value = rule.getValue();
//                if (ObjectUtils.isEmpty(value)) {
//                    iterator.remove();
//                    continue;
//                }
//                try {
//                    calendar.setTimeInMillis((long) value);
//                    String format = simpleDateFormat.format(calendar.getTime());
//                    rule.setValue(format);
//                } catch (Exception e) {
//                    iterator.remove();
//                    log.warn("时间格式错误不能转换");
//                }
//            }
//        }
//    }
//
//    private static <E> void addQueryWrapperRule(QueryWrapper<E> query, Class<E> clazz, List<Rule> ruleList, Map<String, String> propertyChangeMap) {
//        for (Rule rule : ruleList) {
//            Object value = rule.getValue();
//            String field = rule.getField();
//            String type = rule.getType();
//            if (StringUtils.isAnyBlank(field, type) || (!type.equals(QueryEnum.IS_NULL.getType()) && ObjectUtils.isEmpty(value))) {
//                continue;
//            }
//            addQueryWrapper(query, clazz, QueryEnum.getType(type), field, value, propertyChangeMap);
//        }
//    }
//
//    private static <E> void addQueryWrapperMap(QueryWrapper<E> query, Class<E> clazz, QueryEnum type, Map<String, Object> map, Map<String, String> propertyChangeMap) {
//        if (map == null || map.size() == 0) {
//            return;
//        }
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            Object value = entry.getValue();
//            if (ObjectUtils.isEmpty(value)) {
//                continue;
//            }
//            addQueryWrapper(query, clazz, type, entry.getKey(), value, propertyChangeMap);
//        }
//    }
//
//    private static <E> void addQueryWrapperList(QueryWrapper<E> query, Class<E> clazz, QueryEnum type, List<String> list, Map<String, String> propertyChangeMap) {
//        if (CollectionUtils.isEmpty(list)) {
//            return;
//        }
//        for (String key : list) {
//            addQueryWrapper(query, clazz, type, key, null, propertyChangeMap);
//        }
//    }
//
//    private static <E> void addQueryWrapper(QueryWrapper<E> query, Class<E> clazz, QueryEnum type, String name, Object value, Map<String, String> propertyChangeMap) {
//        if (propertyChangeMap != null && propertyChangeMap.containsKey(name)) {
//            name = propertyChangeMap.get(name);
//        }
//
//        name = camelCaseToUnderscore(name, clazz);
//        if (StringUtils.isBlank(name)) {
//            return;
//        }
//
//        switch (type) {
//            case EQ:
//                query.eq(name, value);
//                break;
//            case NE:
//                query.ne(name, value);
//                break;
//            case GT:
//                query.gt(name, value);
//                break;
//            case GE:
//                query.ge(name, value);
//                break;
//            case LT:
//                query.lt(name, value);
//                break;
//            case LE:
//                query.le(name, value);
//                break;
//            case LIKE:
//                query.like(name, value);
//                break;
//            case IN:
//                query.in(name, (List) value);
//                break;
//            case NOT:
//                query.notIn(name, value);
//                break;
//            case IS_NULL:
//                query.isNull(name);
//                break;
//            case IS_NOT_NULL:
//                query.isNotNull(name);
//                break;
//            default:
//                break;
//        }
//    }
//
//    private static void addQueryWrapperOrder(QueryWrapper query, String order, String sort) {
//        if (StringUtils.isAnyBlank(order, sort)) {
//            query.orderByDesc("CREATE_TIME");
//            return;
//        }
//        if (QueryOrderEnum.ASC.getType().equals(sort)) {
//            query.orderByAsc(order);
//        }
//        if (QueryOrderEnum.DESC.getType().equals(sort)) {
//            query.orderByDesc(order);
//        }
//    }
//
//    /**
//     * 驼峰命名转成下划线
//     *
//     * @param camelCaseStr
//     * @return
//     */
//    private static String camelCaseToUnderscore(String camelCaseStr, Class clazz) {
//        String s = LambdaUtils.formatKey(camelCaseStr);
//        Map<String, ColumnCache> columnMap = clazzColumnMap.get(clazz.getName());
//        if (columnMap == null) {
//            columnMap = LambdaUtils.getColumnMap(clazz);
//            clazzColumnMap.put(clazz.getName(), columnMap);
//        }
//        for (Map.Entry<String, ColumnCache> entry : columnMap.entrySet()) {
//            if (entry.getKey().equals(s)) {
//                camelCaseStr = entry.getValue().getColumn();
//                return camelCaseStr;
//            }
//        }
//        return null;
//    }
//}
