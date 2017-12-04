package com.shtd.zstack.cloudresource;

import java.util.Arrays;
import java.util.List;

import org.zstack.sdk.AddImageAction;
import org.zstack.sdk.ChangeImageStateAction;
import org.zstack.sdk.CreateDataVolumeTemplateFromVolumeAction;
import org.zstack.sdk.CreateRootVolumeTemplateFromRootVolumeAction;
import org.zstack.sdk.CreateRootVolumeTemplateFromVolumeSnapshotAction;
import org.zstack.sdk.DeleteImageAction;
import org.zstack.sdk.ExpungeImageAction;
import org.zstack.sdk.ImageInventory;
import org.zstack.sdk.QueryImageAction;
import org.zstack.sdk.RecoverImageAction;
import org.zstack.sdk.SyncImageSizeAction;
import org.zstack.sdk.UpdateImageAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 3 云资源池-镜像相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class ImageAction {

	/**
	 * 3.2.1 添加镜像
	 * @param name
	 * @param url
	 * @param format
	 * @param platform
	 * @param backupStorageUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddImageAction.Result addImage(String name, String url, String format, String platform, 
			List<String> backupStorageUuids, String sessionId){
		
		AddImageAction action = new AddImageAction();
		action.name = name;
		action.url = url;
		action.mediaType = "RootVolumeTemplate";
		action.system = false;
		action.format = format;
		action.platform = platform;
		action.backupStorageUuids = backupStorageUuids;
		action.sessionId = sessionId;
		AddImageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addImage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageInventory inventory = res.value.inventory;
			System.out.println(String.format("addImage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.2.2 删除镜像
	 * @param uuid
	 * @param backupStorageUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteImageAction.Result deleteImage(String uuid, List<String> backupStorageUuids, String sessionId){
		DeleteImageAction action = new DeleteImageAction();
		action.uuid = uuid;
		action.backupStorageUuids = backupStorageUuids;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteImageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteImage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteImage successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 3.2.3 彻底删除镜像
	 * @param imageUuid
	 * @param backupStorageUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ExpungeImageAction.Result expungeImage(String imageUuid, String backupStorageUuids, String sessionId){
		ExpungeImageAction action = new ExpungeImageAction();
		action.imageUuid = imageUuid;
		action.backupStorageUuids = Arrays.asList(backupStorageUuids);
		action.sessionId = sessionId;
		ExpungeImageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteImage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteImage successfully uuid:%s", imageUuid));
		}
		return res;
	}
	
	/**
	 * 3.2.4 查询镜像
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryImageAction.Result queryImage(String uuid, String sessionId){
		QueryImageAction action = new QueryImageAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryImageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryImage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ImageInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryImage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 3.2.5 恢复镜像
	 * @param imageUuid
	 * @param backupStorageUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RecoverImageAction.Result recoverImage(String imageUuid, String backupStorageUuids, String sessionId){
		RecoverImageAction action = new RecoverImageAction();
		action.imageUuid = imageUuid;
		action.backupStorageUuids = Arrays.asList(backupStorageUuids);
		action.sessionId = sessionId;
		RecoverImageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("recoverImage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageInventory inventory = res.value.inventory;
			System.out.println(String.format("recoverImage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.2.6 修改镜像状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeImageStateAction.Result changeImageState(String uuid, String sessionId){
		ChangeImageStateAction action = new ChangeImageStateAction();
		action.uuid = uuid;
		action.stateEvent = "disable";
		action.sessionId = sessionId;
		ChangeImageStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeImageState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageInventory inventory = res.value.inventory;
			System.out.println(String.format("changeImageState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.2.7 更新镜像信息
	 * @param uuid
	 * @param platform
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateImageAction.Result updateImage(String uuid, String platform, String sessionId){
		UpdateImageAction action = new UpdateImageAction();
		action.uuid = uuid;
		action.platform = platform;
		action.sessionId = sessionId;
		UpdateImageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateImage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageInventory inventory = res.value.inventory;
			System.out.println(String.format("updateImage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.2.8 刷新镜像大小信息
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SyncImageSizeAction.Result syncImageSize(String uuid, String sessionId){
		SyncImageSizeAction action = new SyncImageSizeAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		SyncImageSizeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("syncImageSize error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageInventory inventory = res.value.inventory;
			System.out.println(String.format("syncImageSize successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.2.10 从根云盘创建根云盘镜像
	 * @param name
	 * @param backupStorageUuids
	 * @param rootVolumeUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateRootVolumeTemplateFromRootVolumeAction.Result createRootVolumeTemplateFromRootVolume(String name, String backupStorageUuids,
			String rootVolumeUuid, String sessionId){
		
		CreateRootVolumeTemplateFromRootVolumeAction action = new CreateRootVolumeTemplateFromRootVolumeAction();
		action.name = name;
		action.backupStorageUuids = Arrays.asList(backupStorageUuids);
		action.rootVolumeUuid = rootVolumeUuid;
		action.platform = "Linux";
		action.system = false;
		action.sessionId = sessionId;
		CreateRootVolumeTemplateFromRootVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createRootVolumeTemplateFromRootVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageInventory inventory = res.value.inventory;
			System.out.println(String.format("createRootVolumeTemplateFromRootVolume successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.2.11 创建根云盘镜像
	 * @param snapshotUuid
	 * @param name
	 * @param backupStorageUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateRootVolumeTemplateFromVolumeSnapshotAction.Result createRootVolumeTemplateFromVolumeSnapshot(String snapshotUuid, String name,
			String backupStorageUuids, String sessionId){
		
		CreateRootVolumeTemplateFromVolumeSnapshotAction action = new CreateRootVolumeTemplateFromVolumeSnapshotAction();
		action.snapshotUuid = snapshotUuid;
		action.name = name;
		action.backupStorageUuids = Arrays.asList(backupStorageUuids);
		action.system = false;
		action.sessionId = sessionId;
		CreateRootVolumeTemplateFromVolumeSnapshotAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createRootVolumeTemplateFromVolumeSnapshot error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageInventory inventory = res.value.inventory;
			System.out.println(String.format("createRootVolumeTemplateFromVolumeSnapshot successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.2.12 从云盘创建数据云盘镜像
	 * @param name
	 * @param volumeUuid
	 * @param backupStorageUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateDataVolumeTemplateFromVolumeAction.Result createDataVolumeTemplateFromVolume(String name, String volumeUuid, 
			String backupStorageUuids,String sessionId){
		
		CreateDataVolumeTemplateFromVolumeAction action = new CreateDataVolumeTemplateFromVolumeAction();
		action.name = name;
		action.volumeUuid = volumeUuid;
		action.backupStorageUuids = Arrays.asList(backupStorageUuids);
		action.sessionId = sessionId;
		CreateDataVolumeTemplateFromVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createDataVolumeTemplateFromVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageInventory inventory = res.value.inventory;
			System.out.println(String.format("createDataVolumeTemplateFromVolume successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {
		
		String sessionId = ZStackUtils.ZStackLogin();
		
		ImageAction image = new ImageAction();
		
		// 创建镜像 Begin
//		image.addImage("centos6-test1", "file:///opt/centos6.8-mini.qcow2", "qcow2", "Linux", 
//				Arrays.asList("004a15e5210e46de85d02a34da626e83"), sessionId);
		// 创建镜像 End
		
		// 查询镜像 Begin
		image.queryImage("8f059b20bf4b4a64aef34cf7e6cc36bb", sessionId);
		// 查询镜像 End
		
		// 更新镜像信息 Begin
		image.updateImage("8f059b20bf4b4a64aef34cf7e6cc36bb", "Windows", sessionId);
		// 更新镜像信息 End
		
		// 删除镜像 Begin
		image.deleteImage("b66478c2ccbb4c5488ac958c9c920c85", Arrays.asList("004a15e5210e46de85d02a34da626e83"), sessionId);
		// 删除镜像 End
	}
}