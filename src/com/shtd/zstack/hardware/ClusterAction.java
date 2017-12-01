package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.ChangeClusterStateAction;
import org.zstack.sdk.ChangeZoneStateAction;
import org.zstack.sdk.ClusterInventory;
import org.zstack.sdk.CreateClusterAction;
import org.zstack.sdk.DeleteClusterAction;
import org.zstack.sdk.L2NetworkInventory;
import org.zstack.sdk.QueryClusterAction;
import org.zstack.sdk.UpdateClusterAction;
import org.zstack.sdk.UpdateL2NetworkAction;
import org.zstack.sdk.ZoneInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 4 硬件设施-集群相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class ClusterAction {
	
	/**
	 * 4.2.1 改变区域的可用状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeZoneStateAction.Result changeZoneState(String uuid, String sessionId){
		ChangeZoneStateAction action = new ChangeZoneStateAction();
		action.uuid = uuid;
		action.stateEvent = "enable";
		action.sessionId = sessionId;
		ChangeZoneStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeZoneState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ZoneInventory inventory = res.value.inventory;
			System.out.println(String.format("changeZoneState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.2.2 更新二层网络
	 * @param uuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateL2NetworkAction.Result updateL2Network(String uuid, String name, String description, String sessionId){
		UpdateL2NetworkAction action = new UpdateL2NetworkAction();
		action.uuid = uuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		UpdateL2NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateL2Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L2NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("updateL2Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.2.3 创建一个集群
	 * @param zoneUuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateClusterAction.Result createCluster(String zoneUuid, String name, String description, String sessionId){
		CreateClusterAction action = new CreateClusterAction();
		action.zoneUuid = zoneUuid;
		action.name = name;
		action.description = description;
		action.hypervisorType = "KVM";
		action.sessionId = sessionId;
		CreateClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ClusterInventory inventory = res.value.inventory;
			System.out.println(String.format("createCluster successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.2.4 删除一个集群
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteClusterAction.Result deleteCluster(String uuid, String sessionId){
		DeleteClusterAction action = new DeleteClusterAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteCluster successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.2.5 查询集群
	 * @param hypervisorType
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryClusterAction.Result queryCluster(String hypervisorType, String sessionId){
		QueryClusterAction action = new QueryClusterAction();
		action.conditions = Arrays.asList("hypervisorType=" + hypervisorType);
		action.sessionId = sessionId;
		QueryClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ClusterInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryCluster successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.2.6 更新集群
	 * @param uuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateClusterAction.Result updateCluster(String uuid, String name, String description, String sessionId){
		UpdateClusterAction action = new UpdateClusterAction();
		action.uuid = uuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		UpdateClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ClusterInventory inventory = res.value.inventory;
			System.out.println(String.format("updateCluster successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.2.7 改变一个集群的可用状态
	 * @param uuid
	 * @param stateEvent disable
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeClusterStateAction.Result changeClusterState(String uuid, String stateEvent, String sessionId){
		ChangeClusterStateAction action = new ChangeClusterStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangeClusterStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeClusterState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ClusterInventory inventory = res.value.inventory;
			System.out.println(String.format("changeClusterState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}

	public static void main(String[] args) {
		
		String sessionId = ZStackUtils.ZStackLogin();
		
		ClusterAction cluster = new ClusterAction();
		
		// 创建集群 Begin
//		cluster.createCluster("dbe3997285a64f4d9f5cc39cf8367f54", "ClusterTest", "ClusterTest Desc", sessionId);
		// 创建集群 End
		
		// 查询集群 Begin
		cluster.queryCluster("KVM", sessionId);
		// 查询集群 End
		
		// 更新集群信息 Begin
		cluster.updateCluster("2c645f1fe34c402bb51068eeae4cd6c4", "ClusterTestNew", "ClusterTestNew Desc", sessionId);
		// 更新集群信息 End
		
		// 删除集群 Begin
		cluster.deleteCluster("e9be59c37f904a95ba4f7ede1dad661b", sessionId);
		// 删除集群 End
		
	}
}