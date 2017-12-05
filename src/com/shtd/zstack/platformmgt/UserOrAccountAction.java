package com.shtd.zstack.platformmgt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zstack.sdk.AccountInventory;
import org.zstack.sdk.AccountResourceRefInventory;
import org.zstack.sdk.AddUserToGroupAction;
import org.zstack.sdk.AttachPoliciesToUserAction;
import org.zstack.sdk.AttachPolicyToUserAction;
import org.zstack.sdk.AttachPolicyToUserGroupAction;
import org.zstack.sdk.ChangeResourceOwnerAction;
import org.zstack.sdk.CheckApiPermissionAction;
import org.zstack.sdk.CreateAccountAction;
import org.zstack.sdk.CreatePolicyAction;
import org.zstack.sdk.CreateUserAction;
import org.zstack.sdk.CreateUserGroupAction;
import org.zstack.sdk.DeleteAccountAction;
import org.zstack.sdk.DeletePolicyAction;
import org.zstack.sdk.DeleteUserAction;
import org.zstack.sdk.DeleteUserGroupAction;
import org.zstack.sdk.DetachPoliciesFromUserAction;
import org.zstack.sdk.DetachPolicyFromUserAction;
import org.zstack.sdk.DetachPolicyFromUserGroupAction;
import org.zstack.sdk.GetAccountQuotaUsageAction;
import org.zstack.sdk.GetResourceNamesAction;
import org.zstack.sdk.LogInByAccountAction;
import org.zstack.sdk.LogInByUserAction;
import org.zstack.sdk.LogOutAction;
import org.zstack.sdk.PolicyInventory;
import org.zstack.sdk.QueryAccountAction;
import org.zstack.sdk.QueryAccountResourceRefAction;
import org.zstack.sdk.QueryPolicyAction;
import org.zstack.sdk.QueryQuotaAction;
import org.zstack.sdk.QuerySharedResourceAction;
import org.zstack.sdk.QueryUserAction;
import org.zstack.sdk.QueryUserGroupAction;
import org.zstack.sdk.QuotaInventory;
import org.zstack.sdk.QuotaUsage;
import org.zstack.sdk.RemoveUserFromGroupAction;
import org.zstack.sdk.ResourceInventory;
import org.zstack.sdk.RevokeResourceSharingAction;
import org.zstack.sdk.SessionInventory;
import org.zstack.sdk.ShareResourceAction;
import org.zstack.sdk.SharedResourceInventory;
import org.zstack.sdk.UpdateAccountAction;
import org.zstack.sdk.UpdateQuotaAction;
import org.zstack.sdk.UpdateUserAction;
import org.zstack.sdk.UpdateUserGroupAction;
import org.zstack.sdk.UserGroupInventory;
import org.zstack.sdk.UserInventory;
import org.zstack.sdk.ValidateSessionAction;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 7 平台管理-用户管理相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class UserOrAccountAction {

	/**
	 * 7.5.1 创建账户
	 * @param name
	 * @param password
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateAccountAction.Result createAccount(String name, String password, String sessionId){
		CreateAccountAction action = new CreateAccountAction();
		action.name = name;
		action.password = password;
		action.sessionId = sessionId;
		CreateAccountAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createAccount error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			AccountInventory inventory = res.value.inventory;
			System.out.println(String.format("createAccount successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.2 删除账户
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @date 2017-11-29
	 */
	public DeleteAccountAction.Result deleteAccount(String uuid, String sessionId){
		DeleteAccountAction action = new DeleteAccountAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteAccountAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteAccount error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteAccount successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.3 查询账户
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryAccountAction.Result queryAccount(String name, String sessionId){
		QueryAccountAction action = new QueryAccountAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryAccountAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryAccount error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				AccountInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryAccount successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.4 更新账户
	 * @param uuid
	 * @param newname
	 * @param newpassword
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateAccountAction.Result updateAccount(String uuid, String newname, String newpassword, String sessionId){
		UpdateAccountAction action = new UpdateAccountAction();
		action.uuid = uuid;
		action.name = newname;
		action.password = newpassword;
		action.sessionId = sessionId;
		UpdateAccountAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateAccount error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			AccountInventory inventory = res.value.inventory;
			System.out.println(String.format("updateAccount successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.5 使用账户身份登录
	 * @param accountName
	 * @param password
	 * @return
	 * @author Josh
	 */
	public LogInByAccountAction.Result logInByAccount(String accountName, String password){
		LogInByAccountAction action = new LogInByAccountAction();
		action.accountName = accountName;
		action.password = password;
		LogInByAccountAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("logInByAccount error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			SessionInventory inventory = res.value.inventory;
			System.out.println(String.format("logInByAccount successfully userUuid:%s,uuid:%s", inventory.userUuid, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.6 获取账户配额使用情况
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetAccountQuotaUsageAction.Result getAccountQuotaUsage(String uuid, String sessionId){
		GetAccountQuotaUsageAction action = new GetAccountQuotaUsageAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetAccountQuotaUsageAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getAccountQuotaUsage error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<QuotaUsage> usages = res.value.usages;
			if(usages != null && usages.size() > 0){
				for (QuotaUsage usage: usages) {
					System.out.println(String.format("getAccountQuotaUsage successfully name:%s,total:%s,used:%s", usage.name, usage.total, usage.used));
				}
			}
		}
		return res;
	}
	
	/**
	 * 7.5.7 查询账户资源引用
	 * @param acountUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryAccountResourceRefAction.Result queryAccountResourceRef(String acountUuid, String sessionId){
		QueryAccountResourceRefAction action = new QueryAccountResourceRefAction();
		action.conditions = Arrays.asList("acountUuid=" + acountUuid);
		action.sessionId = sessionId;
		QueryAccountResourceRefAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryAccountResourceRef error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				AccountResourceRefInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryAccountResourceRef successfully resourceUuid:%s,resourceType:%s,accountUuid:%s", 
						inventory.resourceUuid, inventory.resourceType, inventory.accountUuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.8 共享资源给账户
	 * @param resourceUuids
	 * @param accountUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ShareResourceAction.Result shareResource(String resourceUuids, String accountUuids, String sessionId){
		ShareResourceAction action = new ShareResourceAction();
		action.resourceUuids = Arrays.asList(resourceUuids);
		action.accountUuids = Arrays.asList(accountUuids);
		action.toPublic = false;
		action.sessionId = sessionId;
		ShareResourceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("shareResource error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("shareResource successfully resourceUuids:%s,accountUuids:%s", resourceUuids, accountUuids));
		}
		return res;
	}
	
	/**
	 * 7.5.9 创建用户组
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateUserGroupAction.Result createUserGroup(String name, String sessionId){
		CreateUserGroupAction action = new CreateUserGroupAction();
		action.name = name;
		action.sessionId = sessionId;
		CreateUserGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createUserGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			UserGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("createUserGroup successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.10 删除用户组
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteUserGroupAction.Result deleteUserGroup(String uuid, String sessionId){
		DeleteUserGroupAction action = new DeleteUserGroupAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteUserGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteUserGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteUserGroup successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.11 查询用户组
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryUserGroupAction.Result queryUserGroup(String name, String sessionId){
		QueryUserGroupAction action = new QueryUserGroupAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryUserGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryUserGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				UserGroupInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryUserGroup successfully name:%s,uuid:%s,description:%s", 
						inventory.name, inventory.uuid, inventory.description));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.12 更新用户组
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateUserGroupAction.Result updateUserGroup(String uuid, String name, String sessionId){
		UpdateUserGroupAction action = new UpdateUserGroupAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateUserGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateUserGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			UserGroupInventory inventory = res.value.inventory;
			System.out.println(String.format("updateUserGroup successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.13 添加到用户组
	 * @param userUuid
	 * @param groupUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AddUserToGroupAction.Result addUserToGroup(String userUuid, String groupUuid, String sessionId){
		AddUserToGroupAction action = new AddUserToGroupAction();
		action.userUuid = userUuid;
		action.groupUuid = groupUuid;
		action.sessionId = sessionId;
		AddUserToGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("addUserToGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("addUserToGroup successfully userUuid:%s,groupUuid:%s", userUuid, groupUuid));
		}
		return res;
	}
	
	/**
	 * 7.5.14 绑定策略到用户组
	 * @param policyUuid
	 * @param groupUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachPolicyToUserGroupAction.Result attachPolicyToUserGroup(String policyUuid, String groupUuid, String sessionId){
		AttachPolicyToUserGroupAction action = new AttachPolicyToUserGroupAction();
		action.policyUuid = policyUuid;
		action.groupUuid = groupUuid;
		action.sessionId = sessionId;
		AttachPolicyToUserGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachPolicyToUserGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("attachPolicyToUserGroup successfully policyUuid:%s,groupUuid:%s", policyUuid, groupUuid));
		}
		return res;
	}
	
	/**
	 * 7.5.15 将策略从用户组解绑
	 * @param policyUuid
	 * @param groupUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachPolicyFromUserGroupAction.Result detachPolicyFromUserGroup(String policyUuid, String groupUuid, String sessionId){
		DetachPolicyFromUserGroupAction action = new DetachPolicyFromUserGroupAction();
		action.policyUuid = policyUuid;
		action.groupUuid = groupUuid;
		action.sessionId = sessionId;
		DetachPolicyFromUserGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachPolicyFromUserGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("detachPolicyFromUserGroup successfully policyUuid:%s,groupUuid:%s", policyUuid, groupUuid));
		}
		return res;
	}
	
	/**
	 * 7.5.16 从用户组中移除用户
	 * @param userUuid
	 * @param groupUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RemoveUserFromGroupAction.Result removeUserFromGroup(String userUuid, String groupUuid, String sessionId){
		RemoveUserFromGroupAction action = new RemoveUserFromGroupAction();
		action.userUuid = userUuid;
		action.groupUuid = groupUuid;
		action.sessionId = sessionId;
		RemoveUserFromGroupAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("removeUserFromGroup error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("removeUserFromGroup successfully userUuid:%s,groupUuid:%s", userUuid, groupUuid));
		}
		return res;
	}
	
	/**
	 * 7.5.17 创建用户
	 * @param name
	 * @param password
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateUserAction.Result createUser(String name, String password, String sessionId){
		CreateUserAction action = new CreateUserAction();
		action.name = name;
		action.password = password;
		action.sessionId = sessionId;
		CreateUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createUser error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			UserInventory inventory = res.value.inventory;
			System.out.println(String.format("createUser successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.18 删除用户
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteUserAction.Result deleteUser(String uuid, String sessionId){
		DeleteUserAction action = new DeleteUserAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteUser error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteUser successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.19 查询用户
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryUserAction.Result queryUser(String name, String sessionId){
		QueryUserAction action = new QueryUserAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryConsoleProxyAgent error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				UserInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryConsoleProxyAgent successfully name:%s,uuid:%s,description:%s", 
						inventory.name, inventory.uuid, inventory.description));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.20 更新用户
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateUserAction.Result updateUser(String uuid, String name, String sessionId){
		UpdateUserAction action = new UpdateUserAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateUser error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			UserInventory inventory = res.value.inventory;
			System.out.println(String.format("updateUser successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.21 使用用户身份登录
	 * @param accountName
	 * @param userName
	 * @param password
	 * @return
	 * @author Josh
	 */
	public LogInByUserAction.Result logInByUser(String accountName, String userName, String password){
		LogInByUserAction action = new LogInByUserAction();
		action.accountName = accountName;
		action.userName = userName;
		action.password = password;
		LogInByUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("logInByUser error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			SessionInventory inventory = res.value.inventory;
			System.out.println(String.format("logInByUser successfully accountUuid:%s,uuid:%s,userUuid:%s", inventory.accountUuid, inventory.uuid, inventory.userUuid));
		}
		return res;
	}
	
	/**
	 * 7.5.22 绑定一条策略到用户
	 * @param userUuid
	 * @param policyUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachPolicyToUserAction.Result attachPolicyToUser(String userUuid, String policyUuid, String sessionId){
		AttachPolicyToUserAction action = new AttachPolicyToUserAction();
		action.userUuid = userUuid;
		action.policyUuid = policyUuid;
		action.sessionId = sessionId;
		AttachPolicyToUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachPolicyToUser error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("attachPolicyToUser successfully userUuid:%s,policyUuid:%s", userUuid, policyUuid));
		}
		return res;
	}
	
	/**
	 * 7.5.23 将一条策略从用户解绑
	 * @param policyUuid
	 * @param groupUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachPolicyFromUserAction.Result detachPolicyFromUser(String policyUuid, String userUuid, String sessionId){
		DetachPolicyFromUserAction action = new DetachPolicyFromUserAction();
		action.policyUuid = policyUuid;
		action.userUuid = userUuid;
		action.sessionId = sessionId;
		DetachPolicyFromUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachPolicyFromUser error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("detachPolicyFromUser successfully policyUuid:%s,userUuid:%s", policyUuid, userUuid));
		}
		return res;
	}
	
	/**
	 * 7.5.24 绑定多条策略到用户
	 * @param userUuid
	 * @param policyUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachPoliciesToUserAction.Result attachPoliciesToUser(String userUuid, String policyUuids, String sessionId){
		AttachPoliciesToUserAction action = new AttachPoliciesToUserAction();
		action.userUuid = userUuid;
		action.policyUuids = Arrays.asList(policyUuids);
		action.sessionId = sessionId;
		AttachPoliciesToUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachPoliciesToUser error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("attachPoliciesToUser successfully userUuid:%s,policyUuids:%s", userUuid, policyUuids));
		}
		return res;
	}
	
	/**
	 * 7.5.25 将多条策略从用户解绑
	 * @param policyUuids
	 * @param userUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachPoliciesFromUserAction.Result detachPoliciesFromUser(String policyUuids, String userUuid, String sessionId){
		DetachPoliciesFromUserAction action = new DetachPoliciesFromUserAction();
		action.policyUuids = Arrays.asList(policyUuids);
		action.userUuid = userUuid;
		action.sessionId = sessionId;
		DetachPoliciesFromUserAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachPoliciesFromUser error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("detachPoliciesFromUser successfully policyUuids:%s,userUuid:%s", policyUuids, userUuid));
		}
		return res;
	}
	
	/**
	 * 7.5.26 创建策略
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreatePolicyAction.Result createPolicy(String name, String sessionId){
		CreatePolicyAction action = new CreatePolicyAction();
		action.name = name;
//		action.statements = Arrays.asList([name:user-reset-password-e3d9eaa96d194f3e8c5c1f68126433f8,effect:Allow, actions:[identity:APIUpdateUserMsg]]);
		action.sessionId = sessionId;
		CreatePolicyAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createPolicy error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			PolicyInventory inventory = res.value.inventory;
			System.out.println(String.format("createPolicy successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.27 删除策略
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeletePolicyAction.Result deletePolicy(String uuid, String sessionId){
		DeletePolicyAction action = new DeletePolicyAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeletePolicyAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deletePolicy error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deletePolicy successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.28 查询策略
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryPolicyAction.Result queryPolicy(String name, String sessionId){
		QueryPolicyAction action = new QueryPolicyAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryPolicyAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryPolicy error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				PolicyInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryPolicy successfully name:%s,uuid:%s,accountUuid:%s", 
						inventory.name, inventory.uuid, inventory.accountUuid));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.29 查询配额
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryQuotaAction.Result queryQuota(String name, String sessionId){
		QueryQuotaAction action = new QueryQuotaAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryQuotaAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryQuota error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				QuotaInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryQuota successfully name:%s,uuid:%s,identityUuid:%s,identityType:%s", 
						inventory.name, inventory.uuid, inventory.identityUuid, inventory.identityType));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.30 更新配额
	 * @param identityUuid
	 * @param name
	 * @param value
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateQuotaAction.Result updateQuota(String identityUuid, String name, Long value, String sessionId){
		UpdateQuotaAction action = new UpdateQuotaAction();
		action.identityUuid = identityUuid;
		action.name = name;
		action.value = value;
		action.sessionId = sessionId;
		UpdateQuotaAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateQuota error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			QuotaInventory inventory = res.value.inventory;
			System.out.println(String.format("updateQuota successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 7.5.31 获取资源名称
	 * @param uuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetResourceNamesAction.Result getResourceNames(String uuids, String sessionId){
		GetResourceNamesAction action = new GetResourceNamesAction();
		action.uuids = Arrays.asList(uuids);
		action.sessionId = sessionId;
		GetResourceNamesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getResourceNames error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				ResourceInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("getResourceNames successfully resourceName:%s,uuid:%s,resourceType:%s", 
						inventory.resourceName, inventory.uuid, inventory.resourceType));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.32 查询共享资源
	 * @param accountUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QuerySharedResourceAction.Result querySharedResource(String accountUuid, String sessionId){
		QuerySharedResourceAction action = new QuerySharedResourceAction();
		action.conditions = Arrays.asList("accountUuid=" + accountUuid);
		action.sessionId = sessionId;
		QuerySharedResourceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("querySharedResource error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				SharedResourceInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("querySharedResource successfully ownerAccountUuid:%s,receiverAccountUuid:%s,resourceType:%s,resourceUuid:%s,toPublic:%s", 
						inventory.ownerAccountUuid, inventory.receiverAccountUuid, inventory.resourceType, inventory.resourceUuid, inventory.toPublic));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.33 解除资源共享
	 * @param resourceUuids
	 * @param accountUuids
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RevokeResourceSharingAction.Result revokeResourceSharing(String resourceUuids, String accountUuids, String sessionId){
		RevokeResourceSharingAction action = new RevokeResourceSharingAction();
		action.resourceUuids = Arrays.asList(resourceUuids);
		action.toPublic = false;
		action.accountUuids = Arrays.asList(accountUuids);
		action.all = false;
		action.sessionId = sessionId;
		RevokeResourceSharingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("revokeResourceSharing error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("revokeResourceSharing successfully resourceUuids:%s,accountUuids:%s", resourceUuids, accountUuids));
		}
		return res;
	}
	
	/**
	 * 7.5.34 变更资源所有者
	 * @param accountUuid
	 * @param resourceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeResourceOwnerAction.Result changeResourceOwner(String accountUuid, String resourceUuid, String sessionId){
		ChangeResourceOwnerAction action = new ChangeResourceOwnerAction();
		action.accountUuid = accountUuid;
		action.resourceUuid = resourceUuid;
		action.sessionId = sessionId;
		ChangeResourceOwnerAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeResourceOwner error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			AccountResourceRefInventory inventory = res.value.inventory;
			System.out.println(String.format("changeResourceOwner successfully resourceUuid:%s,resourceType:%s", inventory.resourceUuid, inventory.resourceType));
		}
		return res;
	}
	
	/**
	 * 7.5.35 检查API权限
	 * @param userUuid
	 * @param apiNames
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CheckApiPermissionAction.Result checkApiPermission(String userUuid, String apiNames, String sessionId){
		CheckApiPermissionAction action = new CheckApiPermissionAction();
		action.userUuid = userUuid;
		action.apiNames = Arrays.asList(apiNames);
		action.sessionId = sessionId;
		CheckApiPermissionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("checkApiPermission error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			Map<String, String> inventory = res.value.inventory;
			for(Entry<String, String> entry: inventory.entrySet()){
				System.out.println(String.format("checkApiPermission successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	/**
	 * 7.5.36 验证会话的有效性
	 * @param sessionUuid
	 * @return
	 * @author Josh
	 */
	public ValidateSessionAction.Result validateSession(String sessionUuid){
		ValidateSessionAction action = new ValidateSessionAction();
		action.sessionUuid = sessionUuid;
		ValidateSessionAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("validateSession error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("validateSession successfully valid:%s", res.value.valid));
		}
		return res;
	}
	
	/**
	 * 7.5.37 退出当前登录状态
	 * @param sessionUuid
	 * @return
	 * @author Josh
	 */
	public LogOutAction.Result logOut(String sessionUuid){
		LogOutAction action = new LogOutAction();
		action.sessionUuid = sessionUuid;
		LogOutAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("logOut error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("logOut successfully sessionUuid:%s", sessionUuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		UserOrAccountAction account = new UserOrAccountAction();
		
		// 创建账户 Begin
		account.createAccount("lanyan", "lanyan", sessionId);
		// 创建账户 End
		
		// 查询账户 Begin
		account.queryAccount("lanyan", sessionId);
		// 查询账户 End
		
		// 更新账户 Begin
		account.updateAccount("2e6678faccfe4b9d9c581056cbddbedb", "newlanyan", "123456", sessionId);
		// 更新账户 End
		
		// 删除账户 Begin
		account.deleteAccount("7aad297328ac4b94b2465d20190b939e", sessionId);
		// 删除账户 End
		
		// 创建用户 Begin
		account.createUser("lanyan009", "lanyan009", sessionId);
		// 创建用户 End
	}
}