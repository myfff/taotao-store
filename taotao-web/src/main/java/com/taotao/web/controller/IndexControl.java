package com.taotao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 芳芳
 * @create 2018-04-08 22:21
 * @desc
 **/
@Controller
public class IndexControl {
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView("index");
        return  mv;
    }
}



