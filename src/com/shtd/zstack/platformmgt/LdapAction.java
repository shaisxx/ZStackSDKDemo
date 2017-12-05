package com.shtd.zstack.platformmgt;

import java.util.Arrays;

import org.zstack.sdk.AccountInventory;
import org.zstack.sdk.AddLdapServerAction;
import org.zstack.sdk.CleanInvalidLdapBindingAction;
import org.zstack.sdk.CreateLdapBindingAction;
import org.zstack.sdk.DeleteLdapBindingAction;
import org.zstack.sdk.DeleteLdapServerAction;
import org.zstack.sdk.LdapAccountRefInventory;
import org.zstack.sdk.LdapServerInventory;
import org.zstack.sdk.LogInByLdapAction;
import org.zstack.sdk.QueryLdapBindingAction;
import org.zstack.sdk.QueryLdapServerAction;
import org.zstack.sdk.SessionInventory;
import org.zstack.sdk.UpdateLdapServerAction;

/**
 * 7 平台管理-设置相关接口-LDAP相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class LdapAction {
	
	/**
	 * 7.6.2.1 创建LDAP绑定
	 * @param ldapUid
	 * @param accountUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateLdapBindingAction.Result createLdapBinding(String ldapUid, String accountUuid, String sessionId){
		CreateLdapBindingAction action = new CreateLdapBindingAction();
		action.ldapUid = ldapUid;
		action.accountUuid = accountUuid;
		action.sessionId = sessionId;
		CreateLdapBindingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createLdapBinding error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			LdapAccountRefInventory inventory = res.value.inventory;
			System.out.println(String.format("createLdapBinding successfully ldapServerUuid:%s,uuid:%s", 
					inventory.ldapServerUuid, inventory.uuid));
		}
		return res;
	}

	/**
	 * 7.6.2.2 删除LDAP绑定
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteLdapBindingAction.Result deleteLdapBinding(String uuid, String sessionId){
		DeleteLdapBindingAction action = new DeleteLdapBindingAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DeleteLdapBindingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteLdapBinding error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteLdapBinding successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.6.2.3 查询LDAP绑定
	 * @param accountUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryLdapBindingAction.Result queryLdapBinding(String accountUuid, String sessionId){
		QueryLdapBindingAction action = new QueryLdapBindingAction();
		action.conditions = Arrays.asList("accountUuid=" + accountUuid);
		action.sessionId = sessionId;
		QueryLdapBindingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryLdapBinding error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				LdapAccountRefInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryLdapBinding successfully ldapServerUuid:%s,ldapUid:%s,uuid:%s", 
						inventory.ldapServerUuid, inventory.ldapUid, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.6.2.4 清理无效的LDAP绑定
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CleanInvalidLdapBindingAction.Result cleanInvalidLdapBinding(String sessionId){
		CleanInvalidLdapBindingAction action = new CleanInvalidLdapBindingAction();
		action.sessionId = sessionId;
		CleanInvalidLdapBindingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("cleanInvalidLdapBinding error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				AccountInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("cleanInvalidLdapBinding successfully name:%s,description:%s,type:%s,uuid:%s", 
						inventory.name, inventory.description, inventory.type, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.6.2.5 添加LDAP服务器
	 * @param name
	 * @param description
	 * @param url
	 * @param base
	 * @param username
	 * @param password
	 * @param encryption
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddLdapServerAction.Result addLdapServer(String name, String description, String url, 
			String base, String username, String password, String encryption, String sessionId){
		
		AddLdapServerAction action = new AddLdapServerAction();
		action.name = name;
		action.description = description;
		action.url = url;
		action.base = base;
		action.username = username;
		action.password = password;
		action.encryption = encryption;
		action.sessionId = sessionId;
		AddLdapServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addLdapServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			LdapServerInventory inventory = res.value.inventory;
			System.out.println(String.format("addLdapServer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.6.2.6 删除LDAP服务器
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteLdapServerAction.Result deleteLdapServer(String uuid, String sessionId){
		DeleteLdapServerAction action = new DeleteLdapServerAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteLdapServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteLdapServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteLdapServer successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.6.2.7 查询LDAP服务器
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryLdapServerAction.Result queryLdapServer(String name, String sessionId){
		QueryLdapServerAction action = new QueryLdapServerAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryLdapServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryLdapServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				LdapServerInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryLdapServer successfully name:%s,uuid:%s", 
						inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.6.2.8 更新LDAP服务器
	 * @param ldapServerUuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateLdapServerAction.Result updateLdapServer(String ldapServerUuid, String name, String sessionId){
		UpdateLdapServerAction action = new UpdateLdapServerAction();
		action.ldapServerUuid = ldapServerUuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateLdapServerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateLdapServer error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			LdapServerInventory inventory = res.value.inventory;
			System.out.println(String.format("updateLdapServer successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.6.2.9 使用LDAP身份登录
	 * @param uid
	 * @param password
	 * @return
	 * @author Josh
	 */
	public LogInByLdapAction.Result logInByLdap(String uid, String password){
		LogInByLdapAction action = new LogInByLdapAction();
		action.uid = uid;
		action.password = password;
		LogInByLdapAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("logInByLdap error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			SessionInventory inventory = res.value.inventory;
			System.out.println(String.format("logInByLdap successfully userUuid:%s,uuid:%s", inventory.userUuid, inventory.uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}