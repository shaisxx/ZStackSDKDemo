package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.AttachL2NetworkToClusterAction;
import org.zstack.sdk.CreateL2NoVlanNetworkAction;
import org.zstack.sdk.CreateL2VlanNetworkAction;
import org.zstack.sdk.CreateL2VxlanNetworkAction;
import org.zstack.sdk.CreateL2VxlanNetworkPoolAction;
import org.zstack.sdk.CreateVniRangeAction;
import org.zstack.sdk.DeleteL2NetworkAction;
import org.zstack.sdk.DeleteVniRangeAction;
import org.zstack.sdk.DetachL2NetworkFromClusterAction;
import org.zstack.sdk.GetL2NetworkTypesAction;
import org.zstack.sdk.L2NetworkInventory;
import org.zstack.sdk.L2VxlanNetworkInventory;
import org.zstack.sdk.QueryL2NetworkAction;
import org.zstack.sdk.QueryL2VlanNetworkAction;
import org.zstack.sdk.QueryL2VxlanNetworkAction;
import org.zstack.sdk.QueryL2VxlanNetworkPoolAction;
import org.zstack.sdk.QueryVniRangeAction;
import org.zstack.sdk.VniRangeInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 5 网络-二层网络相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class L2VlanNetworkAction {

	/**
	 * 5.1.1 创建普通二层网络
	 * @param name
	 * @param description
	 * @param zoneUuid
	 * @param physicalInterface
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateL2NoVlanNetworkAction.Result createL2NoVlanNetwork(String name, String description, 
			String zoneUuid, String physicalInterface, String sessionId){
		
		CreateL2NoVlanNetworkAction action = new CreateL2NoVlanNetworkAction();
		action.name = name;
		action.description = description;
		action.zoneUuid = zoneUuid;
		action.physicalInterface = physicalInterface;
		action.sessionId = sessionId;
		CreateL2NoVlanNetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createL2NoVlanNetwork error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L2NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("createL2NoVlanNetwork successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.1.2 删除二层网络
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteL2NetworkAction.Result deleteL2Network(String uuid, String sessionId){
		DeleteL2NetworkAction action = new DeleteL2NetworkAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteL2NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteL2Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteL2Network successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.1.3 查询二层网络
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryL2NetworkAction.Result queryL2Network(String sessionId){
		QueryL2NetworkAction action = new QueryL2NetworkAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryL2NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryL2Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				L2NetworkInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryL2Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.1.4 获取二层网络类型
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetL2NetworkTypesAction.Result getL2NetworkTypes(String sessionId){
		GetL2NetworkTypesAction action = new GetL2NetworkTypesAction();
		action.sessionId = sessionId;
		GetL2NetworkTypesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getL2NetworkTypes error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.types.size(); i++) {
				String type = res.value.types.get(i);
				System.out.println(String.format("getL2NetworkTypes successfully type:%s", type));
			}
		}
		return res;
	}
	
	/**
	 * 5.1.5 挂载二层网络到集群
	 * @param l2NetworkUuid
	 * @param clusterUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachL2NetworkToClusterAction.Result attachL2NetworkToCluster(String l2NetworkUuid, String clusterUuid, String sessionId){
		AttachL2NetworkToClusterAction action = new AttachL2NetworkToClusterAction();
		action.l2NetworkUuid = l2NetworkUuid;
		action.clusterUuid = clusterUuid;
		action.sessionId = sessionId;
		AttachL2NetworkToClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachL2NetworkToCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L2NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("attachL2NetworkToCluster successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.1.6 从集群上卸载二层网络
	 * @param l2NetworkUuid
	 * @param clusterUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachL2NetworkFromClusterAction.Result detachL2NetworkFromCluster(String l2NetworkUuid, String clusterUuid, String sessionId){
		DetachL2NetworkFromClusterAction action = new DetachL2NetworkFromClusterAction();
		action.l2NetworkUuid = l2NetworkUuid;
		action.clusterUuid = clusterUuid;
		action.sessionId = sessionId;
		DetachL2NetworkFromClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachL2NetworkFromCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L2NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("detachL2NetworkFromCluster successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.1.7 创建二层Vlan网络
	 * @param vlan
	 * @param name
	 * @param description
	 * @param zoneUuid
	 * @param physicalInterface
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateL2VlanNetworkAction.Result createL2VlanNetwork(Integer vlan, String name, String description, String zoneUuid, String physicalInterface, String sessionId){
		CreateL2VlanNetworkAction action = new CreateL2VlanNetworkAction();
		action.vlan = vlan;
		action.name = name;
		action.description = description;
		action.zoneUuid = zoneUuid;
		action.physicalInterface = physicalInterface;
		action.sessionId = sessionId;
		CreateL2VlanNetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createL2VlanNetwork error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L2NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("createL2VlanNetwork successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.1.8 查询二层Vlan网络
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryL2VlanNetworkAction.Result queryL2VlanNetwork(String sessionId){
		QueryL2VlanNetworkAction action = new QueryL2VlanNetworkAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryL2VlanNetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryL2VlanNetwork error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				L2NetworkInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryL2VlanNetwork successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.1.9 创建VXLAN网络池
	 * @param name
	 * @param description
	 * @param zoneUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateL2VxlanNetworkPoolAction.Result createL2VxlanNetworkPool(String name, String description, String zoneUuid, String sessionId){
		CreateL2VxlanNetworkPoolAction action = new CreateL2VxlanNetworkPoolAction();
		action.name = name;
		action.description = description;
		action.zoneUuid = zoneUuid;
		action.sessionId = sessionId;
		CreateL2VxlanNetworkPoolAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createL2VxlanNetworkPool error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L2NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("createL2VxlanNetworkPool successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.1.10 查询VXLAN网络资源池
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryL2VxlanNetworkPoolAction.Result queryL2VxlanNetworkPool(String sessionId){
		QueryL2VxlanNetworkPoolAction action = new QueryL2VxlanNetworkPoolAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryL2VxlanNetworkPoolAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryL2VxlanNetworkPool error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				L2NetworkInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryL2VxlanNetworkPool successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.1.11 创建VXLAN网络
	 * @param name
	 * @param description
	 * @param zoneUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateL2VxlanNetworkAction.Result createL2VxlanNetwork(String name, String description, String zoneUuid, String sessionId){
		CreateL2VxlanNetworkAction action = new CreateL2VxlanNetworkAction();
		action.name = name;
		action.description = description;
		action.zoneUuid = zoneUuid;
		action.sessionId = sessionId;
		CreateL2VxlanNetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createL2VxlanNetwork error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L2VxlanNetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("createL2VxlanNetwork successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.1.12 查询VXLAN网络
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryL2VxlanNetworkAction.Result queryL2VxlanNetwork(String sessionId){
		QueryL2VxlanNetworkAction action = new QueryL2VxlanNetworkAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryL2VxlanNetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryL2VxlanNetwork error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				L2NetworkInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryL2VxlanNetwork successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.1.13 创建Vni Range
	 * @param name
	 * @param description
	 * @param startVni
	 * @param endVni
	 * @param l2NetworkUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateVniRangeAction.Result createVniRange(String name, String description, Integer startVni, Integer endVni, String l2NetworkUuid, String sessionId){
		CreateVniRangeAction action = new CreateVniRangeAction();
		action.name = name;
		action.description = description;
		action.startVni = startVni;
		action.endVni = endVni;
		action.l2NetworkUuid = l2NetworkUuid;
		action.sessionId = sessionId;
		CreateVniRangeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createVniRange error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VniRangeInventory inventory = res.value.inventory;
			System.out.println(String.format("createVniRange successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.1.14 查询Vni Range
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVniRangeAction.Result queryVniRange(String sessionId){
		QueryVniRangeAction action = new QueryVniRangeAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryVniRangeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVniRange error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VniRangeInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVniRange successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.1.15 删除Vni Range
	 * @param uuid
	 * @param sessionId
	 * @author Josh
	 */
	public DeleteVniRangeAction.Result deleteVniRange(String uuid, String sessionId){
		DeleteVniRangeAction action = new DeleteVniRangeAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteVniRangeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVniRange error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVniRange successfully uuid:%s", uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		L2VlanNetworkAction action = new L2VlanNetworkAction();
		
		// 创建二层网络 Begin
		action.createL2NoVlanNetwork("本地二层网络-NEW", "本地二层网络-NEW DESC", 
				"2429832713f74b339d3abf01e126bf23", "eth0", sessionId);
		// 创建二层网络End
		
		// 查询区域 Begin
		action.queryL2Network(sessionId);
		// 查询区域 End
	}
}