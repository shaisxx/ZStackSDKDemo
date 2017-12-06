package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.AddCephPrimaryStorageAction;
import org.zstack.sdk.AddMonToCephPrimaryStorageAction;
import org.zstack.sdk.CephPrimaryStorageInventory;
import org.zstack.sdk.PrimaryStorageInventory;
import org.zstack.sdk.QueryCephPrimaryStorageAction;
import org.zstack.sdk.RemoveMonFromCephPrimaryStorageAction;
import org.zstack.sdk.UpdateCephPrimaryStorageMonAction;

/**
 * 4 硬件设施-主存储相关接口-Ceph主存储相关接口
 * @author Josh
 * @date 2017-12-06
 */
public class CephPrimaryStorageAction {
	
	/**
	 * 4.4.15.1 添加Ceph主存储
	 * @param monUrls
	 * @param rootVolumePoolName
	 * @param dataVolumePoolName
	 * @param imageCachePoolName
	 * @param name
	 * @param zoneUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddCephPrimaryStorageAction.Result addCephPrimaryStorage(String monUrls, String rootVolumePoolName, String dataVolumePoolName, String imageCachePoolName, 
			String name, String zoneUuid, String sessionId){
		
		AddCephPrimaryStorageAction action = new AddCephPrimaryStorageAction();
		action.monUrls = Arrays.asList(monUrls);
		action.rootVolumePoolName = rootVolumePoolName;
		action.dataVolumePoolName = dataVolumePoolName;
		action.imageCachePoolName = imageCachePoolName;
		action.name = name;
		action.zoneUuid = zoneUuid;
		action.sessionId = sessionId;
		AddCephPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addCephPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("addCephPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.15.2 查询Ceph主存储
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryCephPrimaryStorageAction.Result queryCephPrimaryStorage(String uuid, String sessionId){
		QueryCephPrimaryStorageAction action = new QueryCephPrimaryStorageAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryCephPrimaryStorageAction.Result res = action.call();	
		if (res.error != null) {
			System.out.println(String.format("queryCephPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				PrimaryStorageInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryCephPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.4.15.3 为Ceph主存储添加mon节点
	 * @param uuid
	 * @param monUrls
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddMonToCephPrimaryStorageAction.Result addMonToCephPrimaryStorage(String uuid, String monUrls, String sessionId){
		AddMonToCephPrimaryStorageAction action = new AddMonToCephPrimaryStorageAction();
		action.uuid = uuid;
		action.monUrls = Arrays.asList(monUrls);
		action.sessionId = sessionId;
		AddMonToCephPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addMonToCephPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			CephPrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("addMonToCephPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.15.4 从Ceph主存储删除mon节点
	 * @param uuid
	 * @param monHostnames
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RemoveMonFromCephPrimaryStorageAction.Result removeMonFromCephPrimaryStorage(String uuid, String monHostnames, String sessionId){
		RemoveMonFromCephPrimaryStorageAction action = new RemoveMonFromCephPrimaryStorageAction();
		action.uuid = uuid;
		action.monHostnames = Arrays.asList(monHostnames);
		action.sessionId = sessionId;
		RemoveMonFromCephPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("removeMonFromCephPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			CephPrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("removeMonFromCephPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.4.15.5 更新Ceph主存储mon节点
	 * @param monUuid
	 * @param hostname
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateCephPrimaryStorageMonAction.Result updateCephPrimaryStorageMon(String monUuid, String hostname, String sessionId){
		UpdateCephPrimaryStorageMonAction action = new UpdateCephPrimaryStorageMonAction();
		action.monUuid = monUuid;
		action.hostname = hostname;
		action.sessionId = sessionId;
		UpdateCephPrimaryStorageMonAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateCephPrimaryStorageMon error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			CephPrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("updateCephPrimaryStorageMon successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}