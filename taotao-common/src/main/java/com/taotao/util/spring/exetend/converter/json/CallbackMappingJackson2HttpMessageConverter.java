package com.taotao.util.spring.exetend.converter.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 当后台不响应string
 *
 * 同一jsonp响应格式   解决方法1
 * 而是对象时  会用MappingJackson2HttpMessageConverter进行将对象转化为json数据（失败，前台的函数是要js格式（string）来解析）
 * 我们自定义继承   然后将json进一步封装为字符串响应    去配置文件注册
 */
public  class  CallbackMappingJackson2HttpMessageConverter  extends MappingJackson2HttpMessageConverter {

	// 做jsonp的支持的标识，在请求参数中加该参数
	private String callbackName;

	public static final ObjectMapper MAPPER = new ObjectMapper();
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		// 从threadLocal中获取当前的Request对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String callbackParam = request.getParameter(callbackName);
		if(StringUtils.isEmpty(callbackParam)){
			// 没有找到callback参数，直接返回json数据
			super.writeInternal(object, outputMessage);
		}else{
			JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
			try {
				String result =callbackParam+"("+MAPPER.writeValueAsString(object)+");";
				IOUtils.write(result, outputMessage.getBody(),encoding.getJavaName());
			}
			catch (JsonProcessingException ex) {
				throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
			}
		}
		
	}

	public String getCallbackName() {
		return callbackName;
	}

	public void setCallbackName(String callbackName) {
		this.callbackName = callbackName;
	}

}
