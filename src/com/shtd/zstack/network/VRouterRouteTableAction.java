package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.AddVRouterRouteEntryAction;
import org.zstack.sdk.AttachVRouterRouteTableToVRouterAction;
import org.zstack.sdk.CreateVRouterRouteTableAction;
import org.zstack.sdk.DeleteVRouterRouteEntryAction;
import org.zstack.sdk.DeleteVRouterRouteTableAction;
import org.zstack.sdk.DetachVRouterRouteTableFromVRouterAction;
import org.zstack.sdk.GetVRouterRouteTableAction;
import org.zstack.sdk.QueryVRouterRouteEntryAction;
import org.zstack.sdk.QueryVRouterRouteTableAction;
import org.zstack.sdk.QueryVirtualRouterVRouterRouteTableRefAction;
import org.zstack.sdk.VRouterRouteEntryAO;
import org.zstack.sdk.VRouterRouteEntryInventory;
import org.zstack.sdk.VRouterRouteTableInventory;
import org.zstack.sdk.VirtualRouterVRouterRouteTableRefInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 5 网络-云路由相关接口-云路由路由表相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class VRouterRouteTableAction {
	
	/**
	 * 5.3.7.1 创建云路由路由表
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateVRouterRouteTableAction.Result createVRouterRouteTable(String name, String description, String sessionId){
		CreateVRouterRouteTableAction action = new CreateVRouterRouteTableAction();
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		CreateVRouterRouteTableAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createVRouterRouteTable error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VRouterRouteTableInventory inventory = res.value.inventory;
			System.out.println(String.format("createVRouterRouteTable successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.3.7.2 删除云路由路由表
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVRouterRouteTableAction.Result deleteVRouterRouteTable(String uuid, String sessionId){
		DeleteVRouterRouteTableAction action = new DeleteVRouterRouteTableAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteVRouterRouteTableAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVRouterRouteTable error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVRouterRouteTable successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.3.7.3 查询云路由路由表
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVRouterRouteTableAction.Result queryVRouterRouteTable(String sessionId){
		QueryVRouterRouteTableAction action = new QueryVRouterRouteTableAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryVRouterRouteTableAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVRouterRouteTable error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VRouterRouteTableInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVRouterRouteTable successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.3.7.4 获取路由器实时路由表
	 * @param virtualRouterVmUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVRouterRouteTableAction.Result getVRouterRouteTable(String virtualRouterVmUuid, String sessionId){
		GetVRouterRouteTableAction action = new GetVRouterRouteTableAction();
		action.virtualRouterVmUuid = virtualRouterVmUuid;
		action.sessionId = sessionId;
		GetVRouterRouteTableAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVRouterRouteTable error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VRouterRouteEntryAO inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getVRouterRouteTable successfully uuid:%s,target:%s,description:%s", inventory.uuid, inventory.target, inventory.description));
			}
		}
		return res;
	}
	
	/**
	 * 5.3.7.5 添加云路由路由表条目
	 * @param description
	 * @param type "UserStatic"
	 * @param routeTableUuid
	 * @param destination
	 * @param target
	 * @param sessionId
	 * @author Josh
	 */
	public AddVRouterRouteEntryAction.Result addVRouterRouteEntry(String description, String type, String routeTableUuid, 
			String destination, String target, String sessionId){
		
		AddVRouterRouteEntryAction action = new AddVRouterRouteEntryAction();
		action.description = description;
		action.type = type;
		action.routeTableUuid = routeTableUuid;
		action.destination = destination;
		action.target = target;
		action.sessionId = sessionId;
		AddVRouterRouteEntryAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addVRouterRouteEntry error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VRouterRouteEntryInventory inventory = res.value.inventory;
			System.out.println(String.format("addVRouterRouteEntry successfully uuid:%s,target:%s,description:%s", inventory.uuid, inventory.target, inventory.description));
		}
		return res;
	}
	
	/**
	 * 5.3.7.6 删除云路由路由表条目
	 * @param uuid
	 * @param routeTableUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVRouterRouteEntryAction.Result deleteVRouterRouteEntry(String uuid, String routeTableUuid, String sessionId){
		DeleteVRouterRouteEntryAction action = new DeleteVRouterRouteEntryAction();
		action.uuid = uuid;
		action.routeTableUuid = routeTableUuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteVRouterRouteEntryAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVRouterRouteEntry error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVRouterRouteEntry successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.3.7.7 查询云路由路由条目
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVRouterRouteEntryAction.Result queryVRouterRouteEntry(String sessionId) {
		QueryVRouterRouteEntryAction action = new QueryVRouterRouteEntryAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryVRouterRouteEntryAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVRouterRouteEntry error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VRouterRouteEntryInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVRouterRouteEntry successfully uuid:%s,target:%s,description:%s", inventory.uuid, inventory.target, inventory.description));
			}
		}
		return res;
	}
	
	/**
	 * 5.3.7.8 绑定云路由路由表到云路由设备
	 * @param routeTableUuid
	 * @param virtualRouterVmUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachVRouterRouteTableToVRouterAction.Result attachVRouterRouteTableToVRouter(String routeTableUuid, String virtualRouterVmUuid, String sessionId){
		AttachVRouterRouteTableToVRouterAction action = new AttachVRouterRouteTableToVRouterAction();
		action.routeTableUuid = routeTableUuid;
		action.virtualRouterVmUuid = virtualRouterVmUuid;
		action.sessionId = sessionId;
		AttachVRouterRouteTableToVRouterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachVRouterRouteTableToVRouter error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VRouterRouteTableInventory inventory = res.value.inventory;
			System.out.println(String.format("attachVRouterRouteTableToVRouter successfully uuid:%s,name:%s,description:%s", inventory.uuid, inventory.name, inventory.description));
		}
		return res;
	}
	
	/**
	 * 5.3.7.9 解绑云路由路由表
	 * @param routeTableUuid
	 * @param virtualRouterVmUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachVRouterRouteTableFromVRouterAction.Result detachVRouterRouteTableFromVRouter(String routeTableUuid, String virtualRouterVmUuid, String sessionId){
		DetachVRouterRouteTableFromVRouterAction action = new DetachVRouterRouteTableFromVRouterAction();
		action.routeTableUuid = routeTableUuid;
		action.virtualRouterVmUuid = virtualRouterVmUuid;
		action.sessionId = sessionId;
		DetachVRouterRouteTableFromVRouterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachVRouterRouteTableFromVRouter error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("detachVRouterRouteTableFromVRouter successfully routeTableUuid:%s,virtualRouterVmUuid:%s,sessionId:%s", routeTableUuid, virtualRouterVmUuid, sessionId));
		}
		return res;
	}
	
	/**
	 * 5.3.7.10 查询绑定关系
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVirtualRouterVRouterRouteTableRefAction.Result queryVirtualRouterVRouterRouteTableRef(String sessionId){
		QueryVirtualRouterVRouterRouteTableRefAction action = new QueryVirtualRouterVRouterRouteTableRefAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryVirtualRouterVRouterRouteTableRefAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVirtualRouterVRouterRouteTableRef error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VirtualRouterVRouterRouteTableRefInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVirtualRouterVRouterRouteTableRef successfully routeTableUuid:%s,virtualRouterVmUuid:%s", inventory.routeTableUuid, inventory.virtualRouterVmUuid));
			}
		}
		return res;
	}

	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		VRouterRouteTableAction vRouterRouteTable = new VRouterRouteTableAction();
		
		// 创建云路由路由表 Begin
		vRouterRouteTable.createVRouterRouteTable("VRouterRouteTableName", "VRouterRouteTableName Desc", sessionId);
		// 创建云路由路由表 End
	}
}