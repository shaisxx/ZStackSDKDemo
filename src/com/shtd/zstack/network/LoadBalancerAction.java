package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.AddVmNicToLoadBalancerAction;
import org.zstack.sdk.CreateLoadBalancerAction;
import org.zstack.sdk.CreateLoadBalancerListenerAction;
import org.zstack.sdk.DeleteLoadBalancerAction;
import org.zstack.sdk.DeleteLoadBalancerListenerAction;
import org.zstack.sdk.GetCandidateVmNicsForLoadBalancerAction;
import org.zstack.sdk.LoadBalancerInventory;
import org.zstack.sdk.LoadBalancerListenerInventory;
import org.zstack.sdk.QueryLoadBalancerAction;
import org.zstack.sdk.QueryLoadBalancerListenerAction;
import org.zstack.sdk.RefreshLoadBalancerAction;
import org.zstack.sdk.RemoveVmNicFromLoadBalancerAction;
import org.zstack.sdk.VmNicInventory;

/**
 * 5 网络-网络服务相关接口-负载均衡相关接口
 * @author Josh
 * @date 2017-12-04
 */
public class LoadBalancerAction {
	
	/**
	 * 5.4.7.1 创建负载均衡器
	 * @param name
	 * @param vipUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateLoadBalancerAction.Result createLoadBalancer(String name, String vipUuid, String sessionId){
		CreateLoadBalancerAction action = new CreateLoadBalancerAction();
		action.name = name;
		action.vipUuid = vipUuid;
		action.sessionId = sessionId;
		CreateLoadBalancerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			LoadBalancerInventory inventory = res.value.inventory;
			System.out.println(String.format("createLoadBalancer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.7.2 删除负载均衡器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteLoadBalancerAction.Result deleteLoadBalancer(String uuid, String sessionId){
		DeleteLoadBalancerAction action = new DeleteLoadBalancerAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DeleteLoadBalancerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteLoadBalancer successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.7.3 查询负载均衡器
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryLoadBalancerAction.Result queryLoadBalancer(String sessionId){
		QueryLoadBalancerAction action = new QueryLoadBalancerAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryLoadBalancerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				LoadBalancerInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryLoadBalancer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.7.4 刷新负载均衡器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RefreshLoadBalancerAction.Result refreshLoadBalancer(String uuid, String sessionId){
		RefreshLoadBalancerAction action = new RefreshLoadBalancerAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		RefreshLoadBalancerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("refreshLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			LoadBalancerInventory inventory = res.value.inventory;
			System.out.println(String.format("refreshLoadBalancer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.7.5 创建负载均衡监听器
	 * @param loadBalancerUuid
	 * @param name
	 * @param instancePort
	 * @param loadBalancerPort
	 * @param protocol
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateLoadBalancerListenerAction.Result createLoadBalancerListener(String loadBalancerUuid, String name, Integer instancePort, int loadBalancerPort, 
			String protocol, String sessionId){
		CreateLoadBalancerListenerAction action = new CreateLoadBalancerListenerAction();
		action.loadBalancerUuid = loadBalancerUuid;
		action.name = name;
		action.instancePort = instancePort;
		action.loadBalancerPort = loadBalancerPort;
		action.protocol = protocol;
		action.sessionId = sessionId;
		CreateLoadBalancerListenerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("refreshLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			LoadBalancerListenerInventory inventory = res.value.inventory;
			System.out.println(String.format("refreshLoadBalancer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;		
	}
	
	/**
	 * 5.4.7.6 删除负载均衡监听器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteLoadBalancerListenerAction.Result deleteLoadBalancerListener(String uuid, String sessionId){
		DeleteLoadBalancerListenerAction action = new DeleteLoadBalancerListenerAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DeleteLoadBalancerListenerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteLoadBalancerListener error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			LoadBalancerInventory inventory = res.value.inventory;
			System.out.println(String.format("deleteLoadBalancerListener successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;	
	}
	
	/**
	 * 5.4.7.7 查询负载均衡监听器
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryLoadBalancerListenerAction.Result queryLoadBalancerListener(String sessionId){
		QueryLoadBalancerListenerAction action = new QueryLoadBalancerListenerAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryLoadBalancerListenerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				LoadBalancerListenerInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryLoadBalancer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.7.8 获取云主机网卡
	 * @param listenerUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetCandidateVmNicsForLoadBalancerAction.Result getCandidateVmNicsForLoadBalancer(String listenerUuid, String sessionId){
		GetCandidateVmNicsForLoadBalancerAction action = new GetCandidateVmNicsForLoadBalancerAction();
		action.listenerUuid = listenerUuid;
		action.sessionId = sessionId;
		GetCandidateVmNicsForLoadBalancerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getCandidateVmNicsForLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VmNicInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getCandidateVmNicsForLoadBalancer successfully deviceId:%s,uuid:%s,l3NetworkUuid:%s", inventory.deviceId, inventory.uuid, inventory.l3NetworkUuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.7.9 添加云主机网卡到负载均衡器
	 * @param vmNicUuids
	 * @param listenerUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddVmNicToLoadBalancerAction.Result addVmNicToLoadBalancer(String vmNicUuids, String listenerUuid, String sessionId){
		AddVmNicToLoadBalancerAction action = new AddVmNicToLoadBalancerAction();
		action.vmNicUuids = Arrays.asList(vmNicUuids);
		action.listenerUuid = listenerUuid;
		action.sessionId = sessionId;
		AddVmNicToLoadBalancerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addVmNicToLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			LoadBalancerListenerInventory inventory = res.value.inventory;
			System.out.println(String.format("addVmNicToLoadBalancer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.7.10 从负载均衡器移除云主机网卡 
	 * @param vmNicUuids
	 * @param listenerUuid
	 * @param sessionId
	 * @author Josh
	 */
	public RemoveVmNicFromLoadBalancerAction.Result removeVmNicFromLoadBalancer(String vmNicUuids, String listenerUuid, String sessionId){
		RemoveVmNicFromLoadBalancerAction action = new RemoveVmNicFromLoadBalancerAction();
		action.vmNicUuids = Arrays.asList(vmNicUuids);
		action.listenerUuid = listenerUuid;
		action.sessionId = sessionId;
		RemoveVmNicFromLoadBalancerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("removeVmNicFromLoadBalancer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("removeVmNicFromLoadBalancer successfully vmNicUuids:%s,listenerUuid:%s", vmNicUuids, listenerUuid));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}