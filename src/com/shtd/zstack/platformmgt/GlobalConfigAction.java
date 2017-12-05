package com.shtd.zstack.platformmgt;

import java.util.Arrays;

import org.zstack.sdk.GlobalConfigInventory;
import org.zstack.sdk.QueryGlobalConfigAction;
import org.zstack.sdk.UpdateGlobalConfigAction;

/**
 * 7 平台管理-设置相关接口-全局设置相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class GlobalConfigAction {

	/**
	 * 7.6.1.1 查询全局设置
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryGlobalConfigAction.Result queryGlobalConfig(String sessionId){
		QueryGlobalConfigAction action = new QueryGlobalConfigAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryGlobalConfigAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryGlobalConfig error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				GlobalConfigInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryGlobalConfig successfully category:%s,defaultValue:%s,description:%s,name:%s,value:%s", 
						inventory.category, inventory.defaultValue, inventory.description, inventory.name, inventory.value));
			}
		}
		return res;
	}
	
	/**
	 * 7.6.1.2 更新全局设置
	 * @param category
	 * @param name
	 * @param value
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateGlobalConfigAction.Result updateGlobalConfig(String category, String name, String value, String sessionId){
		UpdateGlobalConfigAction action = new UpdateGlobalConfigAction();
		action.category = category;
		action.name = name;
		action.value = value;
		action.sessionId = sessionId;
		UpdateGlobalConfigAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("requestConsoleAccess error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			GlobalConfigInventory inventory = res.value.inventory;
			System.out.println(String.format("requestConsoleAccess successfully name:%s,category:%s,defaultValue:%s,description:%s,value:%s", 
					inventory.name, inventory.category, inventory.defaultValue, inventory.description, inventory.value));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}