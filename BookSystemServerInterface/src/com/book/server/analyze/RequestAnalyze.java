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
 * ���������
 * @author RenYue
 *
 */
public abstract class RequestAnalyze {

	/**
	 * �Դ����������н�������ȡ��Ӧ
	 * @param request ���������
	 * @return �õ�����Ӧ
	 */
	public static BookResponse getResult(BookRequest request){
		IRequestAnalyzeFacotry factory = null;
		BookResponse response  = new BookResponse();
		
		String analyzePageage = SettingHelper.loadAnalyzeJarPackage();	//����������jar����
		ClassLoaderHelper helper = new ClassLoaderHelper(analyzePageage);
		
		try {
		//	@SuppressWarnings("unchecked")
		//	Class<IRequestAnalyzeFacotry> cls = (Class<IRequestAnalyzeFacotry>) Class.forName("com.book.server.analyze.impl.RequestAnalyzeFactory");
			//factory = cls.newInstance();
			factory = helper.newInstance(IRequestAnalyzeFacotry.class);
		} catch (Exception e) {
			System.err.println("ȱ������������������������������Ч");
			response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
			return response;
		} 
			
		IRequestAnalyze requestAnalyze=factory.getRequestAnalyze(request);
		
		//��վ���������
		IngressFilterAttribute ingress = requestAnalyze.getClass().getAnnotation(IngressFilterAttribute.class);
		if(ingress !=null){
			try {
				IRequestIngressFilter filter = helper.newInstance(ingress.value());
				if(!filter.filter(request)){
					return filter.getResponse();
				}
			}  catch (Exception e) {
				System.err.println("ȱ����վ�������������������������Ч");
				response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
				return response;
			} 
		}
		
		//Ȩ�޹�����
		AuthorizeFilterAttribute author = requestAnalyze.getClass().getAnnotation(AuthorizeFilterAttribute.class);
		if(author !=null){
			try {
				IRequestAuthorizeFilter filter = helper.newInstance(author.value());
				if(!filter.filter(request)){
					return filter.getResponse();
				}
			}  catch (Exception e) {
				System.err.println("ȱ��Ȩ�޹����������Ȩ�޹����������Ч");
				response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
				return response;
			} 
		}

		//�Զ��������
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
					System.err.println("ȱ���Զ��������������Զ�������������Ч");
					response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
					return response;
				} 
			}
		}
		
		//��վ���������
		OutgressFilterAttribute outgress = requestAnalyze.getClass().getAnnotation(OutgressFilterAttribute.class);
		if(outgress !=null){
			try {
				IRequestOutgressFilter filter = helper.newInstance(outgress.value());
				if(!filter.filter(request)){
					return filter.getResponse();
				}
			}  catch (Exception e) {
				System.err.println("ȱ�ٳ�վ�������������������������Ч");
				response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
				return response;
			} 
		}
		
		return requestAnalyze.getResult();
	}
}