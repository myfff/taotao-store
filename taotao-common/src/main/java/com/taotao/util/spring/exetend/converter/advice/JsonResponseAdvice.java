package com.taotao.util.spring.exetend.converter.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;
/**
 * @author 芳芳
 * @create 2018-04-13 16:24
 * @desc  json响应通知
 **/
//此方法解决跨域没有成功
//annotations  要拦截的类     basePackages要拦截的类所在的包
@ControllerAdvice(basePackages = "com.taotao.util.spring.exetend.converter.json.CallbackMappingJackson2HttpMessageConverter")
public class JsonResponseAdvice   extends AbstractJsonpResponseBodyAdvice {
    //定义默认空参
    public JsonResponseAdvice() {
        //通过父类的构造函数  传递回调函数在参数列表中的位置
        super("callback","jsonp");
    }
}



