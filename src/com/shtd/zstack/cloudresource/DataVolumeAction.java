package com.shtd.zstack.cloudresource;

import java.util.Arrays;
import java.util.Map.Entry;

import org.zstack.sdk.AttachDataVolumeToVmAction;
import org.zstack.sdk.ChangeVolumeStateAction;
import org.zstack.sdk.CreateDataVolumeAction;
import org.zstack.sdk.CreateDataVolumeFromVolumeSnapshotAction;
import org.zstack.sdk.CreateDataVolumeFromVolumeTemplateAction;
import org.zstack.sdk.CreateVolumeSnapshotAction;
import org.zstack.sdk.DeleteDataVolumeAction;
import org.zstack.sdk.DeleteVolumeSnapshotAction;
import org.zstack.sdk.DetachDataVolumeFromVmAction;
import org.zstack.sdk.ExpungeDataVolumeAction;
import org.zstack.sdk.GetDataVolumeAttachableVmAction;
import org.zstack.sdk.GetVolumeCapabilitiesAction;
import org.zstack.sdk.GetVolumeFormatAction;
import org.zstack.sdk.QueryVolumeAction;
import org.zstack.sdk.QueryVolumeSnapshotAction;
import org.zstack.sdk.QueryVolumeSnapshotTreeAction;
import org.zstack.sdk.RecoverDataVolumeAction;
import org.zstack.sdk.RevertVolumeFromSnapshotAction;
import org.zstack.sdk.SyncVolumeSizeAction;
import org.zstack.sdk.UpdateVolumeAction;
import org.zstack.sdk.UpdateVolumeSnapshotAction;
import org.zstack.sdk.VmInstanceInventory;
import org.zstack.sdk.VolumeFormatReplyStruct;
import org.zstack.sdk.VolumeInventory;
import org.zstack.sdk.VolumeSnapshotInventory;
import org.zstack.sdk.VolumeSnapshotTreeInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 3 云资源池-云盘相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class DataVolumeAction {

	/**
	 * 3.3.1 创建云盘
	 * @param name
	 * @param description
	 * @param diskOfferingUuid
	 * @param primaryStorageUuid
	 * @param resourceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateDataVolumeAction.Result createDataVolume(String name, String description, 
			String diskOfferingUuid, String primaryStorageUuid, String resourceUuid, String sessionId){
		
		CreateDataVolumeAction action = new CreateDataVolumeAction();
		action.name = name;
		action.description = description;
		action.diskOfferingUuid = diskOfferingUuid;
		action.primaryStorageUuid = primaryStorageUuid;
		action.resourceUuid = resourceUuid;
		action.sessionId = sessionId;
		CreateDataVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createDataVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("createDataVolume successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.2 删除云盘
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteDataVolumeAction.Result deleteDataVolume(String uuid, String sessionId){
		DeleteDataVolumeAction action = new DeleteDataVolumeAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteDataVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteDataVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteDataVolume successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.3 彻底删除云盘
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ExpungeDataVolumeAction.Result expungeDataVolume(String uuid, String sessionId){
		ExpungeDataVolumeAction action = new ExpungeDataVolumeAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		ExpungeDataVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("expungeDataVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("expungeDataVolume successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.4 恢复云盘
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RecoverDataVolumeAction.Result recoverDataVolume(String uuid, String sessionId){
		RecoverDataVolumeAction action = new RecoverDataVolumeAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		RecoverDataVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("recoverDataVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("recoverDataVolume successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.5 开启或关闭云盘
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeVolumeStateAction.Result changeVolumeState(String uuid, String sessionId){
		ChangeVolumeStateAction action = new ChangeVolumeStateAction();
		action.uuid = uuid;
		action.stateEvent = "enable";
		action.sessionId = sessionId;
		ChangeVolumeStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeVolumeState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("changeVolumeState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.6  从镜像创建云盘
	 * @param imageUuid
	 * @param name
	 * @param description
	 * @param primaryStorageUuid
	 * @param hostUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateDataVolumeFromVolumeTemplateAction.Result createDataVolumeFromVolumeTemplate(String imageUuid, String name,
			String description, String primaryStorageUuid, String hostUuid, String sessionId){
		
		CreateDataVolumeFromVolumeTemplateAction action = new CreateDataVolumeFromVolumeTemplateAction();
		action.imageUuid = imageUuid;
		action.name = name;
		action.description = description;
		action.primaryStorageUuid = primaryStorageUuid;
		action.hostUuid = hostUuid;
		action.sessionId = sessionId;
		CreateDataVolumeFromVolumeTemplateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createDataVolumeFromVolumeTemplate error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("createDataVolumeFromVolumeTemplate successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.7 从快照创建云盘
	 * @param name
	 * @param description
	 * @param volumeSnapshotUuid
	 * @param primaryStorageUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateDataVolumeFromVolumeSnapshotAction.Result createDataVolumeFromVolumeSnapshot(String name, String description, String volumeSnapshotUuid,
			String primaryStorageUuid, String sessionId){
		
		CreateDataVolumeFromVolumeSnapshotAction action = new CreateDataVolumeFromVolumeSnapshotAction();
		action.name = name;
		action.description = description;
		action.volumeSnapshotUuid = volumeSnapshotUuid;
		action.primaryStorageUuid = primaryStorageUuid;
		action.sessionId = sessionId;
		CreateDataVolumeFromVolumeSnapshotAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createDataVolumeFromVolumeSnapshot error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("createDataVolumeFromVolumeSnapshot successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.8 获取云盘清单
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVolumeAction.Result queryVolume(String sessionId){
		QueryVolumeAction action = new QueryVolumeAction();
		action.conditions = Arrays.asList();
		action.sessionId = "a6c77f7c77dd425e98f3089363725c06";
		QueryVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVolumeFormat error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			java.util.List<VolumeInventory> inventories = res.value.inventories; 
			if(inventories != null && inventories.size() > 0){
				for(VolumeInventory volumeInventory: inventories){
					System.out.println(String.format("getVolumeFormat successfully name:%s,uuid:%s", volumeInventory.name, volumeInventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.3.9 获取云盘格式
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVolumeFormatAction.Result getVolumeFormat(String sessionId){
		GetVolumeFormatAction action = new GetVolumeFormatAction();
		action.sessionId = sessionId;
		GetVolumeFormatAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVolumeFormat error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			java.util.List<VolumeFormatReplyStruct> formats = res.value.formats; 
			if(formats != null && formats.size() > 0){
				for(VolumeFormatReplyStruct volumeFormatReplyStruct: formats){
					System.out.println(String.format("getVolumeFormat successfully format:%s", volumeFormatReplyStruct.format));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.3.10 获取云盘支持的类型的能力
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVolumeCapabilitiesAction.Result getVolumeCapabilities(String uuid, String sessionId){
		GetVolumeCapabilitiesAction action = new GetVolumeCapabilitiesAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVolumeCapabilitiesAction.Result res = action.call();
		
		if (res.error != null) {
			System.out.println(String.format("getVolumeCapabilities error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			java.util.Map<String, Object> map = res.value.capabilities; 
			for (Entry<String, Object> entry : map.entrySet()) {
				System.out.println(String.format("getVolumeCapabilities successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	/**
	 * 3.3.11 同步云盘大小
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SyncVolumeSizeAction.Result syncVolumeSize(String uuid, String sessionId){
		SyncVolumeSizeAction action = new SyncVolumeSizeAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		SyncVolumeSizeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("syncVolumeSize error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("syncVolumeSize successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.12 修改云盘属性
	 * @param uuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateVolumeAction.Result updateVolume(String uuid, String name, String description, String sessionId){
		UpdateVolumeAction action = new UpdateVolumeAction();
		action.uuid = uuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		UpdateVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("updateVolume successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.3.13 设置云盘限速
	 * @param uuid
	 * @param volumeBandwidth
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	/*public SetVolumeQoSAction.Result setVolumeQoS(String uuid, long volumeBandwidth, String sessionId){
		SetVolumeQoSAction action = new SetVolumeQoSAction();
		action.uuid = "ac4fdd09c5884f58a5b38b805b5ddb78";
		action.volumeBandwidth = 10000.0;
		action.sessionUuid = "aeec637635af476b94356f4f8fe714be";
		SetVolumeQoSAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVolumeQoS error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("setVolumeQoS successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}*/
	
	/**
	 * 3.3.14 获取云盘限速
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	/*public GetVolumeQoSAction.Result getVolumeQoS(String uuid, String sessionId){
		GetVolumeQoSAction action = new GetVolumeQoSAction();
		action.uuid = "b0d6b8438a204fa4be27bff9675506ea";
		action.sessionUuid = "58b4821e41664694b7200e3115d02dd8";
		GetVolumeQoSAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVolumeQoS error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("getVolumeQoS successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}*/
	
	/**
	 * 3.3.15 取消云盘网卡限速
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	/*public DeleteVolumeQoSAction.Result deleteVolumeQoS(String uuid, String sessionId){
		DeleteVolumeQoSAction action = new DeleteVolumeQoSAction();
		action.uuid = "3dfcb78645dd4fd5abad6d24facd0432";
		action.sessionUuid = "5cb9c0fce58d480f983e9817f51e4c25";
		DeleteVolumeQoSAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVolumeQoS error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory; 
			System.out.println(String.format("deleteVolumeQoS successfully uuid:%s", uuid));
		}
		return res;
	}*/
	
	/**
	 * 3.3.16 获取云盘是否能被加载
	 * @param volumeUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetDataVolumeAttachableVmAction.Result getDataVolumeAttachableVm(String volumeUuid, String sessionId){
		GetDataVolumeAttachableVmAction action = new GetDataVolumeAttachableVmAction();
		action.volumeUuid = volumeUuid;
		action.sessionId = sessionId;
		GetDataVolumeAttachableVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getDataVolumeAttachableVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VmInstanceInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getDataVolumeAttachableVm successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 3.3.17 挂载云盘到云主机上
	 * @param vmInstanceUuid
	 * @param volumeUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachDataVolumeToVmAction.Result attachDataVolumeToVm(String vmInstanceUuid, String volumeUuid, String sessionId){
		AttachDataVolumeToVmAction action = new AttachDataVolumeToVmAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.volumeUuid = volumeUuid;
		action.sessionId = sessionId;
		AttachDataVolumeToVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachDataVolumeToVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory;
			System.out.println(String.format("attachDataVolumeToVm successfully uuid:%s,name:%s", inventory.uuid, inventory.name));
		}
		return res;
	}
	
	/**
	 * 3.3.18 从云主机上卸载云盘
	 * @param uuid
	 * @param vmUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachDataVolumeFromVmAction.Result detachDataVolumeFromVm(String uuid, String vmUuid, String sessionId){
		DetachDataVolumeFromVmAction action = new DetachDataVolumeFromVmAction();
		action.uuid = uuid;
		action.vmUuid = vmUuid;
		action.sessionId = sessionId;
		DetachDataVolumeFromVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachDataVolumeFromVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeInventory inventory = res.value.inventory;
			System.out.println(String.format("detachDataVolumeFromVm successfully uuid:%s,name:%s", inventory.uuid, inventory.name));
		}
		return res;
	}
	
	/**
	 * 3.3.19 从云盘创建快照
	 * @param volumeUuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateVolumeSnapshotAction.Result createVolumeSnapshot(String volumeUuid, String name, String description, String sessionId){
		CreateVolumeSnapshotAction action = new CreateVolumeSnapshotAction();
		action.volumeUuid = volumeUuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		CreateVolumeSnapshotAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createVolumeSnapshot error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeSnapshotInventory inventory = res.value.inventory;
			System.out.println(String.format("createVolumeSnapshot successfully uuid:%s,name:%s", inventory.uuid, inventory.name));
		}
		return res;
	}
	
	/**
	 * 3.3.20 查询云盘快照
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVolumeSnapshotAction.Result queryVolumeSnapshot(String uuid, String sessionId){
		QueryVolumeSnapshotAction action = new QueryVolumeSnapshotAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVolumeSnapshotAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVolumeSnapshot error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VolumeSnapshotInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVolumeSnapshot successfully name:%s,volumeUuid:%s,uuid:%s", inventory.name, inventory.volumeUuid, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 3.3.21 查询快照树
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVolumeSnapshotTreeAction.Result queryVolumeSnapshotTree(String uuid, String sessionId){
		QueryVolumeSnapshotTreeAction action = new QueryVolumeSnapshotTreeAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVolumeSnapshotTreeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVolumeSnapshotTree error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VolumeSnapshotTreeInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVolumeSnapshotTree successfully volumeUuid:%s,uuid:%s", inventory.volumeUuid, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 3.3.22 更新云盘快照信息
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateVolumeSnapshotAction.Result updateVolumeSnapshot(String uuid, String name, String sessionId){
		UpdateVolumeSnapshotAction action = new UpdateVolumeSnapshotAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateVolumeSnapshotAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateVolumeSnapshot error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VolumeSnapshotInventory inventory = res.value.inventory;
			System.out.println(String.format("updateVolumeSnapshot successfully uuid:%s,name:%s", inventory.uuid, inventory.name));
		}
		return res;
	}
	
	/**
	 * 3.3.23 删除云盘快照
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVolumeSnapshotAction.Result deleteVolumeSnapshot(String uuid, String sessionId){
		DeleteVolumeSnapshotAction action = new DeleteVolumeSnapshotAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteVolumeSnapshotAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVolumeSnapshot error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVolumeSnapshot successfully uuid:%s", uuid));
		}
		return res;
	}
	
	//3.3.24 创建快照的定时任务
	
	/**
	 * 3.3.25 将云盘回滚至指定快照
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RevertVolumeFromSnapshotAction.Result revertVolumeFromSnapshot(String uuid, String sessionId){
		RevertVolumeFromSnapshotAction action = new RevertVolumeFromSnapshotAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		RevertVolumeFromSnapshotAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("revertVolumeFromSnapshot error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("revertVolumeFromSnapshot successfully uuid:%s", uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		DataVolumeAction dataVolume = new DataVolumeAction();
		
		// 创建云盘 Begin
		dataVolume.createDataVolume("test-volume", "test-volume desc", 
				"415291baa51441668f4e96678a442d4b", "60ecca89521b43529156e195c65ed941", "70f867db4c3a46c5b98eb61122d8603a", sessionId);
		// 创建云盘 End
	}
}