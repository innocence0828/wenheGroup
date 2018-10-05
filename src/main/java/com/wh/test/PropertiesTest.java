package com.wh.test;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DateUtil;

import com.wh.time.CronDateUtils;


public class PropertiesTest {
	public static void main(String[] args) {  
        Date now = new Date();  
        System.out.println(CronDateUtils.getCron(now));  
  
        String cron = "20 28 17 02 08 ? 2016";  
  
        Date cronDate = CronDateUtils.getDate(cron);  
        System.out.println("===================");  
        System.out.println(cronDate.toString());  
        String string = CronDateUtils.getCron(now); 
        System.out.println("===================");  
        System.out.println(string);  
  
  
    }   
}