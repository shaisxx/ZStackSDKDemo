package com.shtd.zstack.hardware;

import java.util.Arrays;
import java.util.List;

import org.zstack.sdk.AttachPrimaryStorageToClusterAction;
import org.zstack.sdk.ChangePrimaryStorageStateAction;
import org.zstack.sdk.CleanUpImageCacheOnPrimaryStorageAction;
import org.zstack.sdk.DeletePrimaryStorageAction;
import org.zstack.sdk.DetachPrimaryStorageFromClusterAction;
import org.zstack.sdk.GetPrimaryStorageAllocatorStrategiesAction;
import org.zstack.sdk.GetPrimaryStorageCapacityAction;
import org.zstack.sdk.GetPrimaryStorageTypesAction;
import org.zstack.sdk.PrimaryStorageInventory;
import org.zstack.sdk.QueryPrimaryStorageAction;
import org.zstack.sdk.ReconnectPrimaryStorageAction;
import org.zstack.sdk.SyncPrimaryStorageCapacityAction;
import org.zstack.sdk.UpdatePrimaryStorageAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 4 硬件设施-主存储相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class PrimaryStorageAction {

	/**
	 * 4.4.1 删除主存储
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeletePrimaryStorageAction.Result deletePrimaryStorage(String uuid, String sessionId){
		DeletePrimaryStorageAction action = new DeletePrimaryStorageAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeletePrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deletePrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deletePrimaryStorage successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.2 查询主存储
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryPrimaryStorageAction.Result queryPrimaryStorage(String uuid, String sessionId){
		QueryPrimaryStorageAction action = new QueryPrimaryStorageAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				PrimaryStorageInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	 
	/**
	 * 4.4.3 向集群添加主存储
	 * @param clusterUuid
	 * @param primaryStorageUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachPrimaryStorageToClusterAction.Result attachPrimaryStorageToCluster(String clusterUuid, String primaryStorageUuid, String sessionId){
		AttachPrimaryStorageToClusterAction action = new AttachPrimaryStorageToClusterAction();
		action.clusterUuid = clusterUuid;
		action.primaryStorageUuid = primaryStorageUuid;
		action.sessionId = sessionId;
		AttachPrimaryStorageToClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachPrimaryStorageToCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("attachPrimaryStorageToCluster successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.4 从集群卸载主存储
	 * @param primaryStorageUuid
	 * @param clusterUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachPrimaryStorageFromClusterAction.Result detachPrimaryStorageFromCluster(String primaryStorageUuid, String clusterUuid, String sessionId){
		DetachPrimaryStorageFromClusterAction action = new DetachPrimaryStorageFromClusterAction();
		action.primaryStorageUuid = primaryStorageUuid;
		action.clusterUuid = clusterUuid;
		action.sessionId = sessionId;
		DetachPrimaryStorageFromClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachPrimaryStorageFromCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("detachPrimaryStorageFromCluster successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.5 重连主存储
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ReconnectPrimaryStorageAction.Result reconnectPrimaryStorage(String uuid, String sessionId){
		ReconnectPrimaryStorageAction action = new ReconnectPrimaryStorageAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		ReconnectPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reconnectPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("reconnectPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.6 获取主存储容量
	 * @param primaryStorageUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetPrimaryStorageCapacityAction.Result getPrimaryStorageCapacity(String primaryStorageUuids, String sessionId){
		GetPrimaryStorageCapacityAction action = new GetPrimaryStorageCapacityAction();
		action.primaryStorageUuids = Arrays.asList(primaryStorageUuids);
		action.all = false;
		action.sessionId = sessionId;
		GetPrimaryStorageCapacityAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getPrimaryStorageCapacity error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getPrimaryStorageCapacity successfully availableCapacity:%s,availablePhysicalCapacity:%s,totalCapacity:%s,totalPhysicalCapacity:%s", 
					res.value.availableCapacity, res.value.availablePhysicalCapacity, res.value.totalCapacity, res.value.totalPhysicalCapacity));
		}
		return res;
	}
	
	/**
	 * 4.4.7 刷新主存储容量
	 * @param primaryStorageUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SyncPrimaryStorageCapacityAction.Result syncPrimaryStorageCapacity(String primaryStorageUuid, String sessionId){
		SyncPrimaryStorageCapacityAction action = new SyncPrimaryStorageCapacityAction();
		action.primaryStorageUuid = primaryStorageUuid;
		action.sessionId = sessionId;
		SyncPrimaryStorageCapacityAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("syncPrimaryStorageCapacity error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("syncPrimaryStorageCapacity successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.8 更改主存储状态
	 * @param uuid
	 * @param stateEvent "Disabled"
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangePrimaryStorageStateAction.Result changePrimaryStorageState(String uuid, String stateEvent, String sessionId){
		ChangePrimaryStorageStateAction action = new ChangePrimaryStorageStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangePrimaryStorageStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changePrimaryStorageState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("changePrimaryStorageState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.9 更新主存储信息
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdatePrimaryStorageAction.Result updatePrimaryStorage(String uuid, String name, String sessionId){
		UpdatePrimaryStorageAction action = new UpdatePrimaryStorageAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdatePrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updatePrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("updatePrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.10 清除主存储镜像缓
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CleanUpImageCacheOnPrimaryStorageAction.Result cleanUpImageCacheOnPrimaryStorage(String uuid, String sessionId){
		CleanUpImageCacheOnPrimaryStorageAction action = new CleanUpImageCacheOnPrimaryStorageAction();
		action.uuid = "cb50319265d540e19cbf98137fcbca0e";
		action.sessionId = "5dc546cb18cc46b885412564a39c512f";
		CleanUpImageCacheOnPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("cleanUpImageCacheOnPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("cleanUpImageCacheOnPrimaryStorage successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.11 获取主存储分配策略清单
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetPrimaryStorageAllocatorStrategiesAction.Result getPrimaryStorageAllocatorStrategies(String sessionId){
		GetPrimaryStorageAllocatorStrategiesAction action = new GetPrimaryStorageAllocatorStrategiesAction();
		action.sessionId = sessionId;
		GetPrimaryStorageAllocatorStrategiesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getPrimaryStorageAllocatorStrategies error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<String> strategies = res.value.strategies;
			if(strategies != null && strategies.size() > 0){
				for (String strategy: strategies) {
					System.out.println(String.format("getPrimaryStorageAllocatorStrategies successfully strategy:%s", strategy));
				}
			}
		}
		return res;
	}
	
	/**
	 * 4.4.12 获取主存储类型列表
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetPrimaryStorageTypesAction.Result getPrimaryStorageTypes(String sessionId){
		GetPrimaryStorageTypesAction action = new GetPrimaryStorageTypesAction();
		action.sessionId = sessionId;
		GetPrimaryStorageTypesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getPrimaryStorageTypes error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<String> types = res.value.types;
			if(types != null && types.size() > 0){
				for (String type: types) {
					System.out.println(String.format("getPrimaryStorageTypes successfully type:%s", type));
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		PrimaryStorageAction primaryStorage = new PrimaryStorageAction();
		
		// 查询主存储 Begin
		primaryStorage.queryPrimaryStorage("60ecca89521b43529156e195c65ed941", sessionId);
		// 查询主存储End
		
		// 向集群添加主存储 Begin
//		System.out.println("****** attachPrimaryStorageToCluster Start ******");
//		AttachPrimaryStorageToClusterAction.Result attachPrimaryStorageToClusterResult = 
//				primaryStorage.attachPrimaryStorageToCluster("2c645f1fe34c402bb51068eeae4cd6c4", 
//						"60ecca89521b43529156e195c65ed941", sessionId);
//		System.out.println("****** attachPrimaryStorageToCluster result : " + attachPrimaryStorageToClusterResult.value.getInventory().getName());
//		System.out.println("****** attachPrimaryStorageToCluster Stop ******");
		// 向集群添加主存储 End
	}
}