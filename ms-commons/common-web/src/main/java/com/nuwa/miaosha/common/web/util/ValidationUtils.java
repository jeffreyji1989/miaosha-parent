package com.nuwa.miaosha.common.web.util;

import com.nuwa.miaosha.common.util.constant.SeparatorConstants;
import com.nuwa.miaosha.common.util.enums.ErrorCodeEnum;
import com.nuwa.miaosha.common.util.execution.CommonException;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 参数校验器
 *
 * @author jijunhui
 * @date 2021/2/20
 * @desc
 */
public class ValidationUtils {
    public ValidationUtils() {
    }

    public static <T> void validation(T t, Class<?>... groups) {
        List<String> errList = validationNoException(t, groups);

        if (CollectionUtils.isEmpty(errList)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String msg : errList) {
            sb.append(msg).append(SeparatorConstants.COMMA);
        }

        throw new CommonException(ErrorCodeEnum.PARAM_ERROR.getCode(), ErrorCodeEnum.PARAM_ERROR.getMessage().concat(SeparatorConstants.COLON).concat(sb.toString()));
    }

    public static <T> List<String> validationNoException(T t, Class<?>... groups) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> set = validator.validate(t, groups);
        if (set.size() > 0) {
            List<String> errList = new ArrayList<>();
            set.forEach(v -> {
                errList.add(v.getMessage());
            });

            return errList;
        }

        return null;
    }

//    public interface AddGroup {
//    }
//
//    public interface UpdateGroup {
//    }
//
//    public interface SelectGroup {
//    }
}
