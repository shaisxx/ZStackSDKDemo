package com.shtd.zstack.sysglobal;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import org.zstack.sdk.GetCurrentTimeAction;
import org.zstack.sdk.GetVersionAction;
import org.zstack.sdk.IsReadyToGoAction;
import org.zstack.sdk.ManagementNodeInventory;
import org.zstack.sdk.QueryManagementNodeAction;

/**
 * 8 系统全局相关-管理节点相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class ManagementNodeAction {

	/**
	 * 8.1.1 查询管理节点
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryManagementNodeAction.Result queryManagementNode(String uuid, String sessionId){
		QueryManagementNodeAction action = new QueryManagementNodeAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryManagementNodeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryManagementNode error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ManagementNodeInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryManagementNode successfully hostName:%s,uuid:%s", inventory.hostName, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 8.1.2 获取当前版本
	 * @return
	 * @author Josh
	 */
	public GetVersionAction.Result getVersion(){
		GetVersionAction action = new GetVersionAction();
		GetVersionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVersion error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getVersion successfully version:%s", res.value.version));
		}
		return res;
	}
	
	/**
	 * 8.1.3 获取当前时间
	 * @return
	 * @author Josh
	 */
	public GetCurrentTimeAction.Result getCurrentTime(){
		GetCurrentTimeAction action = new GetCurrentTimeAction();
		GetCurrentTimeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getCurrentTime error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			Map<String, Long> map = res.value.currentTime;
			for (Entry<String, Long> entry: map.entrySet()) {
				System.out.println(String.format("getCurrentTime successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	/**
	 * 8.1.4 检查管理节点是否能正常开始工作
	 * @param managementNodeId
	 * @return
	 * @author Josh
	 */
	public IsReadyToGoAction.Result isReadyToGo(String managementNodeId){
		IsReadyToGoAction action = new IsReadyToGoAction();
		action.managementNodeId = managementNodeId;
		IsReadyToGoAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("isReadyToGo error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("isReadyToGo successfully managementNodeId:%s", managementNodeId));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}