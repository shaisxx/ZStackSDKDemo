package com.shtd.zstack.platformmgt;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import org.zstack.sdk.ConsoleInventory;
import org.zstack.sdk.ConsoleProxyAgentInventory;
import org.zstack.sdk.QueryConsoleProxyAgentAction;
import org.zstack.sdk.ReconnectConsoleProxyAgentAction;
import org.zstack.sdk.RequestConsoleAccessAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 7 平台管理-控制台相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class ConsoleAction {

	/**
	 * 7.4.1 请求控制台访问地址
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RequestConsoleAccessAction.Result requestConsoleAccess(String vmInstanceUuid, String sessionId){
		RequestConsoleAccessAction action = new RequestConsoleAccessAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		RequestConsoleAccessAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("requestConsoleAccess error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			ConsoleInventory inventory = res.value.inventory;
			System.out.println(String.format("requestConsoleAccess successfully scheme:%s,hostname:%s,port:%s,token:%s", 
					inventory.scheme, inventory.hostname, inventory.port, inventory.token));
		}
		return res;
	}
	
	/**
	 * 7.4.2 查询控制台代理
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryConsoleProxyAgentAction.Result queryConsoleProxyAgent(String uuid, String sessionId){
		QueryConsoleProxyAgentAction action = new QueryConsoleProxyAgentAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryConsoleProxyAgentAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryConsoleProxyAgent error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ConsoleProxyAgentInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryConsoleProxyAgent successfully consoleProxyOverriddenIp:%s,uuid:%s,description:%s,managementIp:%s,state:%s", 
						inventory.consoleProxyOverriddenIp, inventory.uuid, inventory.description, inventory.managementIp, inventory.state));
			}
		}
		return res;
	}
	
	/**
	 * 7.4.3 重连控制台代理
	 * @param agentUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ReconnectConsoleProxyAgentAction.Result reconnectConsoleProxyAgent(String agentUuids, String sessionId){
		ReconnectConsoleProxyAgentAction action = new ReconnectConsoleProxyAgentAction();
		action.agentUuids = Arrays.asList(agentUuids);
		action.sessionId = sessionId;
		ReconnectConsoleProxyAgentAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reconnectConsoleProxyAgent error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			Map<String, Object> inventory = res.value.inventory;
			for (Entry<String, Object> entry: inventory.entrySet()) {
				System.out.println(String.format("reconnectConsoleProxyAgent successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
			
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		ConsoleAction console = new ConsoleAction();
		
		// 请求控制台访问地址 Begin
		console.requestConsoleAccess("e003a205042f49fc96349b6da42b5efa", sessionId);
		// 请求控制台访问地址 End
	}
}