package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.AttachPortForwardingRuleAction;
import org.zstack.sdk.ChangePortForwardingRuleStateAction;
import org.zstack.sdk.CreatePortForwardingRuleAction;
import org.zstack.sdk.DeletePortForwardingRuleAction;
import org.zstack.sdk.DetachPortForwardingRuleAction;
import org.zstack.sdk.GetPortForwardingAttachableVmNicsAction;
import org.zstack.sdk.PortForwardingRuleInventory;
import org.zstack.sdk.QueryPortForwardingRuleAction;
import org.zstack.sdk.UpdatePortForwardingRuleAction;
import org.zstack.sdk.VmNicInventory;

/**
 * 5 网络-网络服务相关接口-端口转发相关接口
 * @author Josh
 * @date 2017-12-04
 */
public class PortForwardingRuleAction {
	
	/**
	 * 5.4.6.1 创建端口转发规则
	 * @param vipUuid
	 * @param vipPortStart
	 * @param protocolType
	 * @param vmNicUuid
	 * @param name
	 * @param sessionId
	 * @author Josh
	 */
	public CreatePortForwardingRuleAction.Result createPortForwardingRule(String vipUuid, Integer vipPortStart, String protocolType, 
			String vmNicUuid, String name, String sessionId){
		
		CreatePortForwardingRuleAction action = new CreatePortForwardingRuleAction();
		action.vipUuid = vipUuid;
		action.vipPortStart = vipPortStart;
		action.protocolType = protocolType;
		action.vmNicUuid = vmNicUuid;
		action.name = name;
		action.sessionId = sessionId;
		CreatePortForwardingRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createPortForwardingRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PortForwardingRuleInventory inventory = res.value.inventory;
			System.out.println(String.format("createPortForwardingRule successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.6.2 删除端口转发规则
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeletePortForwardingRuleAction.Result deletePortForwardingRule(String uuid, String sessionId){
		DeletePortForwardingRuleAction action = new DeletePortForwardingRuleAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeletePortForwardingRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deletePortForwardingRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deletePortForwardingRule successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.6.3 查询端口转发规则
	 * @param name
	 * @param state "Enabled"
	 * @param sessionId
	 * @author Josh
	 */
	public QueryPortForwardingRuleAction.Result queryPortForwardingRule(String name, String state, String sessionId){
		QueryPortForwardingRuleAction action = new QueryPortForwardingRuleAction();
		action.conditions = Arrays.asList("name=" + name,"state=");
		action.sessionId = "4311e616ca8b4bb19d0792be1e08f3b8";
		QueryPortForwardingRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryPortForwardingRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				PortForwardingRuleInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryPortForwardingRule successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.6.4 更新端口转发规则
	 * @param uuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdatePortForwardingRuleAction.Result updatePortForwardingRule(String uuid, String name, String description, String sessionId){
		UpdatePortForwardingRuleAction action = new UpdatePortForwardingRuleAction();
		action.uuid = uuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		UpdatePortForwardingRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updatePortForwardingRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PortForwardingRuleInventory inventory = res.value.inventory;
			System.out.println(String.format("updatePortForwardingRule successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.6.5 改变端口转发规则的状态
	 * @param uuid
	 * @param stateEvent 
	 * @param sessionId
	 */
	public ChangePortForwardingRuleStateAction.Result changePortForwardingRuleState(String uuid, String stateEvent, String sessionId){
		ChangePortForwardingRuleStateAction action = new ChangePortForwardingRuleStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangePortForwardingRuleStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changePortForwardingRuleState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PortForwardingRuleInventory inventory = res.value.inventory;
			System.out.println(String.format("changePortForwardingRuleState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.6.6 获取云主机网卡列表
	 * @param ruleUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetPortForwardingAttachableVmNicsAction.Result getPortForwardingAttachableVmNics(String ruleUuid, String sessionId){
		GetPortForwardingAttachableVmNicsAction action = new GetPortForwardingAttachableVmNicsAction();
		action.ruleUuid = ruleUuid;
		action.sessionId = sessionId;
		GetPortForwardingAttachableVmNicsAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryPortForwardingRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VmNicInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryPortForwardingRule successfully deviceId:%s,uuid:%s,vmInstanceUuid:%s", inventory.deviceId, inventory.uuid, inventory.vmInstanceUuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.6.7 挂载规则到虚拟机网卡上
	 * @param ruleUuid
	 * @param vmNicUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachPortForwardingRuleAction.Result attachPortForwardingRule(String ruleUuid, String vmNicUuid, String sessionId){
		AttachPortForwardingRuleAction action = new AttachPortForwardingRuleAction();
		action.ruleUuid = ruleUuid;
		action.vmNicUuid = vmNicUuid;
		action.sessionId = sessionId;
		AttachPortForwardingRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changePortForwardingRuleState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PortForwardingRuleInventory inventory = res.value.inventory;
			System.out.println(String.format("changePortForwardingRuleState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.6.8 从虚拟机网卡卸载规则
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachPortForwardingRuleAction.Result detachPortForwardingRule(String uuid, String sessionId){
		DetachPortForwardingRuleAction action = new DetachPortForwardingRuleAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DetachPortForwardingRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachPortForwardingRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PortForwardingRuleInventory inventory = res.value.inventory;
			System.out.println(String.format("detachPortForwardingRule successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}