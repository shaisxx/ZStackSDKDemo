package com.shtd.zstack.sysglobal;

import java.util.Arrays;

import org.zstack.sdk.DeleteGCJobAction;
import org.zstack.sdk.GarbageCollectorInventory;
import org.zstack.sdk.QueryGCJobAction;
import org.zstack.sdk.TriggerGCJobAction;

/**
 * 8 系统全局相关-垃圾回收相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class GCJobAction {
	
	/**
	 * 8.6.1 触发垃圾回收任务
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public TriggerGCJobAction.Result triggerGCJob(String uuid, String sessionId){
		TriggerGCJobAction action = new TriggerGCJobAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		TriggerGCJobAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("triggerGCJob error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("triggerGCJob successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 8.6.2 删除垃圾回收任务
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteGCJobAction.Result deleteGCJob(String uuid, String sessionId){
		DeleteGCJobAction action = new DeleteGCJobAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DeleteGCJobAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteGCJob error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteGCJob successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 8.6.3 查询垃圾回收任务
	 * @param name
	 * @param state "Enabled"
	 * @param sessionId
	 * @author Josh
	 */
	public QueryGCJobAction.Result queryGCJob(String name, String state, String sessionId){
		QueryGCJobAction action = new QueryGCJobAction();
		action.conditions = Arrays.asList("name=" + name, "state=" + state);
		action.sessionId = sessionId;
		QueryGCJobAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryGCJob error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				GarbageCollectorInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryGCJob successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}

	public static void main(String[] args) {

	}
}