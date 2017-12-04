package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.AddCephBackupStorageAction;
import org.zstack.sdk.BackupStorageInventory;
import org.zstack.sdk.CephBackupStorageInventory;
import org.zstack.sdk.QueryCephBackupStorageAction;
import org.zstack.sdk.RemoveMonFromCephBackupStorageAction;
import org.zstack.sdk.UpdateCephBackupStorageMonAction;

/**
 * 4 硬件设施-镜像服务器相关接口-Ceph镜像服务器相关接口
 * @author Josh
 * @date 2017-12-01
 */
public class CephBackupStorageAction {
	
	/**
	 * 4.5.13.1 添加Ceph镜像服务器
	 * @param monUrls
	 * @param poolName
	 * @param name
	 * @param importImages
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddCephBackupStorageAction.Result addCephBackupStorage(String monUrls, String poolName, String name, String importImages, String sessionId){
		AddCephBackupStorageAction action = new AddCephBackupStorageAction();
		action.monUrls = Arrays.asList(monUrls);
		action.poolName = poolName;
		action.name = name;
		action.importImages = false;
		action.sessionId = sessionId;
		AddCephBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addCephBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("addCephBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.13.2 查询Ceph镜像服务器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryCephBackupStorageAction.Result queryCephBackupStorage(String uuid, String sessionId){
		QueryCephBackupStorageAction action = new QueryCephBackupStorageAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryCephBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryCephBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				BackupStorageInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryCephBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.5.13.3 更新Ceph镜像服务器mon节点
	 * @param monUuid
	 * @param hostname
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateCephBackupStorageMonAction.Result updateCephBackupStorageMon(String monUuid, String hostname, String sessionId){
		UpdateCephBackupStorageMonAction action = new UpdateCephBackupStorageMonAction();
		action.monUuid = monUuid;
		action.hostname = hostname;
		action.sessionId = sessionId;
		UpdateCephBackupStorageMonAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateCephBackupStorageMon error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			CephBackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("updateCephBackupStorageMon successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.13.4 为Ceph镜像服务器添加mon节点
	 * @param uuid
	 * @param monHostnames
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	/*public RemoveMonFromCephBackupStorageAction.Result removeMonFromCephBackupStorage(String uuid, String monHostnames, String sessionId){
		RemoveMonFromCephBackupStorageAction action = new RemoveMonFromCephBackupStorageAction();
		action.uuid = uuid;
		action.monHostnames = Arrays.asList(monHostnames);
		action.sessionId = sessionId;
		RemoveMonFromCephBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("removeMonFromCephBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			CephBackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("removeMonFromCephBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}*/
	
	/**
	 * 4.5.13.5 Ceph镜像服务器删除mon
	 * @param uuid
	 * @param monHostnames
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RemoveMonFromCephBackupStorageAction.Result removeMonFromCephBackupStorage(String uuid, String monHostnames, String sessionId){
		RemoveMonFromCephBackupStorageAction action = new RemoveMonFromCephBackupStorageAction();
		action.uuid = uuid;
		action.monHostnames = Arrays.asList(monHostnames);
		action.sessionId = sessionId;
		RemoveMonFromCephBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("removeMonFromCephBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			CephBackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("removeMonFromCephBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}
