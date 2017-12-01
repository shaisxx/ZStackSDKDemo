package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.CreateZoneAction;
import org.zstack.sdk.DeleteZoneAction;
import org.zstack.sdk.QueryZoneAction;
import org.zstack.sdk.UpdateZoneAction;
import org.zstack.sdk.ZoneInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 4 硬件设施-区域相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class ZoneAction {

	/**
	 * 4.1.1 创建区域
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateZoneAction.Result createZone(String name, String description, String sessionId){
		CreateZoneAction action = new CreateZoneAction();
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		CreateZoneAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createZone error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ZoneInventory inventory = res.value.inventory;
			System.out.println(String.format("createZone successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.1.2 删除区域
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteZoneAction.Result deleteZone(String uuid, String sessionId){
		DeleteZoneAction action = new DeleteZoneAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteZoneAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteZone error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteZone successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.1.3 查询区域
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryZoneAction.Result queryZone(String name, String sessionId){
		QueryZoneAction action = new QueryZoneAction();
		action.conditions = Arrays.asList("name=" + name,"state=Enabled");
		action.sessionId = sessionId;
		QueryZoneAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryZone error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ZoneInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryZone successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.1.4 更新区域
	 * @param name
	 * @param description
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateZoneAction.Result updateZone(String name, String description, String uuid, String sessionId){
		UpdateZoneAction action = new UpdateZoneAction();
		action.name = name;
		action.description = description;
		action.uuid = uuid;
		action.sessionId = sessionId;
		UpdateZoneAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateZone error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ZoneInventory inventory = res.value.inventory;
			System.out.println(String.format("updateZone successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		ZoneAction zone = new ZoneAction();
		
		// 创建区域 Begin
//		zone.createZone("lanyanZone1130", "lanyan-test", sessionId);
		// 创建区域 End
		
		// 查询区域 Begin
		zone.queryZone("lanyanZone1130", sessionId);
		// 查询区域 End
		
		// 更新区域信息 Begin
		zone.updateZone("lanyanZonenew", "lanyanZonenew desc", "35cdfb95ca9745f58a37584c488c8ccb", sessionId);
		// 更新区域信息 End
		
		// 删除区域 Begin
		zone.deleteZone("3edd1501678640f1abdaa609a3bf518c", sessionId);
		// 删除区域 End
	}
}