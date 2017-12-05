package com.shtd.zstack.sysglobal;

import org.zstack.sdk.GetTaskProgressAction;
import org.zstack.sdk.TaskProgressInventory;

/**
 * 8 系统全局相关-进度条相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class TaskProgressAction {
	
	/**
	 * 8.3.1 获取任务进度
	 * @param apiId
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetTaskProgressAction.Result getTaskProgress(String apiId, String sessionId){
		GetTaskProgressAction action = new GetTaskProgressAction();
		action.apiId = apiId;
		action.all = false;
		action.sessionId = sessionId;
		GetTaskProgressAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getTaskProgress error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				TaskProgressInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getTaskProgress successfully taskName:%s,taskUuid:%s,content:%s,type:%s", 
						inventory.taskName, inventory.taskUuid, inventory.content, inventory.type));
			}
		}
		return res;
	}

	public static void main(String[] args) {

	}
}