package com.wh.webService.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.wh.webService.WHPubliceInterface;
@WebService(endpointInterface = "com.wh.webService.WHPubliceInterface")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WHPubliceClass implements WHPubliceInterface {

	@Override
	public String getName(String name) {
		// TODO Auto-generated method stub
		Date date = new Date();
		return name+"时间"+date;
	}

	@Override
	public String getSex(String sex) {
		Date date = new Date();
		return sex+"时间"+date;
	}

}
