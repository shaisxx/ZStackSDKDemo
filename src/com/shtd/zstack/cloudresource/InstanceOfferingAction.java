package com.shtd.zstack.cloudresource;

import java.util.Arrays;

import org.zstack.sdk.ChangeInstanceOfferingAction;
import org.zstack.sdk.ChangeInstanceOfferingStateAction;
import org.zstack.sdk.CreateInstanceOfferingAction;
import org.zstack.sdk.DeleteInstanceOfferingAction;
import org.zstack.sdk.InstanceOfferingInventory;
import org.zstack.sdk.QueryInstanceOfferingAction;
import org.zstack.sdk.UpdateInstanceOfferingAction;
import org.zstack.sdk.VmInstanceInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 云资源池-计算规格相关接口
 * @author Josh
 * @date 2017-11-30
 */
public class InstanceOfferingAction {

	/**
	 * 3.4.1 创建云主机规格
	 * @param name
	 * @param cpuNum
	 * @param memorySize
	 * @param sortKey
	 * @param type
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateInstanceOfferingAction.Result createInstanceOffering(String name, int cpuNum, long memorySize, 
			int sortKey, String type, String sessionId){
		
		CreateInstanceOfferingAction action = new CreateInstanceOfferingAction();
		action.name = name;
		action.cpuNum = cpuNum;
		action.memorySize = memorySize;
		action.sortKey = sortKey;
		action.type = type;
		action.sessionId = sessionId;
		CreateInstanceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createInstanceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			InstanceOfferingInventory inventory = res.value.inventory;
			System.out.println(String.format("createInstanceOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.4.2 删除云主机规格
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteInstanceOfferingAction.Result deleteInstanceOffering(String uuid, String sessionId){
		DeleteInstanceOfferingAction action = new DeleteInstanceOfferingAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteInstanceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteInstanceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteInstanceOffering successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 3.4.3 查询云主机规格
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryInstanceOfferingAction.Result queryInstanceOffering(String uuid, String sessionId){
		QueryInstanceOfferingAction action = new QueryInstanceOfferingAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryInstanceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryInstanceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			java.util.List<InstanceOfferingInventory> inventories = res.value.inventories;
			if(inventories != null && inventories.size() > 0){
				for(InstanceOfferingInventory inventory: inventories){
					System.out.println(String.format("queryInstanceOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.4.4 更改云主机规格
	 * @param vmInstanceUuid
	 * @param instanceOfferingUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeInstanceOfferingAction.Result changeInstanceOffering(String vmInstanceUuid, String instanceOfferingUuid, String sessionId){
		ChangeInstanceOfferingAction action = new ChangeInstanceOfferingAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.instanceOfferingUuid = instanceOfferingUuid;
		action.sessionId = sessionId;
		ChangeInstanceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeInstanceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("changeInstanceOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.4.5 更新云主机规格
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateInstanceOfferingAction.Result updateInstanceOffering(String uuid, String name, String sessionId){
		UpdateInstanceOfferingAction action = new UpdateInstanceOfferingAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateInstanceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateInstanceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			InstanceOfferingInventory inventory = res.value.inventory;
			System.out.println(String.format("updateInstanceOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.4.6 更改云主机规格的启用状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeInstanceOfferingStateAction.Result changeInstanceOfferingState(String uuid, String stateEvent, String sessionId){
		ChangeInstanceOfferingStateAction action = new ChangeInstanceOfferingStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangeInstanceOfferingStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeInstanceOfferingState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			InstanceOfferingInventory inventory = res.value.inventory;
			System.out.println(String.format("changeInstanceOfferingState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		InstanceOfferingAction instanceOffering = new InstanceOfferingAction();
		
		// 创建云主机规格 Begin
		instanceOffering.createInstanceOffering("instanceOffering", 2, Long.valueOf("2052.0"), 0, "UserVm", sessionId);
		// 创建云主机规格 End
		
		// 查询云主机规格 Begin
		instanceOffering.queryInstanceOffering("uuid", sessionId);
		// 查询云主机规格 End
		
		// 更改云主机规格 Begin
		instanceOffering.changeInstanceOffering("vmInstanceUuid", "instanceOfferingUuid", sessionId);
		// 更改云主机规格 End
		
		// 更新云主机规格 Begin
		instanceOffering.updateInstanceOffering("uuid", "newName", sessionId);
		// 更新云主机规格 End
		
		// 更改云主机规格的启用状态 Begin
		instanceOffering.changeInstanceOfferingState("uuid", "enable", sessionId);
		// 更改云主机规格的启用状态 End
		
		// 删除云主机规格 Begin
		instanceOffering.deleteInstanceOffering("uuid", sessionId);
		// 删除云主机规格 End
	}
}