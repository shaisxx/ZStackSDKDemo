package com.shtd.zstack.platformmgt;

import java.util.Arrays;

import org.zstack.sdk.AddSchedulerJobToSchedulerTriggerAction;
import org.zstack.sdk.CreateSchedulerJobAction;
import org.zstack.sdk.CreateSchedulerTriggerAction;
import org.zstack.sdk.DeleteSchedulerJobAction;
import org.zstack.sdk.DeleteSchedulerTriggerAction;
import org.zstack.sdk.QuerySchedulerJobAction;
import org.zstack.sdk.QuerySchedulerTriggerAction;
import org.zstack.sdk.RemoveSchedulerJobFromSchedulerTriggerAction;
import org.zstack.sdk.SchedulerJobInventory;
import org.zstack.sdk.SchedulerJobSchedulerTriggerInventory;
import org.zstack.sdk.SchedulerTriggerInventory;
import org.zstack.sdk.UpdateSchedulerJobAction;
import org.zstack.sdk.UpdateSchedulerTriggerAction;

/**
 * 7 平台管理-定时器相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class SchedulerTriggerAction {

	/**
	 * 7.3.1 创建定时任务
	 * @param name
	 * @param targetResourceUuid
	 * @param type
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateSchedulerJobAction.Result createSchedulerJob(String name, String targetResourceUuid, String type, String sessionId){
		CreateSchedulerJobAction action = new CreateSchedulerJobAction();
		action.name = name;
		action.targetResourceUuid = targetResourceUuid;
		action.type = type;
		action.sessionId = sessionId;
		CreateSchedulerJobAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createSchedulerJob error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			SchedulerJobInventory inventory = res.value.inventory;
			System.out.println(String.format("createSchedulerJob successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.3.2 删除定时任务
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteSchedulerJobAction.Result deleteSchedulerJob(String uuid, String sessionId){
		DeleteSchedulerJobAction action = new DeleteSchedulerJobAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteSchedulerJobAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteSchedulerJob error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteSchedulerJob successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.3.3 查询定时任务Enabled
	 * @param name
	 * @param state
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QuerySchedulerJobAction.Result querySchedulerJob(String name, String state, String sessionId){
		QuerySchedulerJobAction action = new QuerySchedulerJobAction();
		action.conditions = Arrays.asList("name=" + name ,"state=" + state);
		action.sessionId = sessionId;
		QuerySchedulerJobAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("querySchedulerJob error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				SchedulerJobInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("querySchedulerJob successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.3.4 更新定时任务
	 * @param uuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateSchedulerJobAction.Result updateSchedulerJob(String uuid, String name, String description, String sessionId){
		UpdateSchedulerJobAction action = new UpdateSchedulerJobAction();
		action.uuid = uuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		UpdateSchedulerJobAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateSchedulerJob error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			SchedulerJobInventory inventory = res.value.inventory;
			System.out.println(String.format("updateSchedulerJob successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.3.5 创建定时器
	 * @param name
	 * @param schedulerInterval
	 * @param repeatCount
	 * @param startTime
	 * @param schedulerType
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateSchedulerTriggerAction.Result createSchedulerTrigger(String name, Integer schedulerInterval, Integer repeatCount, 
			Long startTime, String schedulerType, String sessionId){
		
		CreateSchedulerTriggerAction action = new CreateSchedulerTriggerAction();
		action.name = name;
		action.schedulerInterval = schedulerInterval;
		action.repeatCount = repeatCount;
		action.startTime = startTime;
		action.schedulerType = schedulerType;
		action.sessionId = sessionId;
		CreateSchedulerTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createSchedulerTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			SchedulerTriggerInventory inventory = res.value.inventory;
			System.out.println(String.format("createSchedulerTrigger successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.3.6 删除定时器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteSchedulerTriggerAction.Result deleteSchedulerTrigger(String uuid, String sessionId){
		DeleteSchedulerTriggerAction action = new DeleteSchedulerTriggerAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteSchedulerTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteSchedulerTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteSchedulerTrigger successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.3.7 查询定时器
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QuerySchedulerTriggerAction.Result querySchedulerTrigger(String name, String sessionId){
		QuerySchedulerTriggerAction action = new QuerySchedulerTriggerAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QuerySchedulerTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("querySchedulerTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				SchedulerTriggerInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("querySchedulerTrigger successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.3.8 更新定时器
	 * @param uuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateSchedulerTriggerAction.Result updateSchedulerTrigger(String uuid, String name, String description, String sessionId){
		UpdateSchedulerTriggerAction action = new UpdateSchedulerTriggerAction();
		action.uuid = uuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		UpdateSchedulerTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateSchedulerTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			SchedulerTriggerInventory inventory = res.value.inventory;
			System.out.println(String.format("updateSchedulerTrigger successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.3.9 添加定时任务到定时器
	 * @param schedulerJobUuid
	 * @param schedulerTriggerUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddSchedulerJobToSchedulerTriggerAction.Result addSchedulerJobToSchedulerTrigger(String schedulerJobUuid, String schedulerTriggerUuid, String sessionId){
		AddSchedulerJobToSchedulerTriggerAction action = new AddSchedulerJobToSchedulerTriggerAction();
		action.schedulerJobUuid = schedulerJobUuid;
		action.schedulerTriggerUuid = schedulerTriggerUuid;
		action.sessionId = sessionId;
		AddSchedulerJobToSchedulerTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addSchedulerJobToSchedulerTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			SchedulerJobSchedulerTriggerInventory inventory = res.value.inventory;
			System.out.println(String.format("addSchedulerJobToSchedulerTrigger successfully uuid:%s", inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.3.10 从定时器移除定时任务
	 * @param schedulerJobUuid
	 * @param schedulerTriggerUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RemoveSchedulerJobFromSchedulerTriggerAction.Result removeSchedulerJobFromSchedulerTrigger(String schedulerJobUuid, String schedulerTriggerUuid, String sessionId){
		RemoveSchedulerJobFromSchedulerTriggerAction action = new RemoveSchedulerJobFromSchedulerTriggerAction();
		action.schedulerJobUuid = schedulerJobUuid;
		action.schedulerTriggerUuid = schedulerTriggerUuid;
		action.sessionId = sessionId;
		RemoveSchedulerJobFromSchedulerTriggerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("removeSchedulerJobFromSchedulerTrigger error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("removeSchedulerJobFromSchedulerTrigger successfully schedulerJobUuid:%s,schedulerTriggerUuid:%s", schedulerJobUuid, schedulerTriggerUuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}