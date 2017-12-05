package com.shtd.zstack.sysglobal;

import java.util.Arrays;

import org.zstack.sdk.DeleteNotificationsAction;
import org.zstack.sdk.NotificationInventory;
import org.zstack.sdk.QueryNotificationAction;
import org.zstack.sdk.UpdateNotificationsStatusAction;

/**
 * 8 系统全局相关-通知相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class NotificationAction {

	/**
	 * 8.4.1 删除通知
	 * @param uuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteNotificationsAction.Result deleteNotifications(String uuids, String sessionId){
		DeleteNotificationsAction action = new DeleteNotificationsAction();
		action.uuids = Arrays.asList(uuids);
		action.sessionId = sessionId;
		DeleteNotificationsAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteNotifications error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteNotifications successfully uuids:%s", uuids));
		}
		return res;
	}
	
	/**
	 * 8.4.2 查询通知
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryNotificationAction.Result queryNotification(String sessionId){
		QueryNotificationAction action = new QueryNotificationAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryNotificationAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryNotification error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				NotificationInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryNotification successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 8.4.3 更新通知状态
	 * @param uuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateNotificationsStatusAction.Result updateNotificationsStatus(String uuids, String sessionId){
		UpdateNotificationsStatusAction action = new UpdateNotificationsStatusAction();
		action.uuids = Arrays.asList(uuids);
		action.status = "Read";
		action.sessionId = sessionId;
		UpdateNotificationsStatusAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateNotificationsStatus error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("updateNotificationsStatus successfully uuids:%s", uuids));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}