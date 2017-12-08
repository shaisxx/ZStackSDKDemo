package com.shtd.zstack.network;

import java.util.Arrays;

import org.zstack.sdk.CreateIPsecConnectionAction;
import org.zstack.sdk.DeleteIPsecConnectionAction;
import org.zstack.sdk.IPsecConnectionInventory;
import org.zstack.sdk.QueryIPSecConnectionAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 5 网络-网络服务相关接口-IPsec相关接口
 * @author Josh
 * @date 2017-12-04
 */
public class IPsecConnectionAction {
	
	/**
	 * 5.4.8.1 创建IPsec连接
	 * @param name
	 * @param l3NetworkUuid
	 * @param peerAddress
	 * @param authMode
	 * @param authKey
	 * @param vipUuid
	 * @param peerCidrs
	 * @param ikeAuthAlgorithm
	 * @param ikeEncryptionAlgorithm
	 * @param ikeDhGroup
	 * @param policyAuthAlgorithm
	 * @param policyEncryptionAlgorithm
	 * @param policyMode
	 * @param transformProtocol
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateIPsecConnectionAction.Result createIPsecConnection(String name, String l3NetworkUuid, String peerAddress, String authMode, 
			String authKey, String vipUuid, String peerCidrs, String ikeAuthAlgorithm, String ikeEncryptionAlgorithm, 
			int ikeDhGroup, String policyAuthAlgorithm, String policyEncryptionAlgorithm, String policyMode, 
			String transformProtocol, String sessionId){
		
		CreateIPsecConnectionAction action = new CreateIPsecConnectionAction();
		action.name = name;
		action.l3NetworkUuid = l3NetworkUuid;
		action.peerAddress = peerAddress;
		action.authMode = authMode;
		action.authKey = authKey;
		action.vipUuid = vipUuid;
		action.peerCidrs = Arrays.asList(peerCidrs);
		action.ikeAuthAlgorithm = ikeAuthAlgorithm;
		action.ikeEncryptionAlgorithm = ikeEncryptionAlgorithm;
		action.ikeDhGroup = ikeDhGroup;
		action.policyAuthAlgorithm = policyAuthAlgorithm;
		action.policyEncryptionAlgorithm = policyEncryptionAlgorithm;
		action.policyMode = policyMode;
		action.transformProtocol = transformProtocol;
		action.sessionId = sessionId;
		CreateIPsecConnectionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createIPsecConnection error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			IPsecConnectionInventory inventory = res.value.inventory;
			System.out.println(String.format("createIPsecConnection successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.8.2 删除IPsec连接
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteIPsecConnectionAction.Result deleteIPsecConnection(String uuid, String sessionId){
		DeleteIPsecConnectionAction action = new DeleteIPsecConnectionAction();
		action.uuid = sessionId;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteIPsecConnectionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteIPsecConnection error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteIPsecConnection successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 5.4.8.3 查询IPsec连接
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryIPSecConnectionAction.Result queryIPSecConnection(String sessionId){
		QueryIPSecConnectionAction action = new QueryIPSecConnectionAction();
		action.conditions = Arrays.asList();
		action.sessionId = sessionId;
		QueryIPSecConnectionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryIPSecConnection error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				IPsecConnectionInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryIPSecConnection successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}

	public static void main(String[] args) {
		
		String sessionId = ZStackUtils.ZStackLogin();
		
		IPsecConnectionAction action = new IPsecConnectionAction();
		
		//  删除IPsec连接 Begin
		action.deleteIPsecConnection("2429832713f74b339d3abf01e126bf23", sessionId);
		//  删除IPsec连接 End
		
	}
}