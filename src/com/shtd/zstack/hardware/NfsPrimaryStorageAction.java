package com.shtd.zstack.hardware;

import org.zstack.sdk.AddNfsPrimaryStorageAction;
import org.zstack.sdk.PrimaryStorageInventory;

/**
 * 4 硬件设施-主存储相关接口-NFS主存储相关接口
 * @author Josh
 * @date 2017-12-06
 */
public class NfsPrimaryStorageAction {
	
	/**
	 * 4.4.14.1 添加NFS主存储
	 * @param url
	 * @param name
	 * @param type
	 * @param zoneUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddNfsPrimaryStorageAction.Result addNfsPrimaryStorage(String url, String name, String type, String zoneUuid, String sessionId){
		AddNfsPrimaryStorageAction action = new AddNfsPrimaryStorageAction();
		action.url = url;
		action.name = name;
		action.type = type;
		action.zoneUuid = zoneUuid;
		action.sessionId = sessionId;
		AddNfsPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addNfsPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PrimaryStorageInventory inventory = res.value.inventory;
			System.out.println(String.format("addNfsPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}