package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.ChangeVipStateAction;
import org.zstack.sdk.CreateVipAction;
import org.zstack.sdk.DeleteVipAction;
import org.zstack.sdk.QueryVipAction;
import org.zstack.sdk.UpdateVipAction;
import org.zstack.sdk.VipInventory;

/**
 * 5 网络-网络服务相关接口-虚拟IP相关接口
 * @author Josh
 * @date 2017-12-04
 */
public class VipAction {
	
	/**
	 * 5.4.4.1 创建虚拟IP
	 * @param name
	 * @param l3NetworkUuid
	 * @param requiredIp
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateVipAction.Result createVip(String name, String l3NetworkUuid, String requiredIp, String sessionId){
		CreateVipAction action = new CreateVipAction();
		action.name = name;
		action.l3NetworkUuid = l3NetworkUuid;
		action.requiredIp = requiredIp;
		action.sessionId = sessionId;
		CreateVipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createVip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VipInventory inventory = res.value.inventory;
			System.out.println(String.format("createVip successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.4.2 删除虚拟IP
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVipAction.Result deleteVip(String uuid, String sessionId){
		DeleteVipAction action = new DeleteVipAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteVipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVip successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.4.3 查询虚拟IP
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVipAction.Result queryVip(String uuid, String sessionId){
		QueryVipAction action = new QueryVipAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VipInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVip successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.4.4 更新虚拟IP
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateVipAction.Result updateVip(String uuid, String name, String sessionId){
		UpdateVipAction action = new UpdateVipAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateVipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateVip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VipInventory inventory = res.value.inventory;
			System.out.println(String.format("updateVip successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.4.5 更改虚拟IP启用状态 
	 * @param uuid
	 * @param stateEvent "enable"
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeVipStateAction.Result changeVipState(String uuid, String stateEvent, String sessionId){
		ChangeVipStateAction action = new ChangeVipStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangeVipStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateVip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VipInventory inventory = res.value.inventory;
			System.out.println(String.format("updateVip successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}