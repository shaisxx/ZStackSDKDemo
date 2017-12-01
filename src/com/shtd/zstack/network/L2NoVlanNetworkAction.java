package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.CreateL2NoVlanNetworkAction;
import org.zstack.sdk.L2NetworkInventory;
import org.zstack.sdk.QueryL2NetworkAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 网络-二层网络相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class L2NoVlanNetworkAction {

	/**
	 * 创建普通二层网络
	 * @param name
	 * @param description
	 * @param zoneUuid
	 * @param physicalInterface
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateL2NoVlanNetworkAction.Result createL2NoVlanNetwork(String name, String description, 
			String zoneUuid, String physicalInterface, String sessionId){
		
		CreateL2NoVlanNetworkAction action = new CreateL2NoVlanNetworkAction();
		action.name = name;
		action.description = description;
		action.zoneUuid = zoneUuid;
		action.physicalInterface = physicalInterface;
		action.sessionId = sessionId;
		CreateL2NoVlanNetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createL2NoVlanNetwork error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L2NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("createL2NoVlanNetwork successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 查询二层网络
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryL2NetworkAction.Result queryL2Network(String sessionId){
		QueryL2NetworkAction action = new QueryL2NetworkAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryL2NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryL2Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				L2NetworkInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryL2Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		L2NoVlanNetworkAction l2NoVlanNetwork = new L2NoVlanNetworkAction();
		
		// 创建二层网络 Begin
		l2NoVlanNetwork.createL2NoVlanNetwork("本地二层网络-NEW", "本地二层网络-NEW DESC", 
				"2429832713f74b339d3abf01e126bf23", "eth0", sessionId);
		// 创建二层网络End
		
		// 查询区域 Begin
		l2NoVlanNetwork.queryL2Network(sessionId);
		// 查询区域 End
	}
}