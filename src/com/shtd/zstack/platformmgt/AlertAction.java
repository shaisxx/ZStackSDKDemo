package com.shtd.zstack.platformmgt;

import java.util.Arrays;

import org.zstack.sdk.AlertInventory;
import org.zstack.sdk.AttachMonitorTriggerActionToTriggerAction;
import org.zstack.sdk.ChangeMediaStateAction;
import org.zstack.sdk.ChangeMonitorTriggerActionStateAction;
import org.zstack.sdk.ChangeMonitorTriggerStateAction;
import org.zstack.sdk.CreateEmailMediaAction;
import org.zstack.sdk.CreateEmailMonitorTriggerActionAction;
import org.zstack.sdk.CreateMonitorTriggerAction;
import org.zstack.sdk.DeleteAlertAction;
import org.zstack.sdk.DeleteMediaAction;
import org.zstack.sdk.DeleteMonitorTriggerAction;
import org.zstack.sdk.DeleteMonitorTriggerActionAction;
import org.zstack.sdk.DetachMonitorTriggerActionFromTriggerAction;
import org.zstack.sdk.EmailMediaInventory;
import org.zstack.sdk.GetMonitorItemAction;
import org.zstack.sdk.ItemInventory;
import org.zstack.sdk.MediaInventory;
import org.zstack.sdk.MonitorTriggerActionInventory;
import org.zstack.sdk.MonitorTriggerInventory;
import org.zstack.sdk.QueryAlertAction;
import org.zstack.sdk.QueryEmailTriggerActionAction;
import org.zstack.sdk.QueryMediaAction;
import org.zstack.sdk.QueryMonitorTriggerAction;
import org.zstack.sdk.QueryMonitorTriggerActionAction;
import org.zstack.sdk.UpdateEmailMediaAction;
import org.zstack.sdk.UpdateMonitorTriggerAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 7 平台管理-监控报警相关接口-智能报警相关接口
 * @author Josh
 * @date 2017-12-06
 */
public class AlertAction {
	
