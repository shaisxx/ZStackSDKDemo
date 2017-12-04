package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.AddSecurityGroupRuleAction;
import org.zstack.sdk.AddVmNicToSecurityGroupAction;
import org.zstack.sdk.AttachSecurityGroupToL3NetworkAction;
import org.zstack.sdk.ChangeSecurityGroupStateAction;
import org.zstack.sdk.CreateSecurityGroupAction;
import org.zstack.sdk.DeleteSecurityGroupAction;
import org.zstack.sdk.DeleteSecurityGroupRuleAction;
import org.zstack.sdk.DeleteVmNicFromSecurityGroupAction;
import org.zstack.sdk.DetachSecurityGroupFromL3NetworkAction;
import org.zstack.sdk.GetCandidateVmNicForSecurityGroupAction;
import org.zstack.sdk.QuerySecurityGroupAction;
import org.zstack.sdk.QuerySecurityGroupRuleAction;
import org.zstack.sdk.QueryVmNicInSecurityGroupAction;
import org.zstack.sdk.SecurityGroupInventory;
import org.zstack.sdk.SecurityGroupRuleInventory;
import org.zstack.sdk.UpdateSecurityGroupAction;
import org.zstack.sdk.VmNicInventory;
import org.zstack.sdk.VmNicSecurityGroupRefInventory;

/**
 * 5 网络-网络服务相关接口-安全组相关接口
 * @author Josh
 * @date 2017-12-04
 */
public class SecurityGroupAction {
	
