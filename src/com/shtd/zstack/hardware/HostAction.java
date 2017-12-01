package com.shtd.zstack.hardware;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zstack.sdk.AddKVMHostAction;
import org.zstack.sdk.ChangeHostStateAction;
import org.zstack.sdk.GetHostAllocatorStrategiesAction;
import org.zstack.sdk.GetHypervisorTypesAction;
import org.zstack.sdk.HostInventory;
import org.zstack.sdk.KvmRunShellAction;
import org.zstack.sdk.QueryHostAction;
import org.zstack.sdk.ReconnectHostAction;
import org.zstack.sdk.ShellResult;
import org.zstack.sdk.UpdateHostAction;
import org.zstack.sdk.UpdateKVMHostAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 4 硬件设施-物理机相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class HostAction {

	/**
	 * 4.3.1 查询物理机
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryHostAction.Result queryHost(String uuid, String sessionId){
		QueryHostAction action = new QueryHostAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				HostInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryHost successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.3.2 更新物理机信息
	 * @param uuid
	 * @param name
	 * @param description
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateHostAction.Result updateHost(String uuid, String name, String description, String sessionId){
		UpdateHostAction action = new UpdateHostAction();
		action.uuid = uuid;
		action.name = name;
		action.description = description;
		action.sessionId = sessionId;
		UpdateHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			HostInventory inventory = res.value.inventory;
			System.out.println(String.format("updateHost successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.3 更新物理机启用状态
	 * @param uuid
	 * @param stateEvent "enable"
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeHostStateAction.Result changeHostState(String uuid, String stateEvent, String sessionId){
		ChangeHostStateAction action = new ChangeHostStateAction();
		action.uuid = uuid;
		action.stateEvent = stateEvent;
		action.sessionId = sessionId;
		ChangeHostStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeHostState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			HostInventory inventory = res.value.inventory;
			System.out.println(String.format("changeHostState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.4 重连物理机
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ReconnectHostAction.Result reconnectHost(String uuid, String sessionId){
		ReconnectHostAction action = new ReconnectHostAction();
		action.uuid = "1c44c6b269724a62b7fb69bd25e5a8a9";
		action.sessionId = "ccc6f95bad044da59080a87e710587c0";
		ReconnectHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reconnectHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			HostInventory inventory = res.value.inventory;
			System.out.println(String.format("reconnectHost successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.5 获取物理机分配策略
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetHostAllocatorStrategiesAction.Result getHostAllocatorStrategies(String sessionId){
		GetHostAllocatorStrategiesAction action = new GetHostAllocatorStrategiesAction();
		action.sessionId = sessionId;
		GetHostAllocatorStrategiesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getHostAllocatorStrategies error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<String> strategies = res.value.strategies;
			if(strategies != null && strategies.size() > 0){
				for (String strategy: strategies) {
					System.out.println(String.format("getHostAllocatorStrategies successfully strategy:%s", strategy));
				}
			}
		}
		return res;
	}
	
	/**
	 * 4.3.6 获取云主机虚拟化技术类型
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetHypervisorTypesAction.Result getHypervisorTypes(String sessionId){
		GetHypervisorTypesAction action = new GetHypervisorTypesAction();
		action.sessionId = sessionId;
		GetHypervisorTypesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getHostAllocatorStrategies error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<String> hypervisorTypes = res.value.hypervisorTypes;
			if(hypervisorTypes != null && hypervisorTypes.size() > 0){
				for (String hypervisorType: hypervisorTypes) {
					System.out.println(String.format("getHostAllocatorStrategies successfully hypervisorType:%s", hypervisorType));
				}
			}
		}
		return res;
	}
	
	/**
	 * 4.3.7 更新KVM主机信息
	 * @param username
	 * @param password
	 * @param sshPort
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateKVMHostAction.Result updateKVMHost(String username, String password, Integer sshPort, String uuid, String sessionId){
		UpdateKVMHostAction action = new UpdateKVMHostAction();
		action.username = username;
		action.password = password;
		action.sshPort = sshPort;
		action.uuid = uuid;
		action.sessionId = sessionId;
		UpdateKVMHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateKVMHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			HostInventory inventory = res.value.inventory;
			System.out.println(String.format("updateKVMHost successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.8 添加KVM主机
	 * @param username
	 * @param password
	 * @param sshPort
	 * @param name
	 * @param managementIp
	 * @param clusterUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddKVMHostAction.Result addKVMHost(String username, String password, Integer sshPort, String name, 
			String managementIp, String clusterUuid, String sessionId){
		
		AddKVMHostAction action = new AddKVMHostAction();
		action.username = username;
		action.password = password;
		action.sshPort = sshPort;
		action.name = name;
		action.managementIp = managementIp;
		action.clusterUuid = clusterUuid;
		action.sessionId = sessionId;
		AddKVMHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addKVMHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			HostInventory inventory = res.value.inventory;
			System.out.println(String.format("addKVMHost successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.9 KVM运行Shell命令
	 * @param hostUuids
	 * @param script "ls"
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public KvmRunShellAction.Result kvmRunShell(String[] hostUuids, String script, String sessionId){
		KvmRunShellAction action = new KvmRunShellAction();
		action.hostUuids = new HashSet(Arrays.asList(hostUuids));
		action.script = script;
		action.sessionId = sessionId;
		KvmRunShellAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("kvmRunShell error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			Map<String, ShellResult> inventory = res.value.inventory;
			if(inventory != null){
				for (Entry<String, ShellResult> entry : inventory.entrySet()) {
					ShellResult shellResult = entry.getValue();
					System.out.println(String.format("kvmRunShell successfully key:%s", entry.getKey()));
					System.out.print(String.format("shellResult returnCode:%s,stdout:%s,stderr:%s,errorCode:%s", 
							shellResult.returnCode, shellResult.stdout, shellResult.stderr, shellResult.errorCode));
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		HostAction host = new HostAction();
		
		// 查询物理机 Begin
		host.queryHost("70f867db4c3a46c5b98eb61122d8603a", sessionId);
		// 查询区域 End
		
		// 更新物理机信息 Begin
		host.updateHost("70f867db4c3a46c5b98eb61122d8603a", "Host-1", "Host-1-New Desc", sessionId);
		// 更新物理机信息 End
	}
}