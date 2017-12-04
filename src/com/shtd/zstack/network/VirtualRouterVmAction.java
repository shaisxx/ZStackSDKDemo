package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.ApplianceVmInventory;
import org.zstack.sdk.CreateVirtualRouterOfferingAction;
import org.zstack.sdk.InstanceOfferingInventory;
import org.zstack.sdk.QueryApplianceVmAction;
import org.zstack.sdk.QueryVirtualRouterOfferingAction;
import org.zstack.sdk.QueryVirtualRouterVmAction;
import org.zstack.sdk.ReconnectVirtualRouterAction;
import org.zstack.sdk.VirtualRouterOfferingInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 5 网络-云路由相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class VirtualRouterVmAction {

	/**
	 * 5.3.1 创建云路由
	 * @param managementNetworkUuid
	 * @param publicNetworkUuid
	 * @param name
	 * @param instanceOfferingUuid
	 * @param imageUuid
	 * @param clusterUuid
	 * @param description
	 * @param strategy
	 * @param sessionId
	 * @author Josh
	 */
	public void createVirtualRouterVm(String managementNetworkUuid, String publicNetworkUuid, String name, 
			String instanceOfferingUuid, String imageUuid, String clusterUuid, String description, 
			String strategy, String sessionId){
		
		/*CreateVirtualRouterVmAction action = new CreateVirtualRouterVmAction();
		action.managementNetworkUuid = managementNetworkUuid;
		action.publicNetworkUuid = publicNetworkUuid;
		action.name = name;
		action.instanceOfferingUuid = instanceOfferingUuid;
		action.imageUuid = imageUuid;
		action.clusterUuid = clusterUuid;
		action.description = description;
		action.strategy = strategy;
		action.sessionId = sessionId;
		CreateVirtualRouterVmAction.Result res = action.call();*/
	}
	
	/**
	 * 5.3.2 查询云路由
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVirtualRouterVmAction.Result queryVirtualRouterVm(String name, String sessionId){
		QueryVirtualRouterVmAction action = new QueryVirtualRouterVmAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryVirtualRouterVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVirtualRouterVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		}else{
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ApplianceVmInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVirtualRouterVm successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.3.3 重连云路由
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ReconnectVirtualRouterAction.Result reconnectVirtualRouter(String vmInstanceUuid, String sessionId){
		ReconnectVirtualRouterAction action = new ReconnectVirtualRouterAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		ReconnectVirtualRouterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reconnectVirtualRouter error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ApplianceVmInventory inventory = res.value.inventory;
			System.out.println(String.format("reconnectVirtualRouter successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.3.4 创建云路由规格
	 * @param zoneUuid
	 * @param managementNetworkUuid
	 * @param imageUuid
	 * @param publicNetworkUuid
	 * @param isDefault
	 * @param name
	 * @param cpuNum
	 * @param memorySize
	 * @param sortKey
	 * @param type
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateVirtualRouterOfferingAction.Result createVirtualRouterOffering(String zoneUuid, String managementNetworkUuid, 
			String imageUuid, String publicNetworkUuid, Boolean isDefault, String name, int cpuNum, long memorySize, int sortKey, 
			String type, String sessionId){
		
		CreateVirtualRouterOfferingAction action = new CreateVirtualRouterOfferingAction();
		action.zoneUuid = zoneUuid;
		action.managementNetworkUuid = managementNetworkUuid;
		action.imageUuid = imageUuid;
		action.publicNetworkUuid = publicNetworkUuid;
		action.isDefault = isDefault;
		action.name = name;
		action.cpuNum = cpuNum;
		action.memorySize = memorySize;
		action.sortKey = sortKey;
		action.type = type;
		action.sessionId = sessionId;
		CreateVirtualRouterOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createVirtualRouterOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			InstanceOfferingInventory inventory = res.value.inventory;
			System.out.println(String.format("createVirtualRouterOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.3.5 查询云路由规格
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVirtualRouterOfferingAction.Result queryVirtualRouterOffering(String sessionId){
		QueryVirtualRouterOfferingAction action = new QueryVirtualRouterOfferingAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryVirtualRouterOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVirtualRouterOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		}else{
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VirtualRouterOfferingInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVirtualRouterOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.3.6 查询云路由设备
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryApplianceVmAction.Result queryApplianceVm(String uuid, String sessionId){
		QueryApplianceVmAction action = new QueryApplianceVmAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryApplianceVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryApplianceVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		}else{
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ApplianceVmInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryApplianceVm successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		VirtualRouterVmAction virtualRouterVmAction = new VirtualRouterVmAction();
		
		// 查询云路由 Begin
		virtualRouterVmAction.queryVirtualRouterVm("", sessionId);
		// 查询云路由 End
	}
}