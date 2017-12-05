package com.shtd.zstack.sysglobal;

import java.util.Arrays;
import java.util.Map.Entry;

import org.zstack.sdk.GetLicenseCapabilitiesAction;
import org.zstack.sdk.GetLicenseInfoAction;
import org.zstack.sdk.LicenseInventory;
import org.zstack.sdk.ReloadLicenseAction;

/**
 * 8 系统全局相关-许可证相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class LicenseAction {
	
	/**
	 * 8.7.1 获取许可证信息
	 * @return
	 * @author Josh
	 */
	public GetLicenseInfoAction.Result getLicenseInfo(){
		GetLicenseInfoAction action = new GetLicenseInfoAction();
		GetLicenseInfoAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getLicenseInfo error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			LicenseInventory inventory = res.value.inventory;
			System.out.println(String.format("getLicenseInfo successfully availableHostNum:%s,availableCpuNum:%s,cpuNum:%s,expired:%s,expiredDate:%s,hostNum:%s,user:%s", 
					inventory.availableHostNum, inventory.availableCpuNum, inventory.cpuNum, inventory.expired, inventory.expiredDate, inventory.hostNum, inventory.user));
		}
		return res;
	}
	
	/**
	 * 8.7.2 获取许可证容量
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetLicenseCapabilitiesAction.Result getLicenseCapabilities(String sessionId){
		GetLicenseCapabilitiesAction action = new GetLicenseCapabilitiesAction();
		action.sessionId = sessionId;
		GetLicenseCapabilitiesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getLicenseCapabilities error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			java.util.Map<String, String> map = res.value.capabilities; 
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.println(String.format("getLicenseCapabilities successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	/**
	 * 8.7.3 重新加载许可证
	 * @param managementNodeUuids
	 * @param sessionId
	 * @author Josh
	 */
	public ReloadLicenseAction.Result reloadLicense(String managementNodeUuids, String sessionId){
		ReloadLicenseAction action = new ReloadLicenseAction();
		action.managementNodeUuids = Arrays.asList(managementNodeUuids);
		action.sessionId = sessionId;
		ReloadLicenseAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reloadLicense error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			LicenseInventory inventory = res.value.inventory;
			System.out.println(String.format("reloadLicense successfully availableHostNum:%s,availableCpuNum:%s,cpuNum:%s,expired:%s,expiredDate:%s,hostNum:%s,user:%s", 
					inventory.availableHostNum, inventory.availableCpuNum, inventory.cpuNum, inventory.expired, inventory.expiredDate, inventory.hostNum, inventory.user));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}