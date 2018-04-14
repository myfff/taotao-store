<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘淘商城后台管理系统</title>
<jsp:include page="/commons/common-js.jsp"></jsp:include>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body>
	<div id="master-layout">
		<div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
        	<div class="theme-navigate">
                <div class="left">
                </div>
                <div class="right">
                    <a href="#" class="easyui-menubutton theme-navigate-more-button" data-options="menu:'#more',hasDownArrow:false"></a>
                    <div id="more" class="theme-navigate-more-panel">
                    	<div>联系管理员</div>
                        <div>安全退出</div>
                        <div>关于</div>
                    </div>
                </div>
            </div>
        </div>
		 <div data-options="region:'west',title:'菜单',split:true,border:false" style="width:200px; padding:10px 10px;">
	    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
	         	<li>
	         		<span>商品管理</span>
	         		<ul>
		         		<li data-options="attributes:{'url':'/rest/page/item-add'}">新增商品</li>
		         		<li data-options="attributes:{'url':'/rest/page/item-list'}">查询商品</li>
		         		<li data-options="attributes:{'url':'/rest/page/item-param-list'}">规格参数</li>
		         	</ul>
	         	</li>
	         	<li>
	         		<span>网站内容管理</span>
	         		<ul>
		         		<li data-options="attributes:{'url':'/rest/page/content-category'}">内容分类管理</li>
		         		<li data-options="attributes:{'url':'/rest/page/content'}">内容管理</li>
		         	</ul>
	         	</li>
	         </ul>
	    </div>
	    
	    <div data-options="region:'center',title:'',border:false">
	    	<div id="tabs" class="easyui-tabs" style="width:100%;height:100%">
			    <div title="首页" style="padding:20px;" data-options="fit:false,border:false,href:'/js/EasyUI_of_InsdepTheme-1.5.1-1.0.0-rc2/info.html'">
			        	
			    </div>
			</div>
	    </div>
	</div>
   
    
    <!--第三方插件加载-->
    <script src="/js/EasyUI_of_InsdepTheme-1.5.1-1.0.0-rc2/plugin/justgage-1.2.2/raphael-2.1.4.min.js"></script>
    <script src="/js/EasyUI_of_InsdepTheme-1.5.1-1.0.0-rc2/plugin/justgage-1.2.2/justgage.js"></script>


    <script src="/js/EasyUI_of_InsdepTheme-1.5.1-1.0.0-rc2/plugin/Highcharts-5.0.0/js/highcharts.js"></script>


    <script type="text/javascript" src="/js/EasyUI_of_InsdepTheme-1.5.1-1.0.0-rc2/plugin/ueditor-1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" src="/js/EasyUI_of_InsdepTheme-1.5.1-1.0.0-rc2/plugin/ueditor-1.4.3.3/ueditor.all.min.js"></script>


    <link href="/js/EasyUI_of_InsdepTheme-1.5.1-1.0.0-rc2/plugin/cropper-2.3.4/dist/cropper.min.css" rel="stylesheet" type="text/css">
    <script src="/js/EasyUI_of_InsdepTheme-1.5.1-1.0.0-rc2/plugin/cropper-2.3.4/dist/cropper.min.js"></script>


    <!--第三方插件加载结束-->
    
    
<script type="text/javascript">
$(function(){
	/*布局部分*/
	$('#master-layout').layout({
		fit:true/*布局框架全屏*/
	});   
	
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
</script>
</body>
</html>