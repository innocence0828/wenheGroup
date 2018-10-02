package com.wh.time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.wh.entity.Result;
import com.wh.service.app.PushService;

public class MyPushJob implements Job{
    private static String appId = "IY512oNzvM6zj7QPP3IzH";
    private static String appKey = "nhE0xz0zr99aL1oE0HMRF8";
    private static String masterSecret = "Gi3u8nxZcBAVYhTRaCxkk2";
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println(new Date() + ": job 1 doing something...");
        String pushId = jobExecutionContext.getTrigger().getJobKey().getName();
        String str = jobExecutionContext.getTrigger().getKey().getName();
        String cid = str.substring(0, 32);
        //移除定时任务
        QuartzManager.removeJob(pushId, str, str, str);  
        if(StringUtils.isNotBlank(cid)){
	        System.setProperty("gexin_pushList_needDetails", "true");
	        // 配置返回每个别名及其对应cid的用户状态，可选
	        // System.setProperty("gexin_pushList_needAliasDetails", "true");
	        IGtPush push = new IGtPush(null, appKey, masterSecret);
	        // 通知透传模板
	        NotificationTemplate template = notificationTemplateDemo(pushId);
	        ListMessage message = new ListMessage();
	        message.setData(template);
	        // 设置消息离线，并设置离线时间
	        message.setOffline(true);
	        // 离线有效时间，单位为毫秒，可选
	        message.setOfflineExpireTime(24 * 1000 * 3600);
	        // 配置推送目标
	        List targets = new ArrayList();
	        Target target = new Target();
	//        Target target2 = new Target();
	        target.setAppId(appId);
	        target.setClientId(cid);
	        targets.add(target);
	        // taskId用于在推送时去查找对应的message
	        String taskId = push.getContentId(message);
	        IPushResult ret = push.pushMessageToList(taskId, targets);
	        System.out.println(ret.getResponse().toString());
        }
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public NotificationTemplate notificationTemplateDemo(String  maps) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("最新的任务");
//        style.setText((String)maps.get(0).get("F_NOTE"));
        style.setText("您有一项待办任务时间已到，点按查看详情");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
      
        template.setTransmissionContent(JSON.toJSONString(new Result(true, "200", "",maps)));
        return template;
       
    }
}