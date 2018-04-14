package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author 芳芳
 * @create 2018-04-07 14:08
 * @desc
 *
 * spring父子容器
 * 子可以访问父的bean（配置文件在spring容器加载）  在springMVC我们想使用这些资源需要先在spring的bean中注入（然后使用）
 **/
@Service
public class PropertiesService{
	 //jackson对象 : 将java对象手动转json，解析json格式的数据，转成java对象
	public static final ObjectMapper MAPPER = new ObjectMapper();
	 //@Value注解：获取当前容器中某个key对应的value
	//图片上传的目录路径
    @Value("${TT_MANAGE_IMAGE_PATH}")
    public String IMAGE_PATH;
    //图片访问的域名配置
    @Value("${TT_MANAGE_IMAGE_URL}")
    public String IMAGE_URL;
}
