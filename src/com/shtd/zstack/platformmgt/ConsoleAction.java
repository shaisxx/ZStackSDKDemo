package com.shtd.zstack.platformmgt;

import org.zstack.sdk.ConsoleInventory;
import org.zstack.sdk.RequestConsoleAccessAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 平台管理-控制台相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class ConsoleAction {

	/**
	 * 请求控制台访问地址
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
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		ConsoleAction console = new ConsoleAction();
		
		// 请求控制台访问地址 Begin
		console.requestConsoleAccess("e003a205042f49fc96349b6da42b5efa", sessionId);
		// 请求控制台访问地址 End
	}
}