package com.wh.webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * 文荷公告接口
 * @author Thinkpad-e470
 *
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WHPubliceInterface {
	 /**
     * 执行测试的WebService方法(有参)
     * 
     * @param name
     */
    @WebMethod
	public String getName(@WebParam(name = "name") String name);
    
    /**
     * 执行测试的WebService方法(有参执行测试的WebService方法(用于时间校验)
     * 
     * @param sex 性别
     * @return 
     */
    @WebMethod
	public String getSex(@WebParam(name = "sex") String sex);
	

}
