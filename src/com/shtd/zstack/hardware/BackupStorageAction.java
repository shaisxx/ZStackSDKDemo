package com.shtd.zstack.hardware;

import java.util.Arrays;
import java.util.List;

import org.zstack.sdk.AttachBackupStorageToZoneAction;
import org.zstack.sdk.BackupStorageInventory;
import org.zstack.sdk.ChangeBackupStorageStateAction;
import org.zstack.sdk.DeleteBackupStorageAction;
import org.zstack.sdk.DeleteExportedImageFromBackupStorageAction;
import org.zstack.sdk.DetachBackupStorageFromZoneAction;
import org.zstack.sdk.ExportImageFromBackupStorageAction;
import org.zstack.sdk.GetBackupStorageCapacityAction;
import org.zstack.sdk.GetBackupStorageTypesAction;
import org.zstack.sdk.QueryBackupStorageAction;
import org.zstack.sdk.ReconnectBackupStorageAction;
import org.zstack.sdk.UpdateBackupStorageAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 4 硬件设施-镜像服务器相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class BackupStorageAction {

	/**
	 * 4.5.1 删除镜像服务器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteBackupStorageAction.Result deleteBackupStorage(String uuid, String sessionId){
		DeleteBackupStorageAction action = new DeleteBackupStorageAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteBackupStorage successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.2 查询镜像服务器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryBackupStorageAction.Result queryBackupStorage(String uuid, String sessionId){
		QueryBackupStorageAction action = new QueryBackupStorageAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				BackupStorageInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.5.3 重连镜像服务器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ReconnectBackupStorageAction.Result reconnectBackupStorage(String uuid, String sessionId){
		ReconnectBackupStorageAction action = new ReconnectBackupStorageAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		ReconnectBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reconnectBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("reconnectBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.4 更改镜像服务器可用状态
	 * @param zoneUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeBackupStorageStateAction.Result changeBackupStorageState(String uuid, String sessionId){
		ChangeBackupStorageStateAction action = new ChangeBackupStorageStateAction();
		action.uuid = uuid;
		action.stateEvent = "Disabled";
		action.sessionId = sessionId;
		ChangeBackupStorageStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeBackupStorageState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("changeBackupStorageState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.5 获取镜像服务器存储容量
	 * @param zoneUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetBackupStorageCapacityAction.Result getBackupStorageCapacity(String zoneUuids, String sessionId){
		GetBackupStorageCapacityAction action = new GetBackupStorageCapacityAction();
		action.zoneUuids = Arrays.asList(zoneUuids);
		action.all = true;
		action.sessionId = sessionId;
		GetBackupStorageCapacityAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getBackupStorageCapacity error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getBackupStorageCapacity successfully availableCapacity:%s,totalCapacity:%s", 
					res.value.availableCapacity, res.value.totalCapacity));
		}
		return res;
	}
	
	/**
	 * 4.5.6 更新镜像服务器信息
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateBackupStorageAction.Result updateBackupStorage(String uuid, String name, String sessionId){
		UpdateBackupStorageAction action = new UpdateBackupStorageAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("updateBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.7 从镜像服务器导出镜像
	 * @param backupStorageUuid
	 * @param imageUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ExportImageFromBackupStorageAction.Result exportImageFromBackupStorage(String backupStorageUuid, String imageUuid, String sessionId){
		ExportImageFromBackupStorageAction action = new ExportImageFromBackupStorageAction();
		action.backupStorageUuid = backupStorageUuid;
		action.imageUuid = imageUuid;
		action.sessionId = sessionId;
		ExportImageFromBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("exportImageFromBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("exportImageFromBackupStorage successfully imageUrl:%s", res.value.imageUrl));
		}
		return res;
	}
	
	/**
	 * 4.5.8 从镜像服务器删除导出的镜像
	 * @param backupStorageUuid
	 * @param imageUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteExportedImageFromBackupStorageAction.Result deleteExportedImageFromBackupStorage(String backupStorageUuid, String imageUuid, String sessionId){
		DeleteExportedImageFromBackupStorageAction action = new DeleteExportedImageFromBackupStorageAction();
		action.backupStorageUuid = "9c781fd5c7334e8492c9d3409ad0c63a";
		action.imageUuid = "b9b4f79d505040eba37ad6e4581b64fd";
		action.sessionId = "ecce8dd56c3a476c8dd1ac90aeb0d321";
		DeleteExportedImageFromBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteExportedImageFromBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteExportedImageFromBackupStorage successfully backupStorageUuid:%s,imageUuid:%s", backupStorageUuid, imageUuid));
		}
		return res;
	}
	
	/**
	 * 4.5.9 挂载镜像服务器至区域
	 * @param zoneUuid
	 * @param backupStorageUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachBackupStorageToZoneAction.Result attachBackupStorageToZone(String zoneUuid, String backupStorageUuid, String sessionId){
		AttachBackupStorageToZoneAction action = new AttachBackupStorageToZoneAction();
		action.zoneUuid = "426c6d907d3546378bd0007a74fc68e4";
		action.backupStorageUuid = "d60efb0b61c3420a8e2d90d30054548f";
		action.sessionId = "1b44ab68b73b4336a34aa10f6dc93cf8";
		AttachBackupStorageToZoneAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachBackupStorageToZone error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("attachBackupStorageToZone successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.10 从区域中卸载已经挂载的镜像服务器
	 * @param backupStorageUuid
	 * @param zoneUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachBackupStorageFromZoneAction.Result detachBackupStorageFromZone(String backupStorageUuid, String zoneUuid, String sessionId){
		DetachBackupStorageFromZoneAction action = new DetachBackupStorageFromZoneAction();
		action.backupStorageUuid = backupStorageUuid;
		action.zoneUuid = zoneUuid;
		action.sessionId = sessionId;
		DetachBackupStorageFromZoneAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachBackupStorageFromZone error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("detachBackupStorageFromZone successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.11 获取镜像服务器类型列表
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetBackupStorageTypesAction.Result getBackupStorageTypes(String sessionId){
		GetBackupStorageTypesAction action = new GetBackupStorageTypesAction();
		action.sessionId = sessionId;
		GetBackupStorageTypesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getBackupStorageTypes error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<String> types = res.value.types;
			if(types != null && types.size() > 0){
				for (String type: types) {
					System.out.println(String.format("getBackupStorageTypes successfully type:%s", type));
				}
			}
		}
		return res;
	}
			
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		BackupStorageAction action = new BackupStorageAction();
		
		// 查询镜像服务器 Begin
		action.queryBackupStorage("uuid", sessionId);
		// 查询镜像服务器 End
		
	}
}