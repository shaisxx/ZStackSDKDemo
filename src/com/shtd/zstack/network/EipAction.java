package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.AttachEipAction;
import org.zstack.sdk.ChangeEipStateAction;
import org.zstack.sdk.CreateEipAction;
import org.zstack.sdk.DeleteEipAction;
import org.zstack.sdk.DetachEipAction;
import org.zstack.sdk.EipInventory;
import org.zstack.sdk.GetEipAttachableVmNicsAction;
import org.zstack.sdk.QueryEipAction;
import org.zstack.sdk.UpdateEipAction;
import org.zstack.sdk.VmNicInventory;

/**
 * 5 网络-网络服务相关接口-弹性IP相关接口
 * @author Josh
 * @date 2017-12-04
 */
public class EipAction {
	
	/**
	 * 5.4.5.1 创建弹性IP
	 * @param name
	 * @param vipUuid
	 * @param vmNicUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateEipAction.Result createEip(String name, String vipUuid, String vmNicUuid, String sessionId){
		CreateEipAction action = new CreateEipAction();
		action.name = name;
		action.vipUuid = vipUuid;
		action.vmNicUuid = vmNicUuid;
		action.sessionId = sessionId;
		CreateEipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createEip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			EipInventory inventory = res.value.inventory;
			System.out.println(String.format("createEip successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.5.2 删除弹性IP
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteEipAction.Result deleteEip(String uuid, String sessionId){
		DeleteEipAction action = new DeleteEipAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteEipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteEip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteEip successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.5.3 查询弹性IP
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryEipAction.Result queryEip(String sessionId){
		QueryEipAction action = new QueryEipAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryEipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryEip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				EipInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryEip successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.5.4 更新弹性IP
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateEipAction.Result updateEip(String uuid, String name, String sessionId){
		UpdateEipAction action = new UpdateEipAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateEipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateEip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			EipInventory inventory = res.value.inventory;
			System.out.println(String.format("updateEip successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.5.5 更改弹性IP状态
	 * @param uuid
	 * @param stateEvent "enable"
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeEipStateAction.Result changeEipState(String uuid, String stateEvent, String sessionId){
		ChangeEipStateAction action = new ChangeEipStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangeEipStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeEipState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			EipInventory inventory = res.value.inventory;
			System.out.println(String.format("changeEipState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.5.6 获取可绑定指定弹性IP的云主机网卡
	 * @param eipUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetEipAttachableVmNicsAction.Result getEipAttachableVmNics(String eipUuid, String sessionId){
		GetEipAttachableVmNicsAction action = new GetEipAttachableVmNicsAction();
		action.eipUuid = eipUuid;
		action.sessionId = sessionId;
		GetEipAttachableVmNicsAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getEipAttachableVmNics error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VmNicInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getEipAttachableVmNics successfully uuid:%s,ip:%s,deviceId:%s,gateway:%s", inventory.uuid, inventory.ip, inventory.deviceId, inventory.gateway));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.5.7 绑定弹性IP
	 * @param eipUuid
	 * @param vmNicUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachEipAction.Result attachEip(String eipUuid, String vmNicUuid, String sessionId){
		AttachEipAction action = new AttachEipAction();
		action.eipUuid = eipUuid;
		action.vmNicUuid = vmNicUuid;
		action.sessionId = sessionId;
		AttachEipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachEip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			EipInventory inventory = res.value.inventory;
			System.out.println(String.format("attachEip successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.5.8 解绑弹性IP
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachEipAction.Result detachEip(String uuid, String sessionId){
		DetachEipAction action = new DetachEipAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DetachEipAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachEip error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("detachEip successfully uuid:%s", uuid));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}