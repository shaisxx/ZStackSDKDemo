package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.BaremetalChassisInventory;
import org.zstack.sdk.BaremetalConsoleProxyInventory;
import org.zstack.sdk.BaremetalHardwareInfoInventory;
import org.zstack.sdk.BaremetalHostCfgInventory;
import org.zstack.sdk.CreateBaremetalChassisAction;
import org.zstack.sdk.CreateBaremetalHostCfgAction;
import org.zstack.sdk.DeleteBaremetalChassisAction;
import org.zstack.sdk.DeleteBaremetalHostCfgAction;
import org.zstack.sdk.PowerOffBaremetalHostAction;
import org.zstack.sdk.PowerOnBaremetalHostAction;
import org.zstack.sdk.PowerResetBaremetalHostAction;
import org.zstack.sdk.PowerStatusBaremetalHostAction;
import org.zstack.sdk.ProvisionBaremetalHostAction;
import org.zstack.sdk.QueryBaremetalChassisAction;
import org.zstack.sdk.QueryBaremetalHardwareInfoAction;
import org.zstack.sdk.QueryBaremetalHostCfgAction;
import org.zstack.sdk.RequestBaremetalConsoleAccessAction;
import org.zstack.sdk.UpdateBaremetalChassisAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 4 硬件设施-裸机管理相关接口-裸机安装相关接口
 * @author Josh
 * @date 2017-12-01
 */
public class BaremetalHostAction {
	
