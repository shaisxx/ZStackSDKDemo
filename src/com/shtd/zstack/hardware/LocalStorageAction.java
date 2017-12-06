package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.AddLocalPrimaryStorageAction;
import org.zstack.sdk.GetLocalStorageHostDiskCapacityAction;
import org.zstack.sdk.HostDiskCapacity;
import org.zstack.sdk.HostInventory;
import org.zstack.sdk.LocalStorageGetVolumeMigratableHostsAction;
import org.zstack.sdk.LocalStorageMigrateVolumeAction;
import org.zstack.sdk.LocalStorageResourceRefInventory;
import org.zstack.sdk.PrimaryStorageInventory;
import org.zstack.sdk.QueryLocalStorageResourceRefAction;

/**
 * 4 硬件设施-主存储相关接口-本地存储相关接口
 * @author Josh
 * @date 2017-12-06
 */
public class LocalStorageAction {

	/**
	 * 4.4.13.1 添加本地存储为主存储
	 * @param url
	 * @param name
	 * @param zoneUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddLocalPrimaryStorageAction.Result addLocalPrimaryStorage(String url, String name, String zoneUuid, String sessionId){
		AddLocalPrimaryStorageAction action = new AddLocalPrimaryStorageAction();
		action.url = url;
		action.name = name;
		action.zoneUuid = zoneUuid;
		action.sessionId = sessionId;
		AddLocalPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addLocalPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("addLocalPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.13.2 查询本地存储资源引用
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryLocalStorageResourceRefAction.Result queryLocalStorageResourceRef(String uuid, String sessionId){
		QueryLocalStorageResourceRefAction action = new QueryLocalStorageResourceRefAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryLocalStorageResourceRefAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryLocalStorageResourceRef error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				LocalStorageResourceRefInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryLocalStorageResourceRef successfully hostUuid:%s,primaryStorageUuid:%s,resourceType:%s,resourceUuid:%s", 
						inventory.hostUuid, inventory.primaryStorageUuid, inventory.resourceType, inventory.resourceUuid));
			}
		}
		return res;
	}
		
	/**
	 * 4.4.13.3 迁移本地存储上存放的云盘
	 * @param volumeUuid
	 * @param destHostUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public LocalStorageMigrateVolumeAction.Result localStorageMigrateVolume(String volumeUuid, String destHostUuid, String sessionId){
		LocalStorageMigrateVolumeAction action = new LocalStorageMigrateVolumeAction();
		action.volumeUuid = volumeUuid;
		action.destHostUuid = destHostUuid;
		action.sessionId = sessionId;
		LocalStorageMigrateVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("localStorageMigrateVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			LocalStorageResourceRefInventory inventory = res.value.inventory;
			System.out.println(String.format("localStorageMigrateVolume successfully hostUuid:%s,primaryStorageUuid:%s,resourceType:%s,resourceUuid:%s", 
					inventory.hostUuid, inventory.primaryStorageUuid, inventory.resourceType, inventory.resourceUuid));
		}
		return res;
	}
	
	/**
	 * 4.4.13.4 获取主机本地存储容量
	 * @param hostUuid
	 * @param primaryStorageUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetLocalStorageHostDiskCapacityAction.Result getLocalStorageHostDiskCapacity(String hostUuid, String primaryStorageUuid, String sessionId){
		GetLocalStorageHostDiskCapacityAction action = new GetLocalStorageHostDiskCapacityAction();
		action.hostUuid = hostUuid;
		action.primaryStorageUuid = primaryStorageUuid;
		action.sessionId = sessionId;
		GetLocalStorageHostDiskCapacityAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getLocalStorageHostDiskCapacity error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				HostDiskCapacity inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getLocalStorageHostDiskCapacity successfully hostUuid:%s,availableCapacity:%s,availablePhysicalCapacity:%s,totalCapacity:%s,totalPhysicalCapacity:%s", 
						inventory.hostUuid, inventory.availableCapacity, inventory.availablePhysicalCapacity, inventory.totalCapacity, inventory.totalPhysicalCapacity));
			}
		}
		return res;
	}
	
	/**
	 * 4.4.13.5 获取迁移本地存储物理机
	 * @param volumeUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public LocalStorageGetVolumeMigratableHostsAction.Result localStorageGetVolumeMigratableHosts(String volumeUuid, String sessionId){
		LocalStorageGetVolumeMigratableHostsAction action = new LocalStorageGetVolumeMigratableHostsAction();
		action.volumeUuid = volumeUuid;
		action.sessionId = sessionId;
		LocalStorageGetVolumeMigratableHostsAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("localStorageGetVolumeMigratableHosts error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				HostInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("localStorageGetVolumeMigratableHosts successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}