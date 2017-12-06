package com.shtd.zstack.hardware;

import org.zstack.sdk.AddSharedMountPointPrimaryStorageAction;
import org.zstack.sdk.PrimaryStorageInventory;

/**
 * 4 硬件设施-主存储相关接口-Shared Mount Point主存储相关接口
 * @author Josh
 * @date 2017-12-06
 */
public class SharedMountPointPrimaryStorageAction {
	
	/**
	 * 4.4.16.1 添加一个共享挂载点的主存储
	 * @param url
	 * @param name
	 * @param type
	 * @param zoneUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddSharedMountPointPrimaryStorageAction.Result addSharedMountPointPrimaryStorage(String url, String name, String type, String zoneUuid, String sessionId){
		AddSharedMountPointPrimaryStorageAction action = new AddSharedMountPointPrimaryStorageAction();
		action.url = url;
		action.name = name;
		action.type = type;
		action.zoneUuid = zoneUuid;
		action.sessionId = sessionId;
		AddSharedMountPointPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addSharedMountPointPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("addSharedMountPointPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}