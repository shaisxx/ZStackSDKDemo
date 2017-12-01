package com.shtd.zstack.cloudresource;

import java.util.Arrays;

import org.zstack.sdk.ChangeDiskOfferingStateAction;
import org.zstack.sdk.CreateDiskOfferingAction;
import org.zstack.sdk.DeleteDiskOfferingAction;
import org.zstack.sdk.DiskOfferingInventory;
import org.zstack.sdk.QueryDiskOfferingAction;
import org.zstack.sdk.UpdateDiskOfferingAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 云资源池-云盘规格相关接口
 * @author Josh
 * @date 2017-11-30
 */
public class DiskOfferingAction {

	/**
	 * 3.5.1 创建云盘规格
	 * @param name
	 * @param diskSize
	 * @param sortKey
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateDiskOfferingAction.Result createDiskOffering(String name, long diskSize, int sortKey, String sessionId){
		CreateDiskOfferingAction action = new CreateDiskOfferingAction();
		action.name = name;
		action.diskSize = diskSize;
		action.sortKey = sortKey;
		action.sessionId = sessionId;
		CreateDiskOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createDiskOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			DiskOfferingInventory inventory = res.value.inventory;
			System.out.println(String.format("createDiskOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.5.2 删除云盘规格
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteDiskOfferingAction.Result deleteDiskOffering(String uuid, String sessionId){
		DeleteDiskOfferingAction action = new DeleteDiskOfferingAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteDiskOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteDiskOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteDiskOffering successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 3.5.3 查询云盘规格
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryDiskOfferingAction.Result queryDiskOffering(String uuid, String sessionId){
		QueryDiskOfferingAction action = new QueryDiskOfferingAction();
		action.conditions = Arrays.asList("uuid=e01b8a266d05425ba2d0c05308763db8");
		action.sessionId = "d8a3f3b62d8c4affab7c5d491f95d4de";
		QueryDiskOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryDiskOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				DiskOfferingInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryDiskOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 3.5.4 更改云盘规格的启用状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeDiskOfferingStateAction.Result changeDiskOfferingState(String uuid, String stateEvent, String sessionId){
		ChangeDiskOfferingStateAction action = new ChangeDiskOfferingStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangeDiskOfferingStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeDiskOfferingState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			DiskOfferingInventory inventory = res.value.inventory;
			System.out.println(String.format("changeDiskOfferingState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.5.5 更新云盘规格
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateDiskOfferingAction.Result updateDiskOffering(String uuid, String name, String sessionId){
		UpdateDiskOfferingAction action = new UpdateDiskOfferingAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateDiskOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateDiskOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			DiskOfferingInventory inventory = res.value.inventory;
			System.out.println(String.format("updateDiskOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		DiskOfferingAction diskOffering = new DiskOfferingAction();
		
		// 创建云盘规格 Begin
		diskOffering.createDiskOffering("diskOffering1", Long.valueOf("100.0"), 0, sessionId);
		// 创建云盘规格 End
		
		// 查询云盘规格 Begin
		diskOffering.queryDiskOffering("uuid", sessionId);
		// 查询云盘规格 End
		
		// 更改云盘规格的启用状态 Begin
		diskOffering.changeDiskOfferingState("uuid", "enable", sessionId);
		// 更改云盘规格的启用状态 End
		
		// 更新云盘规格 Begin
		diskOffering.updateDiskOffering("uuid", "newdiskOffering1", sessionId);
		// 更新云盘规格 End
		
		// 删除云盘规格 Begin
		diskOffering.deleteDiskOffering("uuid", sessionId);
		// 删除云盘规格 End
	}
}