	/**
	 * 7.1.1.1 创建Email媒体
	 * @param smtpServer
	 * @param smtpPort
	 * @param username
	 * @param password
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateEmailMediaAction.Result createEmailMedia(String smtpServer, Integer smtpPort, String username, 
			String password, String name, String sessionId){
		
		CreateEmailMediaAction action = new CreateEmailMediaAction();
		action.smtpServer = smtpServer;
		action.smtpPort = smtpPort;
		action.username = username;
		action.password = password;
		action.name = name;
		action.sessionId = sessionId;
		CreateEmailMediaAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createEmailMedia error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			MediaInventory inventory = res.value.inventory;
			System.out.println(String.format("createEmailMedia successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.2 更新Email媒体
	 * @param uuid
	 * @param smtpServer
	 * @param smtpPort
	 * @param username
	 * @param password
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateEmailMediaAction.Result updateEmailMedia(String uuid, String smtpServer, Integer smtpPort, 
			String username, String password, String sessionId){
		
		UpdateEmailMediaAction action = new UpdateEmailMediaAction();
		action.uuid = uuid;
		action.smtpServer = smtpServer;
		action.smtpPort = smtpPort;
		action.username = username;
		action.password = password;
		action.sessionId = sessionId;
		UpdateEmailMediaAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateEmailMedia error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			EmailMediaInventory inventory = res.value.inventory;
			System.out.println(String.format("updateEmailMedia successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
		
	/**
	 * 7.1.1.3 删除媒体
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteMediaAction.Result deleteMedia(String uuid, String sessionId){
		DeleteMediaAction action = new DeleteMediaAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteMediaAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteMedia error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteMedia successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.4 查询媒体
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryMediaAction.Result queryMedia(String name, String sessionId){
		QueryMediaAction action = new QueryMediaAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryMediaAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryMedia error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				MediaInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryMedia successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.1.1.5 改变媒体状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeMediaStateAction.Result changeMediaState(String uuid, String sessionId){
		ChangeMediaStateAction action = new ChangeMediaStateAction();
		action.uuid = uuid;
		action.stateEvent = "disable";
		action.sessionId = sessionId;
		ChangeMediaStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeMediaState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			MediaInventory inventory = res.value.inventory;
			System.out.println(String.format("changeMediaState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.6 创建Email报警动作
	 * @param email
	 * @param mediaUuid
	 * @param name
	 * @param triggerUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateEmailMonitorTriggerActionAction.Result createEmailMonitorTriggerAction(String email, String mediaUuid, String name, String triggerUuids, String sessionId){
		CreateEmailMonitorTriggerActionAction action = new CreateEmailMonitorTriggerActionAction();
		action.email = email;
		action.mediaUuid = mediaUuid;
		action.name = name;
		action.triggerUuids = Arrays.asList(triggerUuids);
		action.sessionId = sessionId;
		CreateEmailMonitorTriggerActionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createEmailMonitorTriggerAction error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			MonitorTriggerActionInventory inventory = res.value.inventory;
			System.out.println(String.format("createEmailMonitorTriggerAction successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.7 查询Email报警动作
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryEmailTriggerActionAction.Result queryEmailTriggerAction(String name, String sessionId){
		QueryEmailTriggerActionAction action = new QueryEmailTriggerActionAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryEmailTriggerActionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryEmailTriggerAction error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				MonitorTriggerActionInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryEmailTriggerAction successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.1.1.8 创建报警器
	 * @param name
	 * @param expression
	 * @param duration
	 * @param targetResourceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateMonitorTriggerAction.Result createMonitorTrigger(String name, String expression, Integer duration, String targetResourceUuid, String sessionId){
		CreateMonitorTriggerAction action = new CreateMonitorTriggerAction();
		action.name = name;
		action.expression = expression;
		action.duration = duration;
		action.targetResourceUuid = targetResourceUuid;
		action.sessionId = sessionId;
		CreateMonitorTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createMonitorTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			MonitorTriggerInventory inventory = res.value.inventory;
			System.out.println(String.format("createMonitorTrigger successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.9 删除报警器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteMonitorTriggerAction.Result deleteMonitorTrigger(String uuid, String sessionId){
		DeleteMonitorTriggerAction action = new DeleteMonitorTriggerAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteMonitorTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteMonitorTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteMonitorTrigger successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.10 查询报警器
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryMonitorTriggerAction.Result queryMonitorTrigger(String name, String sessionId){
		QueryMonitorTriggerAction action = new QueryMonitorTriggerAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryMonitorTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryMonitorTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				MonitorTriggerInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryMonitorTrigger successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.1.1.11 更新报警器
	 * @param uuid
	 * @param name
	 * @param expression
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateMonitorTriggerAction.Result updateMonitorTrigger(String uuid, String name, String expression, String sessionId){
		UpdateMonitorTriggerAction action = new UpdateMonitorTriggerAction();
		action.uuid = uuid;
		action.name = name;
		action.expression = expression;
		action.sessionId = sessionId;
		UpdateMonitorTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateMonitorTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			MonitorTriggerInventory inventory = res.value.inventory;
			System.out.println(String.format("updateMonitorTrigger successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.12 修改报警器状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeMonitorTriggerStateAction.Result changeMonitorTriggerState(String uuid, String sessionId){
		ChangeMonitorTriggerStateAction action = new ChangeMonitorTriggerStateAction();
		action.uuid = uuid;
		action.stateEvent = "disable";
		action.sessionId = sessionId;
		ChangeMonitorTriggerStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeMonitorTriggerState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			MonitorTriggerInventory inventory = res.value.inventory;
			System.out.println(String.format("changeMonitorTriggerState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.13 创建报警器动作
	 * @param name
	 * @param expression
	 * @param targetResourceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateMonitorTriggerAction.Result createMonitorTriggerAction(String name, String expression, String targetResourceUuid, String sessionId){
		CreateMonitorTriggerAction action = new CreateMonitorTriggerAction();
		action.name = name;
		action.expression = expression;
		action.targetResourceUuid = targetResourceUuid;
		action.sessionId = sessionId;
		CreateMonitorTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createMonitorTriggerAction error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			MonitorTriggerInventory inventory = res.value.inventory;
			System.out.println(String.format("createMonitorTriggerAction successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.14 删除报警器动作
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteMonitorTriggerActionAction.Result deleteMonitorTriggerAction(String uuid, String sessionId){
		DeleteMonitorTriggerActionAction action = new DeleteMonitorTriggerActionAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteMonitorTriggerActionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteMonitorTriggerAction error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteMonitorTriggerAction successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.15 查询报警器动作
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryMonitorTriggerActionAction.Result queryMonitorTriggerAction(String name, String sessionId){
		QueryMonitorTriggerActionAction action = new QueryMonitorTriggerActionAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryMonitorTriggerActionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryMonitorTriggerAction error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				MonitorTriggerActionInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryMonitorTriggerAction successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.1.1.16 更改报警器动作状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeMonitorTriggerActionStateAction.Result changeMonitorTriggerActionState(String uuid, String sessionId){
		ChangeMonitorTriggerActionStateAction action = new ChangeMonitorTriggerActionStateAction();
		action.uuid = uuid;
		action.stateEvent = "disable";
		action.sessionId = sessionId;
		ChangeMonitorTriggerActionStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeMonitorTriggerActionState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			MonitorTriggerActionInventory inventory = res.value.inventory;
			System.out.println(String.format("changeMonitorTriggerActionState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.17 加载报警动作到报警器
	 * @param triggerUuid
	 * @param actionUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachMonitorTriggerActionToTriggerAction.Result attachMonitorTriggerActionToTrigger(String triggerUuid, String actionUuid, String sessionId){
		AttachMonitorTriggerActionToTriggerAction action = new AttachMonitorTriggerActionToTriggerAction();
		action.triggerUuid = triggerUuid;
		action.actionUuid = actionUuid;
		action.sessionId = sessionId;
		AttachMonitorTriggerActionToTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachMonitorTriggerActionToTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("attachMonitorTriggerActionToTrigger successfully triggerUuid:%s,actionUuid:%s", triggerUuid, actionUuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.18 卸载报警动作
	 * @param triggerUuid
	 * @param actionUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachMonitorTriggerActionFromTriggerAction.Result detachMonitorTriggerActionFromTrigger(String triggerUuid, String actionUuid, String sessionId){
		DetachMonitorTriggerActionFromTriggerAction action = new DetachMonitorTriggerActionFromTriggerAction();
		action.triggerUuid = triggerUuid;
		action.actionUuid = actionUuid;
		action.sessionId = sessionId;
		DetachMonitorTriggerActionFromTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachMonitorTriggerActionFromTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("detachMonitorTriggerActionFromTrigger successfully triggerUuid:%s,actionUuid:%s", triggerUuid, actionUuid));
		}
		return res;
	}
	
	/**
	 * 7.1.1.19 查询报警记录
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryAlertAction.Result queryAlert(String uuid, String sessionId){
		QueryAlertAction action = new QueryAlertAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryAlertAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryAlert error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				AlertInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryAlert successfully content:%s,uuid:%s", inventory.content, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.1.1.20 删除报警记录
	 * @param uuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteAlertAction.Result deleteAlert(String uuids, String sessionId){
		DeleteAlertAction action = new DeleteAlertAction();
		action.uuids = Arrays.asList(uuids);
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteAlertAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteAlert error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteAlert successfully uuids:%s", uuids));
		}
		return res;
	}
	
	/**
	 * 7.1.1.21 获取报警条目
	 * @param resourceType
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetMonitorItemAction.Result getMonitorItem(String resourceType, String sessionId){
		GetMonitorItemAction action = new GetMonitorItemAction();
		action.resourceType = resourceType;
		action.sessionId = sessionId;
		GetMonitorItemAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getMonitorItem error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ItemInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getMonitorItem successfully name:%s,readableName:%s", inventory.name, inventory.readableName));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		AlertAction action = new AlertAction();

		// 删除媒体 Begin
		action.deleteMedia("uuid", sessionId);
		// 删除媒体 End
	}
}