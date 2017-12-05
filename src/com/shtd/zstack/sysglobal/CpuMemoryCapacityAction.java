package com.shtd.zstack.sysglobal;

import java.util.Arrays;

import org.zstack.sdk.GetCpuMemoryCapacityAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 8 系统全局相关-查询可用资源相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class CpuMemoryCapacityAction {
	
	/**
	 * 8.5.1 获取cpu和内存容量
	 * @param zoneUuids
	 * @param clusterUuids
	 * @param hostUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetCpuMemoryCapacityAction.Result getCpuMemoryCapacity(String zoneUuids, String clusterUuids, String hostUuids, String sessionId){
		
		GetCpuMemoryCapacityAction action = new GetCpuMemoryCapacityAction();
		action.zoneUuids = Arrays.asList(zoneUuids);
		action.clusterUuids = Arrays.asList(clusterUuids);
		action.hostUuids = Arrays.asList(hostUuids);
		action.all = true;
		action.sessionId = sessionId;
		GetCpuMemoryCapacityAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getCpuMemoryCapacity error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("getCpuMemoryCapacity successfully availableCpu:%s,availableMemory:%s,totalCpu:%s,totalMemory:%s", 
					res.value.availableCpu, res.value.availableMemory, res.value.totalCpu, res.value.totalMemory));
		}
		return res;
	}

	public static void main(String[] args) {
		
		String sessionId = ZStackUtils.ZStackLogin();
		
		CpuMemoryCapacityAction cpuMemoryCapacity = new CpuMemoryCapacityAction();
		
		//  获取cpu和内存容量 Begin
		cpuMemoryCapacity.getCpuMemoryCapacity("e003a205042f49fc96349b6da42b5efa", "e003a205042f49fc96349b6da42b5efa", "e003a205042f49fc96349b6da42b5efa", sessionId);
		//  获取cpu和内存容量  End
		
	}
}