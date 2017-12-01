package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.BackupStorageInventory;
import org.zstack.sdk.QueryBackupStorageAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 硬件设施-镜像服务器相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class BackupStorageAction {

	/**
	 * 查询镜像服务器
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
			
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		BackupStorageAction backupStorage = new BackupStorageAction();
		
		// 查询物理机 Begin
		backupStorage.queryBackupStorage("004a15e5210e46de85d02a34da626e83", sessionId);
		// 查询区域 End
		
	}
}