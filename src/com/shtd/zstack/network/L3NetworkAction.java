package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.AddIpRangeAction;
import org.zstack.sdk.CreateL3NetworkAction;
import org.zstack.sdk.IpRangeInventory;
import org.zstack.sdk.L3NetworkInventory;
import org.zstack.sdk.QueryL3NetworkAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 网络-三层网络相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class L3NetworkAction {
	
	/**
	 * 创建三层网络
	 * @param name
	 * @param l2NetworkUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateL3NetworkAction.Result createL3Network(String name, String l2NetworkUuid, String sessionId){
		CreateL3NetworkAction createL3NetworkAction = new CreateL3NetworkAction();
		createL3NetworkAction.name = name;
		createL3NetworkAction.type = "L3BasicNetwork";
		createL3NetworkAction.l2NetworkUuid = l2NetworkUuid;
		createL3NetworkAction.system = false;
		createL3NetworkAction.sessionId = sessionId;
		CreateL3NetworkAction.Result res = createL3NetworkAction.call();
		if (res.error != null) {
			System.out.println(String.format("createL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L3NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("createL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 添加IP地址范围
	 * @param l3NetworkUuid
	 * @param name
	 * @param startIp
	 * @param endIp
	 * @param netmask
	 * @param gateway
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddIpRangeAction.Result addIpRange(String l3NetworkUuid, String name, String startIp, String endIp, String netmask,
			String gateway, String sessionId){
		
		AddIpRangeAction action = new AddIpRangeAction();
		action.l3NetworkUuid = "471f0bcd100a40bda0a2366106f97a2c";
		action.name = "Test-IP-Range";
		action.startIp = "192.168.100.10";
		action.endIp = "192.168.100.250";
		action.netmask = "255.255.255.0";
		action.gateway = "192.168.100.1";
		action.sessionId = "2a858b6bfa6542d8be18df36fe6d1603";
		AddIpRangeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addIpRange error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			IpRangeInventory inventory = res.value.inventory;
			System.out.println(String.format("addIpRange successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 查询三层网络
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryL3NetworkAction.Result queryL3Network(String sessionId){
		QueryL3NetworkAction action = new QueryL3NetworkAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				L3NetworkInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		String sessionId = ZStackUtils.ZStackLogin();
		
		L3NetworkAction l3NetworkAction = new L3NetworkAction();
		
		// 创建三层网络 Begin
//		l3NetworkAction.createL3Network("本地三层网络-NEW", "d8f2ba9691e441f8a74040be309e3237", sessionId);
		// 创建三层网络End
		
		// 查询三层网络 Begin
		l3NetworkAction.queryL3Network(sessionId);
		// 查询三层网络 End
		
	}
}