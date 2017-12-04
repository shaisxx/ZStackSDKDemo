package com.shtd.zstack.network;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.zstack.sdk.GetNetworkServiceTypesAction;
import org.zstack.sdk.NetworkServiceProviderInventory;
import org.zstack.sdk.QueryNetworkServiceProviderAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 5 网络-网络服务相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class NetworkServiceAction {

	/**
	 * 5.4.1 获取网络服务类型
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	@SuppressWarnings("unchecked")
	public GetNetworkServiceTypesAction.Result getNetworkServiceTypes(String sessionId){
		GetNetworkServiceTypesAction action = new GetNetworkServiceTypesAction();
		action.sessionId = sessionId;
		GetNetworkServiceTypesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getNetworkServiceTypes error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			Map<String, Object> types = res.value.types;
			for (Entry<String, Object> entry: types.entrySet()) {
			    System.out.println(String.format("getNetworkServiceTypes successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	/**
	 * 5.4.2 查询网络服务模块
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryNetworkServiceProviderAction.Result queryNetworkServiceProvider(String sessionId){
		QueryNetworkServiceProviderAction action = new QueryNetworkServiceProviderAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryNetworkServiceProviderAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryNetworkServiceProvider error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				NetworkServiceProviderInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryNetworkServiceProvider successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		String sessionId = ZStackUtils.ZStackLogin();
		
		NetworkServiceAction networkService = new NetworkServiceAction();
		
		// 获取网络服务类型 Begin
		System.out.println("****** getNetworkServiceTypes Start ******");
		GetNetworkServiceTypesAction.Result getResult = networkService.getNetworkServiceTypes(sessionId);
		if(getResult != null && getResult.value.getTypes() != null && getResult.value.getTypes().size() > 0){
			for (int i = 0; i < getResult.value.getTypes().size(); i++) {
				Map<String, Object> map = (Map<String, Object>)getResult.value.getTypes().get(i);
				if(map != null && !map.isEmpty()){
					Set set = map.keySet();
					Iterator<Object> iterator = set.iterator();
					while (iterator.hasNext()) {
						System.out.println("key : " + iterator.next() + " value : " + map.get(iterator.next()));
					}
				}
			}
		}
		System.out.println("****** getNetworkServiceTypes Stop ******");
		// 获取网络服务类型 End
	}
}