package com.taotao.manage.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @author 芳芳
 * @create 2018-03-01 14:08
 * @desc
 **/
@RequestMapping("page")
@Controller
public class PageControl {
    @RequestMapping(value = "{pageName}",method = RequestMethod.GET)
    public  String toPage(@PathVariable("pageName") String pageName){
        return pageName;
    }
}
