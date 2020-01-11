package com.language.originality.commons;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ValidationException;

/**
 * @Auther: zhaikaixuan
 * @Date: 2020-01-11 22:13
 * @Description: 参数验证器
 */
public class ValidatorParamUtil {
    /**
     * @return void
     * @Author zhaikaixuan
     * @Description 验证参数
     * @Date 2020-01-11 22:15
     * @Param [bindingResult]
     **/
    public static void validatorData(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                sb.append(bindingResult.getAllErrors().get(i).getDefaultMessage());
                if (i != (bindingResult.getAllErrors().size() - 1)) {
                    sb.append(",");
                }
            }
            throw new ValidationException(sb.toString());
        }

    }
}