	/**
	 * 4.6.2.1 查询裸机硬件配置
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryBaremetalHardwareInfoAction.Result queryBaremetalHardwareInfo(String uuid, String sessionId){
		QueryBaremetalHardwareInfoAction action = new QueryBaremetalHardwareInfoAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryBaremetalHardwareInfoAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryBaremetalHardwareInfo error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				BaremetalHardwareInfoInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryBaremetalHardwareInfo successfully uuid:%s,content:%s", inventory.uuid, inventory.content));
			}
		}
		return res;
	}
	
	/**
	 * 4.6.2.2 创建裸机配置
	 * @param chassisUuid
	 * @param password
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateBaremetalHostCfgAction.Result createBaremetalHostCfg(String chassisUuid, String password, String sessionId){
		CreateBaremetalHostCfgAction action = new CreateBaremetalHostCfgAction();
		action.chassisUuid = chassisUuid;
		action.password = password;
		action.vnc = true;
		action.unattended = true;
		action.sessionId = sessionId;
		CreateBaremetalHostCfgAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createBaremetalHostCfg error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BaremetalHostCfgInventory inventory = res.value.inventory;
			System.out.println(String.format("createBaremetalHostCfg successfully chassisUuid:%s,uuid:%s", inventory.chassisUuid, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.3 删除裸机配置
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteBaremetalHostCfgAction.Result deleteBaremetalHostCfg(String uuid, String sessionId){
		DeleteBaremetalHostCfgAction action = new DeleteBaremetalHostCfgAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteBaremetalHostCfgAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteBaremetalHostCfg error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteBaremetalHostCfg successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.4 查询裸机配置
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryBaremetalHostCfgAction.Result queryBaremetalHostCfg(String uuid, String sessionId){
		QueryBaremetalHostCfgAction action = new QueryBaremetalHostCfgAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryBaremetalHostCfgAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryBaremetalHostCfg error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				BaremetalHostCfgInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryBaremetalHostCfg successfully uuid:%s,chassisUuid:%s", inventory.uuid, inventory.chassisUuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.6.2.5 创建裸机底盘信息
	 * @param name
	 * @param ipmiAddress
	 * @param ipmiPort
	 * @param ipmiUsername
	 * @param ipmiPassword
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateBaremetalChassisAction.Result createBaremetalChassis(String name, String ipmiAddress, String ipmiPort, String ipmiUsername, String ipmiPassword, String sessionId){
		CreateBaremetalChassisAction action = new CreateBaremetalChassisAction();
		action.name = name;
		action.ipmiAddress = ipmiAddress;
		action.ipmiPort = ipmiPort;
		action.ipmiUsername = ipmiUsername;
		action.ipmiPassword = ipmiPassword;
		action.sessionId = sessionId;
		CreateBaremetalChassisAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createBaremetalChassis error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BaremetalChassisInventory inventory = res.value.inventory;
			System.out.println(String.format("createBaremetalChassis successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.6 删除裸机底盘信息
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteBaremetalChassisAction.Result deleteBaremetalChassis(String uuid, String sessionId){
		DeleteBaremetalChassisAction action = new DeleteBaremetalChassisAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteBaremetalChassisAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteBaremetalChassis error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteBaremetalChassis successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.7 查询裸机底盘信息
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryBaremetalChassisAction.Result queryBaremetalChassis(String uuid, String sessionId){
		QueryBaremetalChassisAction action = new QueryBaremetalChassisAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryBaremetalChassisAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryBaremetalChassis error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				BaremetalChassisInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryBaremetalChassis successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.6.2.8 更新裸机底盘信息
	 * @param uuid
	 * @param name
	 * @param ipmiAddress
	 * @param ipmiPort
	 * @param ipmiUsername
	 * @param ipmiPassword
	 * @param status "Unprovisioned"
	 * @param sessionId
	 * @return
	 */
	public UpdateBaremetalChassisAction.Result updateBaremetalChassis(String uuid, String name, String ipmiAddress, String ipmiPort, 
			String ipmiUsername, String ipmiPassword, String status, String sessionId){
		
		UpdateBaremetalChassisAction action = new UpdateBaremetalChassisAction();
		action.uuid = uuid;
		action.name = name;
		action.ipmiAddress = ipmiAddress;
		action.ipmiPort = ipmiPort;
		action.ipmiUsername = ipmiUsername;
		action.ipmiPassword = ipmiPassword;
		action.status = status;
		action.sessionId = sessionId;
		UpdateBaremetalChassisAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateBaremetalChassis error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BaremetalChassisInventory inventory = res.value.inventory;
			System.out.println(String.format("updateBaremetalChassis successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.9 远程配置裸机PXE启动并远程开机
	 * @param chassisUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ProvisionBaremetalHostAction.Result provisionBaremetalHost(String chassisUuid, String sessionId){
		ProvisionBaremetalHostAction action = new ProvisionBaremetalHostAction();
		action.chassisUuid = chassisUuid;
		action.sessionId = sessionId;
		ProvisionBaremetalHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("provisionBaremetalHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BaremetalChassisInventory inventory = res.value.inventory;
			System.out.println(String.format("provisionBaremetalHost successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.10 获取裸机电源状态
	 * @param chassisUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public PowerStatusBaremetalHostAction.Result powerStatusBaremetalHost(String chassisUuid, String sessionId){
		PowerStatusBaremetalHostAction action = new PowerStatusBaremetalHostAction();
		action.chassisUuid = chassisUuid;
		action.sessionId = sessionId;
		PowerStatusBaremetalHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("powerStatusBaremetalHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("powerStatusBaremetalHost successfully status:%s", res.value.status));
		}
		return res;
	}
	
	/**
	 * 4.6.2.11 远程开启裸机
	 * @param chassisUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public PowerOnBaremetalHostAction.Result powerOnBaremetalHost(String chassisUuid, String sessionId){
		PowerOnBaremetalHostAction action = new PowerOnBaremetalHostAction();
		action.chassisUuid = chassisUuid;
		action.sessionId = sessionId;
		PowerOnBaremetalHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("powerOnBaremetalHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("powerOnBaremetalHost successfully chassisUuid:%s", chassisUuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.12 远程关闭裸机
	 * @param chassisUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public PowerOffBaremetalHostAction.Result powerOffBaremetalHost(String chassisUuid, String sessionId){
		PowerOffBaremetalHostAction action = new PowerOffBaremetalHostAction();
		action.chassisUuid = chassisUuid;
		action.sessionId = sessionId;
		PowerOffBaremetalHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("powerOffBaremetalHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("powerOffBaremetalHost successfully chassisUuid:%s", chassisUuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.13 远程重启裸机
	 * @param chassisUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public PowerResetBaremetalHostAction.Result powerResetBaremetalHost(String chassisUuid, String sessionId){
		PowerResetBaremetalHostAction action = new PowerResetBaremetalHostAction();
		action.chassisUuid = "22b915a3ae36424f959e36f1886e7b75";
		action.sessionId = "8a51e23f331d49c89a5c8f1cd4519fcb";
		PowerResetBaremetalHostAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("powerResetBaremetalHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("powerResetBaremetalHost successfully chassisUuid:%s", chassisUuid));
		}
		return res;
	}
	
	/**
	 * 4.6.2.14 获取裸机控制台地址
	 * @param chassisUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RequestBaremetalConsoleAccessAction.Result requestBaremetalConsoleAccess(String chassisUuid, String sessionId){
		RequestBaremetalConsoleAccessAction action = new RequestBaremetalConsoleAccessAction();
		action.chassisUuid = "87c12cbe97e240acb3d6ede807d31fa4";
		action.sessionId = "9b8afb41136e429e88b150d549a2c012";
		RequestBaremetalConsoleAccessAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("provisionBaremetalHost error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			BaremetalConsoleProxyInventory inventory = res.value.inventory;
			System.out.println(String.format("provisionBaremetalHost successfully chassisUuid:%s,uuid:%s,token:%s", inventory.chassisUuid, inventory.uuid, inventory.token));
		}
		return res;
	}
	
	public static void main(String[] args) {
		
		String sessionId = ZStackUtils.ZStackLogin();
		
		BaremetalHostAction action = new BaremetalHostAction();
		
		// 查询裸机硬件配置 Begin
		action.queryBaremetalHardwareInfo("uuid", sessionId);
		// 查询裸机硬件配置 End
	}
}