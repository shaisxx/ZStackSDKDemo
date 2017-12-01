package com.shtd.zstack.network;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.zstack.sdk.GetNetworkServiceTypesAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 网络-网络服务相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class NetworkServiceAction {

	/**
	 * 获取网络服务类型
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetNetworkServiceTypesAction.Result getNetworkServiceTypes(String sessionId){
		GetNetworkServiceTypesAction action = new GetNetworkServiceTypesAction();
		action.sessionId = sessionId;
		GetNetworkServiceTypesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getNetworkServiceTypes error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
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