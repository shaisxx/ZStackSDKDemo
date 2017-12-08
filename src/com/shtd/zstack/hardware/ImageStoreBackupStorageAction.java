package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.AddImageStoreBackupStorageAction;
import org.zstack.sdk.BackupStorageInventory;
import org.zstack.sdk.ImageStoreBackupStorageInventory;
import org.zstack.sdk.QueryImageStoreBackupStorageAction;
import org.zstack.sdk.ReclaimSpaceFromImageStoreAction;
import org.zstack.sdk.ReconnectImageStoreBackupStorageAction;
import org.zstack.sdk.UpdateImageStoreBackupStorageAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 4 硬件设施-镜像服务器相关接口-镜像仓库服务器相关接口
 * @author Josh
 * @date 2017-12-01
 */
public class ImageStoreBackupStorageAction {

	/**
	 * 4.5.12.1 添加镜像仓库服务器
	 * @param hostname
	 * @param username
	 * @param password
	 * @param sshPort
	 * @param url
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddImageStoreBackupStorageAction.Result addImageStoreBackupStorage(String hostname, String username, 
			String password, int sshPort, String url, String name, String sessionId){
		AddImageStoreBackupStorageAction action = new AddImageStoreBackupStorageAction();
		action.hostname = hostname;
		action.username = username;
		action.password = password;
		action.sshPort = sshPort;
		action.url = url;
		action.name = name;
		action.importImages = false;
		action.sessionId = sessionId;
		AddImageStoreBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addImageStoreBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			ImageStoreBackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("addImageStoreBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.12.2 查询镜像仓库服务器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryImageStoreBackupStorageAction.Result queryImageStoreBackupStorage(String uuid, String sessionId){
		QueryImageStoreBackupStorageAction action = new QueryImageStoreBackupStorageAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryImageStoreBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryImageStoreBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ImageStoreBackupStorageInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryImageStoreBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.5.12.3 更新镜像仓库服务器信息
	 * @param hostname
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateImageStoreBackupStorageAction.Result updateImageStoreBackupStorage(String hostname, String uuid, String sessionId){
		UpdateImageStoreBackupStorageAction action = new UpdateImageStoreBackupStorageAction();
		action.hostname = hostname;
		action.uuid = uuid;
		action.sessionId = sessionId;
		UpdateImageStoreBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateImageStoreBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("updateImageStoreBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.12.4 重连镜像仓库服务器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ReconnectImageStoreBackupStorageAction.Result reconnectImageStoreBackupStorage(String uuid, String sessionId){
		ReconnectImageStoreBackupStorageAction action = new ReconnectImageStoreBackupStorageAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		ReconnectImageStoreBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reconnectImageStoreBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BackupStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("reconnectImageStoreBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.5.12.5 从镜像仓库回收磁盘空间
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ReclaimSpaceFromImageStoreAction.Result reclaimSpaceFromImageStore(String uuid, String sessionId){
		ReclaimSpaceFromImageStoreAction action = new ReclaimSpaceFromImageStoreAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		ReclaimSpaceFromImageStoreAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reclaimSpaceFromImageStore error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("reclaimSpaceFromImageStore successfully uuid:%s", uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		ImageStoreBackupStorageAction action = new ImageStoreBackupStorageAction();
		
		// 查询镜像仓库服务器 Begin
		action.queryImageStoreBackupStorage("uuid", sessionId);
		// 查询镜像仓库服务器 End
	}
}