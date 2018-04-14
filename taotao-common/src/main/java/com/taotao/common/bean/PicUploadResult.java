package com.taotao.common.bean;

/**
 * @author 芳芳
 * @create 2018-03-21 12:26
 * @desc 后台  商品分类的图片上传的返回结果
 **/
public class PicUploadResult {
    //图片上传的状态  1 失败  0 成功
    private Integer error;
    //图片上传之后的地址
    private String url;
    //图片的宽度
    private String width;
    //图片的高度
    private String height;

    
    public PicUploadResult() {
		super();
	}

	public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
    

}
