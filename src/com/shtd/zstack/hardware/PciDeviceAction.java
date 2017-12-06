package com.shtd.zstack.hardware;

import java.util.Arrays;

import org.zstack.sdk.AttachPciDeviceToVmAction;
import org.zstack.sdk.CreatePciDeviceOfferingAction;
import org.zstack.sdk.DeletePciDeviceAction;
import org.zstack.sdk.DeletePciDeviceOfferingAction;
import org.zstack.sdk.DetachPciDeviceFromVmAction;
import org.zstack.sdk.GetHostIommuStateAction;
import org.zstack.sdk.GetHostIommuStatusAction;
import org.zstack.sdk.PciDeviceInventory;
import org.zstack.sdk.PciDeviceOfferingInventory;
import org.zstack.sdk.PciDevicePciDeviceOfferingRefInventory;
import org.zstack.sdk.QueryPciDeviceAction;
import org.zstack.sdk.QueryPciDeviceOfferingAction;
import org.zstack.sdk.QueryPciDevicePciDeviceOfferingAction;
import org.zstack.sdk.UpdateHostIommuStateAction;

/**
 * 4 硬件设施-物理机相关接口-PCI设备相关接口
 * @author Josh
 * @date 2017-12-06
 */
public class PciDeviceAction {
	
	/**
	 * 4.3.10.1 查询PCI设备
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryPciDeviceAction.Result queryPciDevice(String sessionId){
		QueryPciDeviceAction action = new QueryPciDeviceAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryPciDeviceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryPciDevice error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				PciDeviceInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryPciDevice successfully deviceId:%s,uuid:%s", inventory.deviceId, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.3.10.2 删除PCI设备
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeletePciDeviceAction.Result deletePciDevice(String uuid, String sessionId){
		DeletePciDeviceAction action = new DeletePciDeviceAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeletePciDeviceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deletePciDevice error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deletePciDevice successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.10.3 绑定PCI设备到云主机
	 * @param pciDeviceUuid
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachPciDeviceToVmAction.Result attachPciDeviceToVm(String pciDeviceUuid, String vmInstanceUuid, String sessionId){
		AttachPciDeviceToVmAction action = new AttachPciDeviceToVmAction();
		action.pciDeviceUuid = pciDeviceUuid;
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		AttachPciDeviceToVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachPciDeviceToVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PciDeviceInventory inventory = res.value.inventory;
			System.out.println(String.format("attachPciDeviceToVm successfully deviceId:%s,uuid:%s", inventory.deviceId, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.10.4 卸载PCI设备
	 * @param pciDeviceUuid
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachPciDeviceFromVmAction.Result detachPciDeviceFromVm(String pciDeviceUuid, String vmInstanceUuid, String sessionId){
		DetachPciDeviceFromVmAction action = new DetachPciDeviceFromVmAction();
		action.pciDeviceUuid = pciDeviceUuid;
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		DetachPciDeviceFromVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachPciDeviceFromVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PciDeviceInventory inventory = res.value.inventory;
			System.out.println(String.format("detachPciDeviceFromVm successfully deviceId:%s,uuid:%s", inventory.deviceId, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.10.5 创建PCI设备规格
	 * @param name
	 * @param vendorId
	 * @param deviceId
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreatePciDeviceOfferingAction.Result createPciDeviceOffering(String name, String vendorId, String deviceId, String sessionId){
		CreatePciDeviceOfferingAction action = new CreatePciDeviceOfferingAction();
		action.name = name;
		action.vendorId = vendorId;
		action.deviceId = deviceId;
		action.sessionId = sessionId;
		CreatePciDeviceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createPciDeviceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			PciDeviceOfferingInventory inventory = res.value.inventory;
			System.out.println(String.format("createPciDeviceOffering successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.10.6 删除PCI设备规格
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeletePciDeviceOfferingAction.Result deletePciDeviceOffering(String uuid, String sessionId){
		DeletePciDeviceOfferingAction action = new DeletePciDeviceOfferingAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DeletePciDeviceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deletePciDeviceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deletePciDeviceOffering successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.10.7 查询PCI设备规格
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryPciDeviceOfferingAction.Result queryPciDeviceOffering(String sessionId){
		QueryPciDeviceOfferingAction action = new QueryPciDeviceOfferingAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryPciDeviceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryPciDeviceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				PciDeviceOfferingInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryPciDeviceOffering successfully deviceId:%s,uuid:%s", inventory.deviceId, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.3.10.8 查询PCI设备规格匹配
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryPciDevicePciDeviceOfferingAction.Result queryPciDevicePciDeviceOffering(String sessionId){
		QueryPciDevicePciDeviceOfferingAction action = new QueryPciDevicePciDeviceOfferingAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryPciDevicePciDeviceOfferingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryPciDevicePciDeviceOffering error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				PciDevicePciDeviceOfferingRefInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryPciDevicePciDeviceOffering successfully pciDeviceOfferingUuid:%s,pciDeviceUuid:%s", inventory.pciDeviceOfferingUuid, inventory.pciDeviceUuid));
			}
		}
		return res;
	}
	
	/**
	 * 4.3.10.9 获取物理机lommu启用状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetHostIommuStatusAction.Result getHostIommuStatus(String uuid, String sessionId){
		GetHostIommuStatusAction action = new GetHostIommuStatusAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetHostIommuStatusAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getHostIommuStatus error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getHostIommuStatus successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.10.10 更新物理机Iommu启用状态
	 * @param uuid
	 * @param state
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateHostIommuStateAction.Result updateHostIommuState(String uuid, String state, String sessionId){
		UpdateHostIommuStateAction action = new UpdateHostIommuStateAction();
		action.uuid = uuid;
		action.state = "Enabled";
		action.sessionId = sessionId;
		UpdateHostIommuStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateHostIommuState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("updateHostIommuState successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 4.3.10.11 获取物理机lommu就绪状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetHostIommuStateAction.Result getHostIommuState(String uuid, String sessionId){
		GetHostIommuStateAction action = new GetHostIommuStateAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetHostIommuStateAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getHostIommuState error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getHostIommuState successfully uuid:%s", uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}