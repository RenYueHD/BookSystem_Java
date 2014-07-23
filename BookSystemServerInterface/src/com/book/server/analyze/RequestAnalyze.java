package com.book.server.analyze;
import com.book.enums.ResponseTypeEnum;
import com.book.message.BookRequest;
import com.book.message.BookResponse;
import com.book.server.analyze.filter.AuthorizeFilterAttribute;
import com.book.server.analyze.filter.CustomFilterAttribute;
import com.book.server.analyze.filter.IRequestAuthorizeFilter;
import com.book.server.analyze.filter.IRequestCustomFilter;
import com.book.server.analyze.filter.IRequestIngressFilter;
import com.book.server.analyze.filter.IRequestOutgressFilter;
import com.book.server.analyze.filter.IngressFilterAttribute;
import com.book.server.analyze.filter.OutgressFilterAttribute;
import com.book.server.util.ClassLoaderHelper;
import com.book.server.util.SettingHelper;

/**
 * 请求解析器
 * @author RenYue
 *
 */
public abstract class RequestAnalyze {

	/**
	 * 对传入的请求进行解析并获取响应
	 * @param request 传入的请求
	 * @return 得到的响应
	 */
	public static BookResponse getResult(BookRequest request){
		IRequestAnalyzeFacotry factory = null;
		BookResponse response  = new BookResponse();
		
		String analyzePageage = SettingHelper.loadAnalyzeJarPackage();	//解析器工厂jar包名
		ClassLoaderHelper helper = new ClassLoaderHelper(analyzePageage);
		
		try {
		//	@SuppressWarnings("unchecked")
		//	Class<IRequestAnalyzeFacotry> cls = (Class<IRequestAnalyzeFacotry>) Class.forName("com.book.server.analyze.impl.RequestAnalyzeFactory");
			//factory = cls.newInstance();
			factory = helper.newInstance(IRequestAnalyzeFacotry.class);
		} catch (Exception e) {
			System.err.println("缺少请求解析器组件或请求解析器组件无效");
			response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
			return response;
		} 
			
		IRequestAnalyze requestAnalyze=factory.getRequestAnalyze(request);
		
		//入站请求过滤器
		IngressFilterAttribute ingress = requestAnalyze.getClass().getAnnotation(IngressFilterAttribute.class);
		if(ingress !=null){
			try {
				IRequestIngressFilter filter = helper.newInstance(ingress.value());
				if(!filter.filter(request)){
					return filter.getResponse();
				}
			}  catch (Exception e) {
				System.err.println("缺少入站过滤器组件或请求过滤器组件无效");
				response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
				return response;
			} 
		}
		
		//权限过滤器
		AuthorizeFilterAttribute author = requestAnalyze.getClass().getAnnotation(AuthorizeFilterAttribute.class);
		if(author !=null){
			try {
				IRequestAuthorizeFilter filter = helper.newInstance(author.value());
				if(!filter.filter(request)){
					return filter.getResponse();
				}
			}  catch (Exception e) {
				System.err.println("缺少权限过滤器组件或权限过滤器组件无效");
				response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
				return response;
			} 
		}

		//自定义过滤器
		CustomFilterAttribute names =requestAnalyze.getClass().getAnnotation(CustomFilterAttribute.class);
		if(names !=null){
			String[] cname = names.values();
			for(String s : cname){
				try {
					IRequestCustomFilter filter =  helper.newInstance(s);
					if(!filter.filter(request)){
						return filter.getResponse();
					}
				} catch (Exception e) {
					System.err.println("缺少自定义过滤器组件或自定义过滤器组件无效");
					response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
					return response;
				} 
			}
		}
		
		//出站请求过滤器
		OutgressFilterAttribute outgress = requestAnalyze.getClass().getAnnotation(OutgressFilterAttribute.class);
		if(outgress !=null){
			try {
				IRequestOutgressFilter filter = helper.newInstance(outgress.value());
				if(!filter.filter(request)){
					return filter.getResponse();
				}
			}  catch (Exception e) {
				System.err.println("缺少出站过滤器组件或请求过滤器组件无效");
				response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
				return response;
			} 
		}
		
		return requestAnalyze.getResult();
	}
}