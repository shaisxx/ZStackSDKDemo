package com.shtd.zstack.vcenter;

import java.util.Arrays;

import org.zstack.sdk.DeleteVCenterAction;
import org.zstack.sdk.QueryVCenterAction;
import org.zstack.sdk.QueryVCenterBackupStorageAction;
import org.zstack.sdk.QueryVCenterClusterAction;
import org.zstack.sdk.QueryVCenterDatacenterAction;
import org.zstack.sdk.QueryVCenterPrimaryStorageAction;
import org.zstack.sdk.VCenterBackupStorageInventory;
import org.zstack.sdk.VCenterClusterInventory;
import org.zstack.sdk.VCenterDatacenterInventory;
import org.zstack.sdk.VCenterInventory;
import org.zstack.sdk.VCenterPrimaryStorageInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 6 vCenter-vCenter相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class VCenterAction {

	/**
	 * 6.1.1 查询vCenter资源
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVCenterAction.Result queryVCenter(String uuid, String sessionId){
		QueryVCenterAction action = new QueryVCenterAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVCenterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVCenter error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VCenterInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVCenter successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 6.1.2 删除vCenter资源
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVCenterAction.Result deleteVCenter(String uuid, String sessionId){
		DeleteVCenterAction action = new DeleteVCenterAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteVCenterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVCenter error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVCenter successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 6.1.3 查询vCenter下记录的数据中心
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVCenterDatacenterAction.Result queryVCenterDatacenter(String uuid, String sessionId){
		QueryVCenterDatacenterAction action = new QueryVCenterDatacenterAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVCenterDatacenterAction.Result res = action.call();	
		if (res.error != null) {
			System.out.println(String.format("queryVCenterDatacenter error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VCenterDatacenterInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVCenterDatacenter successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 6.1.4 查询vCenter集群
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVCenterClusterAction.Result queryVCenterCluster(String uuid, String sessionId){
		QueryVCenterClusterAction action = new QueryVCenterClusterAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVCenterClusterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVCenterCluster error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VCenterClusterInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVCenterCluster successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 6.1.5 查询vCenter主存储
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVCenterPrimaryStorageAction.Result queryVCenterPrimaryStorage(String uuid, String sessionId){
		QueryVCenterPrimaryStorageAction action = new QueryVCenterPrimaryStorageAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVCenterPrimaryStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVCenterPrimaryStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VCenterPrimaryStorageInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVCenterPrimaryStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 6.1.6 查询vCenter镜像服务器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVCenterBackupStorageAction.Result queryVCenterBackupStorage(String uuid, String sessionId){
		QueryVCenterBackupStorageAction action = new QueryVCenterBackupStorageAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVCenterBackupStorageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVCenterBackupStorage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VCenterBackupStorageInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVCenterBackupStorage successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		VCenterAction action = new VCenterAction();
		
		// 查询vCenter资源 Begin
		action.queryVCenter("ca7c23f51fca4be793340a6134d5c5a0", sessionId);
		// 查询vCenter资源 End
		
		//  删除vCenter资源 Begin
		action.deleteVCenter("ca7c23f51fca4be793340a6134d5c5a0", sessionId);
		//  删除vCenter资源 End
		
	}
}