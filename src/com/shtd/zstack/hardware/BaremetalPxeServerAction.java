package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.BaremetalPxeServerInventory;
import org.zstack.sdk.CreateBaremetalPxeServerAction;
import org.zstack.sdk.DeleteBaremetalPxeServerAction;
import org.zstack.sdk.QueryBaremetalPxeServerAction;
import org.zstack.sdk.StartBaremetalPxeServerAction;
import org.zstack.sdk.StopBaremetalPxeServerAction;
import org.zstack.sdk.UpdateBaremetalPxeServerAction;

/**
 * 4 硬件设施-裸机管理相关接口-安装服务相关接口
 * @author Josh
 * @date 2017-12-01
 */
public class BaremetalPxeServerAction {

	/**
	 * 4.6.1.1 创建PXE服务
	 * @param name
	 * @param dhcpInterface
	 * @param dhcpRangeBegin
	 * @param dhcpRangeEnd
	 * @param dhcpRangeNetmask
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateBaremetalPxeServerAction.Result createBaremetalPxeServer(String name, String dhcpInterface, String dhcpRangeBegin, 
			String dhcpRangeEnd, String dhcpRangeNetmask, String sessionId){
		
		CreateBaremetalPxeServerAction action = new CreateBaremetalPxeServerAction();
		action.name = name;
		action.dhcpInterface = dhcpInterface;
		action.dhcpRangeBegin = dhcpRangeBegin;
		action.dhcpRangeEnd = dhcpRangeEnd;
		action.dhcpRangeNetmask = dhcpRangeNetmask;
		action.sessionId = sessionId;
		CreateBaremetalPxeServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createBaremetalPxeServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BaremetalPxeServerInventory inventory = res.value.inventory;
			System.out.println(String.format("createBaremetalPxeServer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.1.2 删除PXE服务
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteBaremetalPxeServerAction.Result deleteBaremetalPxeServer(String uuid, String sessionId){
		DeleteBaremetalPxeServerAction action = new DeleteBaremetalPxeServerAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteBaremetalPxeServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteBaremetalPxeServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteBaremetalPxeServer successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.1.3 查询PXE服务
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryBaremetalPxeServerAction.Result queryBaremetalPxeServer(String uuid, String sessionId){
		QueryBaremetalPxeServerAction action = new QueryBaremetalPxeServerAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryBaremetalPxeServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryBaremetalPxeServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				BaremetalPxeServerInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryBaremetalPxeServer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.6.1.4 更新PXE服务
	 * @param uuid
	 * @param name
	 * @param dhcpRangeBegin
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateBaremetalPxeServerAction.Result updateBaremetalPxeServer(String uuid, String name, String dhcpRangeBegin, String sessionId){
		UpdateBaremetalPxeServerAction action = new UpdateBaremetalPxeServerAction();
		action.uuid = uuid;
		action.name = name;
		action.dhcpRangeBegin = dhcpRangeBegin;
		action.sessionId = sessionId;
		UpdateBaremetalPxeServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateBaremetalPxeServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BaremetalPxeServerInventory inventory = res.value.inventory;
			System.out.println(String.format("updateBaremetalPxeServer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.1.5 启动PXE服务
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public StartBaremetalPxeServerAction.Result startBaremetalPxeServer(String uuid, String sessionId){
		StartBaremetalPxeServerAction action = new StartBaremetalPxeServerAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		StartBaremetalPxeServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("startBaremetalPxeServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("startBaremetalPxeServer successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.1.6 停止PXE服务
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public StopBaremetalPxeServerAction.Result stopBaremetalPxeServer(String uuid, String sessionId){
		StopBaremetalPxeServerAction action = new StopBaremetalPxeServerAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		StopBaremetalPxeServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("stopBaremetalPxeServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("stopBaremetalPxeServer successfully uuid:%s", uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}