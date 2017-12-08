package com.shtd.zstack.network;

import java.util.Arrays;
import java.util.Map;

import org.zstack.sdk.AddDnsToL3NetworkAction;
import org.zstack.sdk.AddIpRangeAction;
import org.zstack.sdk.AddIpRangeByNetworkCidrAction;
import org.zstack.sdk.AttachNetworkServiceToL3NetworkAction;
import org.zstack.sdk.ChangeL3NetworkStateAction;
import org.zstack.sdk.CheckIpAvailabilityAction;
import org.zstack.sdk.CreateL3NetworkAction;
import org.zstack.sdk.DeleteIpRangeAction;
import org.zstack.sdk.DeleteL3NetworkAction;
import org.zstack.sdk.DetachNetworkServiceFromL3NetworkAction;
import org.zstack.sdk.GetIpAddressCapacityAction;
import org.zstack.sdk.GetL3NetworkDhcpIpAddressAction;
import org.zstack.sdk.GetL3NetworkTypesAction;
import org.zstack.sdk.IpRangeInventory;
import org.zstack.sdk.L3NetworkInventory;
import org.zstack.sdk.NetworkServiceL3NetworkRefInventory;
import org.zstack.sdk.QueryIpRangeAction;
import org.zstack.sdk.QueryL3NetworkAction;
import org.zstack.sdk.QueryNetworkServiceL3NetworkRefAction;
import org.zstack.sdk.RemoveDnsFromL3NetworkAction;
import org.zstack.sdk.UpdateIpRangeAction;
import org.zstack.sdk.UpdateL3NetworkAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 5 网络-三层网络相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class L3NetworkAction {
	
	/**
	 * 5.2.1 创建三层网络
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
	 * 5.2.2 删除三层网络
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteL3NetworkAction.Result deleteL3Network(String uuid, String sessionId){
		DeleteL3NetworkAction action = new DeleteL3NetworkAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteL3Network successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.2.3 查询三层网络
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
	
	/**
	 * 5.2.4 更新三层网络
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateL3NetworkAction.Result updateL3Network(String uuid, String name, String sessionId){
		UpdateL3NetworkAction action = new UpdateL3NetworkAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L3NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("updateL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.2.5 获取三层网络类型
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetL3NetworkTypesAction.Result getL3NetworkTypes(String sessionId){
		GetL3NetworkTypesAction action = new GetL3NetworkTypesAction();
		action.sessionId = sessionId;
		GetL3NetworkTypesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getL3NetworkTypes error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.types.size(); i++) {
				String inventory = res.value.types.get(i);
				System.out.println(String.format("getL3NetworkTypes successfully type:%s", inventory));
			}
		}
		return res;
	}
	
	/**
	 * 5.2.6 改变三层网络状态
	 * @param uuid
	 * @param sessionId
	 * @author Josh
	 */
	public ChangeL3NetworkStateAction.Result changeL3NetworkState(String uuid, String sessionId){
		ChangeL3NetworkStateAction action = new ChangeL3NetworkStateAction();
		action.uuid = uuid;
		action.stateEvent = "enable";
		action.sessionId = sessionId;
		ChangeL3NetworkStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeL3NetworkState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L3NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("changeL3NetworkState successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.2.7 挂载网络服务到三层网络
	 * @param l3NetworkUuid
	 * @param networkServices
	 * @param sessionId
	 */
	@SuppressWarnings("rawtypes")
	public AttachNetworkServiceToL3NetworkAction.Result attachNetworkServiceToL3Network(String l3NetworkUuid, Map networkServices, String sessionId){
		AttachNetworkServiceToL3NetworkAction action = new AttachNetworkServiceToL3NetworkAction();
		action.l3NetworkUuid = l3NetworkUuid;
		action.networkServices = networkServices;
		action.sessionId = sessionId;
		AttachNetworkServiceToL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachNetworkServiceToL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L3NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("attachNetworkServiceToL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.2.8 从三层网络卸载网络服务
	 * @param l3NetworkUuid
	 * @param networkServices
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	@SuppressWarnings("rawtypes")
	public DetachNetworkServiceFromL3NetworkAction.Result detachNetworkServiceFromL3Network(String l3NetworkUuid, Map networkServices, String sessionId){
		DetachNetworkServiceFromL3NetworkAction action = new DetachNetworkServiceFromL3NetworkAction();
		action.l3NetworkUuid = l3NetworkUuid;
		action.networkServices = networkServices;
		action.sessionId = sessionId;
		DetachNetworkServiceFromL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachNetworkServiceFromL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("detachNetworkServiceFromL3Network successfully l3NetworkUuid:%s", l3NetworkUuid));
		}
		return res;
	}
	
	/**
	 * 5.2.9 查询网络服务与三层网络引用
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryNetworkServiceL3NetworkRefAction.Result queryNetworkServiceL3NetworkRef(String sessionId){
		QueryNetworkServiceL3NetworkRefAction action = new QueryNetworkServiceL3NetworkRefAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryNetworkServiceL3NetworkRefAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryNetworkServiceL3NetworkRef error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				NetworkServiceL3NetworkRefInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryNetworkServiceL3NetworkRef successfully l3NetworkUuid:%s,networkServiceType:%s,networkServiceProviderUuid:%s", inventory.l3NetworkUuid, inventory.networkServiceType, inventory.networkServiceProviderUuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.2.10 获取网络DHCP服务所用地址
	 * @param l3NetworkUuid
	 * @param sessionId
	 * @author Josh
	 */
	public GetL3NetworkDhcpIpAddressAction.Result getL3NetworkDhcpIpAddress(String l3NetworkUuid, String sessionId){
		GetL3NetworkDhcpIpAddressAction action = new GetL3NetworkDhcpIpAddressAction();
		action.l3NetworkUuid = l3NetworkUuid;
		action.sessionId = sessionId;
		GetL3NetworkDhcpIpAddressAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getL3NetworkDhcpIpAddress error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getL3NetworkDhcpIpAddress successfully ip:%s", res.value.ip));
		}
		return res;
	}
	
	/**
	 * 5.2.11 从三层网络移除DNS
	 * @param l3NetworkUuid
	 * @param dns
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RemoveDnsFromL3NetworkAction.Result removeDnsFromL3Network(String l3NetworkUuid, String dns, String sessionId){
		RemoveDnsFromL3NetworkAction action = new RemoveDnsFromL3NetworkAction();
		action.l3NetworkUuid = l3NetworkUuid;
		action.dns = dns;
		action.sessionId = sessionId;
		RemoveDnsFromL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("removeDnsFromL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L3NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("removeDnsFromL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.2.12 向三层网络添加DNS
	 * @param l3NetworkUuid
	 * @param dns
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddDnsToL3NetworkAction.Result addDnsToL3Network(String l3NetworkUuid, String dns, String sessionId){
		AddDnsToL3NetworkAction action = new AddDnsToL3NetworkAction();
		action.l3NetworkUuid = l3NetworkUuid;
		action.dns = dns;
		action.sessionId = sessionId;
		AddDnsToL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addDnsToL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			L3NetworkInventory inventory = res.value.inventory;
			System.out.println(String.format("addDnsToL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.2.13 获取空闲IP
	 * @param l3NetworkUuid
	 * @param ipRangeUuid
	 * @param start
	 * @param limit
	 * @param sessionId
	 */
	public void getFreeIp(String l3NetworkUuid, String ipRangeUuid, String start, String limit, String sessionId){
		/*GetFreeIpAction action = new GetFreeIpAction();
		action.l3NetworkUuid = "80ffbdcfb3f447abb33400614e08d47a";
		action.ipRangeUuid = "26a2dc70d5854a8daa7343b2a01bb0a6";
		action.start = "0.0.0.0";
		action.limit = 100.0;
		action.sessionUuid = "a5fbab39bc1849988b9e421a4678d9ab";
		GetFreeIpAction.Result res = action.call();*/
	}
	
	/**
	 * 5.2.14 检查IP可用性
	 * @param l3NetworkUuid
	 * @param ip
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CheckIpAvailabilityAction.Result checkIpAvailability(String l3NetworkUuid, String ip, String sessionId){
		CheckIpAvailabilityAction action = new CheckIpAvailabilityAction();
		action.l3NetworkUuid = l3NetworkUuid;
		action.ip = ip;
		action.sessionId = sessionId;
		CheckIpAvailabilityAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("checkIpAvailability error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("checkIpAvailability successfully available:%s", res.value.available));
		}
		return res;
	}
	
	/**
	 * 5.2.15 获取IP网络地址容量
	 * @param ipRangeUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetIpAddressCapacityAction.Result getIpAddressCapacity(String ipRangeUuids, String sessionId){
		GetIpAddressCapacityAction action = new GetIpAddressCapacityAction();
		action.ipRangeUuids = Arrays.asList(ipRangeUuids);
		action.all = false;
		action.sessionId = sessionId;
		GetIpAddressCapacityAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getIpAddressCapacity error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getIpAddressCapacity successfully availableCapacity:%s,totalCapacity:%s", res.value.availableCapacity, res.value.totalCapacity));
		}
		return res;
	}
	
	/**
	 * 5.2.16 添加IP地址范围
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
		action.l3NetworkUuid = l3NetworkUuid;
		action.name = name;
		action.startIp = startIp;
		action.endIp = endIp;
		action.netmask = netmask;
		action.gateway = gateway;
		action.sessionId = sessionId;
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
	 * 5.2.17 删除IP地址范围
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteIpRangeAction.Result deleteIpRange(String uuid, String sessionId){
		DeleteIpRangeAction action = new DeleteIpRangeAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteIpRangeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteIpRange error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteIpRange successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.2.18 查询IP地址范围
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryIpRangeAction.Result queryIpRange(String uuid, String sessionId){
		QueryIpRangeAction action = new QueryIpRangeAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryIpRangeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryIpRange error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				IpRangeInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryIpRange successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 5.2.19 更新IP地址范围
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateIpRangeAction.Result updateIpRange(String uuid, String sessionId){
		UpdateIpRangeAction action = new UpdateIpRangeAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		UpdateIpRangeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateIpRange error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			IpRangeInventory inventory = res.value.inventory;
			System.out.println(String.format("updateIpRange successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.2.20 通过网络CIDR添加IP地址范围
	 * @param name
	 * @param l3NetworkUuid
	 * @param networkCidr
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddIpRangeByNetworkCidrAction.Result addIpRangeByNetworkCidr(String name, String l3NetworkUuid, String networkCidr, String sessionId){
		AddIpRangeByNetworkCidrAction action = new AddIpRangeByNetworkCidrAction();
		action.name = name;
		action.l3NetworkUuid = l3NetworkUuid;
		action.networkCidr = networkCidr;
		action.sessionId = sessionId;
		AddIpRangeByNetworkCidrAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addIpRangeByNetworkCidr error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			IpRangeInventory inventory = res.value.inventory;
			System.out.println(String.format("addIpRangeByNetworkCidr successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {
		
		String sessionId = ZStackUtils.ZStackLogin();
		
		L3NetworkAction action = new L3NetworkAction();
		
		// 创建三层网络 Begin
//		action.createL3Network("本地三层网络-NEW", "d8f2ba9691e441f8a74040be309e3237", sessionId);
		// 创建三层网络End
		
		// 查询三层网络 Begin
		action.queryL3Network(sessionId);
		// 查询三层网络 End
		
	}
}