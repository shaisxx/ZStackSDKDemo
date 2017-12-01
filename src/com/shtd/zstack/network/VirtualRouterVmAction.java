package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.ApplianceVmInventory;
import org.zstack.sdk.QueryVirtualRouterVmAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 网络-云路由相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class VirtualRouterVmAction {

	/**
	 * 查询云路由
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVirtualRouterVmAction.Result queryVirtualRouterVm(String name, String sessionId){
		QueryVirtualRouterVmAction action = new QueryVirtualRouterVmAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryVirtualRouterVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVirtualRouterVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		}else{
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ApplianceVmInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVirtualRouterVm successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		VirtualRouterVmAction virtualRouterVmAction = new VirtualRouterVmAction();
		
		// 查询云路由 Begin
		virtualRouterVmAction.queryVirtualRouterVm("", sessionId);
		// 查询云路由 End
	}
}