	/**
	 * 5.4.3.1 创建安全组
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateSecurityGroupAction.Result createSecurityGroup(String name, String description, String sessionId){
		CreateSecurityGroupAction action = new CreateSecurityGroupAction();
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		CreateSecurityGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createSecurityGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SecurityGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("createSecurityGroup successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.3.2 删除安全组
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteSecurityGroupAction.Result deleteSecurityGroup(String uuid, String sessionId){
		DeleteSecurityGroupAction action = new DeleteSecurityGroupAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteSecurityGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteSecurityGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteSecurityGroup successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.3.3 查询安全组
	 * @param name
	 * @param state "Enabled"
	 * @param sessionId
	 * @author Josh
	 */
	public QuerySecurityGroupAction.Result querySecurityGroup(String name, String state, String sessionId){
		QuerySecurityGroupAction action = new QuerySecurityGroupAction();
		action.conditions = Arrays.asList("name=" + name,"state=" + state);
		action.sessionId = sessionId;
		QuerySecurityGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("querySecurityGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				SecurityGroupInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("querySecurityGroup successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.3.4 更新安全组
	 * @param uuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateSecurityGroupAction.Result updateSecurityGroup(String uuid, String name, String description, String sessionId){
		UpdateSecurityGroupAction action = new UpdateSecurityGroupAction();
		action.uuid = uuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		UpdateSecurityGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateSecurityGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SecurityGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("updateSecurityGroup successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.3.5 改变安全组状态
	 * @param uuid
	 * @param stateEvent "disable"
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeSecurityGroupStateAction.Result changeSecurityGroupState(String uuid, String stateEvent, String sessionId){
		ChangeSecurityGroupStateAction action = new ChangeSecurityGroupStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangeSecurityGroupStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeSecurityGroupState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SecurityGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("changeSecurityGroupState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.3.6 挂载安全组到L3网络
	 * @param securityGroupUuid
	 * @param l3NetworkUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachSecurityGroupToL3NetworkAction.Result attachSecurityGroupToL3Network(String securityGroupUuid, String l3NetworkUuid, String sessionId){
		AttachSecurityGroupToL3NetworkAction action = new AttachSecurityGroupToL3NetworkAction();
		action.securityGroupUuid = securityGroupUuid;
		action.l3NetworkUuid = l3NetworkUuid;
		action.sessionId = sessionId;
		AttachSecurityGroupToL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachSecurityGroupToL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SecurityGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("attachSecurityGroupToL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.3.7 从L3网络卸载安全组
	 * @param securityGroupUuid
	 * @param l3NetworkUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachSecurityGroupFromL3NetworkAction.Result detachSecurityGroupFromL3Network(String securityGroupUuid, String l3NetworkUuid, String sessionId){
		DetachSecurityGroupFromL3NetworkAction action = new DetachSecurityGroupFromL3NetworkAction();
		action.securityGroupUuid = securityGroupUuid;
		action.l3NetworkUuid = l3NetworkUuid;
		action.sessionId = sessionId;
		DetachSecurityGroupFromL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachSecurityGroupFromL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SecurityGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("detachSecurityGroupFromL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.3.8 获取网卡列表清单
	 * @param securityGroupUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetCandidateVmNicForSecurityGroupAction.Result getCandidateVmNicForSecurityGroup(String securityGroupUuid, String sessionId){
		GetCandidateVmNicForSecurityGroupAction action = new GetCandidateVmNicForSecurityGroupAction();
		action.securityGroupUuid = securityGroupUuid;
		action.sessionId = sessionId;
		GetCandidateVmNicForSecurityGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getCandidateVmNicForSecurityGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VmNicInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getCandidateVmNicForSecurityGroup successfully deviceId:%s,uuid:%s,ip:%s", inventory.deviceId, inventory.uuid, inventory.ip));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.3.9 添加虚拟机网卡到安全组
	 * @param securityGroupUuid
	 * @param vmNicUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddVmNicToSecurityGroupAction.Result addVmNicToSecurityGroup(String securityGroupUuid, String vmNicUuids, String sessionId){
		AddVmNicToSecurityGroupAction action = new AddVmNicToSecurityGroupAction();
		action.securityGroupUuid = "b651075ffcac475c91d3edaa5e9ade75";
		action.vmNicUuids = Arrays.asList("ff0f6c13311b46ddb186a95c0d18350a");
		action.sessionId = "605fca0559284eff95d78e418508ff2f";
		AddVmNicToSecurityGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addVmNicToSecurityGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("addVmNicToSecurityGroup successfully securityGroupUuid:%s,vmNicUuids:%s", securityGroupUuid, vmNicUuids));
		}
		return res;
	}
	
	/**
	 * 5.4.3.10 从安全组删除虚拟机网卡
	 * @param securityGroupUuid
	 * @param vmNicUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVmNicFromSecurityGroupAction.Result deleteVmNicFromSecurityGroup(String securityGroupUuid, String vmNicUuids, String sessionId){
		DeleteVmNicFromSecurityGroupAction action = new DeleteVmNicFromSecurityGroupAction();
		action.securityGroupUuid = securityGroupUuid;
		action.vmNicUuids = Arrays.asList(vmNicUuids);
		action.sessionId = sessionId;
		DeleteVmNicFromSecurityGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVmNicFromSecurityGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVmNicFromSecurityGroup successfully securityGroupUuid:%s,vmNicUuids:%s", securityGroupUuid, vmNicUuids));
		}
		return res;
	}
	
	/**
	 * 5.4.3.11 查询应用了安全组的网卡列表
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVmNicInSecurityGroupAction.Result queryVmNicInSecurityGroup(String sessionId){
		QueryVmNicInSecurityGroupAction action = new QueryVmNicInSecurityGroupAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryVmNicInSecurityGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVmNicInSecurityGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VmNicSecurityGroupRefInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVmNicInSecurityGroup successfully vmNicUuid:%s,vmInstanceUuid:%s,securityGroupUuid:%s", inventory.vmNicUuid, inventory.vmInstanceUuid, inventory.securityGroupUuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.3.12 添加规则到安全组
	 * @param securityGroupUuid
	 * @param type
	 * @param startPort
	 * @param endPort
	 * @param protocol
	 * @param allowedCidr
	 * @param remoteSecurityGroupUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddSecurityGroupRuleAction.Result addSecurityGroupRule(String securityGroupUuid, String type, String startPort, String endPort, 
			String protocol, String allowedCidr, String remoteSecurityGroupUuids, String sessionId){
		
		AddSecurityGroupRuleAction action = new AddSecurityGroupRuleAction();
		action.securityGroupUuid = securityGroupUuid;
		action.rules = Arrays.asList("type:" + type, "startPort:" + startPort, "endPort:" + endPort, "protocol:" + protocol, "allowedCidr:" + allowedCidr);
		action.remoteSecurityGroupUuids = Arrays.asList(remoteSecurityGroupUuids);
		action.sessionId = sessionId;
		AddSecurityGroupRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addSecurityGroupRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SecurityGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("addSecurityGroupRule successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.3.13 查询安全组规则
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QuerySecurityGroupRuleAction.Result querySecurityGroupRule(String sessionId){
		QuerySecurityGroupRuleAction action = new QuerySecurityGroupRuleAction();
		action.conditions = Arrays.asList("endPort=22","state=Enabled");
		action.sessionId = "362452e4e8a746ce981a5a54c7390445";
		QuerySecurityGroupRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("querySecurityGroupRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				SecurityGroupRuleInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("querySecurityGroupRule successfully uuid:%s,allowedCidr:%s,protocol:%s", inventory.uuid, inventory.allowedCidr, inventory.protocol));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.3.14 删除安全组规则
	 * @param ruleUuids
	 * @param sessionId
	 * @author Josh
	 */
	public DeleteSecurityGroupRuleAction.Result deleteSecurityGroupRule(String ruleUuids, String sessionId){
		DeleteSecurityGroupRuleAction action = new DeleteSecurityGroupRuleAction();
		action.ruleUuids = Arrays.asList(ruleUuids);
		action.sessionId = sessionId;
		DeleteSecurityGroupRuleAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteSecurityGroupRule error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SecurityGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("deleteSecurityGroupRule successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}