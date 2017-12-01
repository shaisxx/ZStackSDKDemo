package com.shtd.zstack.vcenter;

import java.util.Arrays;

import org.zstack.sdk.QueryVCenterAction;
import org.zstack.sdk.VCenterInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * vCenter-vCenter相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class VCenterAction {

	/**
	 * 查询vCenter资源
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVCenterAction.Result queryVCenter(String uuid, String sessionId){
		QueryVCenterAction action = new QueryVCenterAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryVCenterAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVCenter error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VCenterInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVCenter successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		VCenterAction vCenter = new VCenterAction();
		
		// 查询vCenter资源 Begin
		vCenter.queryVCenter("ca7c23f51fca4be793340a6134d5c5a0", sessionId);
		// 查询vCenter资源 End
		
	}
}