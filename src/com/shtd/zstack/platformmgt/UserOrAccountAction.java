package com.shtd.zstack.platformmgt;

import java.util.Arrays;

import org.zstack.sdk.AccountInventory;
import org.zstack.sdk.AddUserToGroupAction;
import org.zstack.sdk.AttachPolicyToUserGroupAction;
import org.zstack.sdk.CreateAccountAction;
import org.zstack.sdk.CreateUserAction;
import org.zstack.sdk.CreateUserGroupAction;
import org.zstack.sdk.DeleteAccountAction;
import org.zstack.sdk.DeleteUserGroupAction;
import org.zstack.sdk.DetachPolicyFromUserGroupAction;
import org.zstack.sdk.GetAccountQuotaUsageAction;
import org.zstack.sdk.LogInByAccountAction;
import org.zstack.sdk.QueryAccountAction;
import org.zstack.sdk.QueryAccountResourceRefAction;
import org.zstack.sdk.QueryUserGroupAction;
import org.zstack.sdk.RemoveUserFromGroupAction;
import org.zstack.sdk.ShareResourceAction;
import org.zstack.sdk.UpdateAccountAction;
import org.zstack.sdk.UpdateUserGroupAction;
import org.zstack.sdk.UserInventory;

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
	
	// 7.5.5 使用账户身份登录
	public void logInByAccount(String accountName, String password){
		LogInByAccountAction action = new LogInByAccountAction();
		action.accountName = accountName;
		action.password = password;
		LogInByAccountAction.Result res = action.call();
	}
	
	// 7.5.6 获取账户配额使用情况
	public void getAccountQuotaUsage(){
		GetAccountQuotaUsageAction action = new GetAccountQuotaUsageAction();
		action.uuid = "a4b28114528645989d70729385149220";
		action.sessionId = "0e8ef3f8ed9145d3a6c81cc960d6e23d";
		GetAccountQuotaUsageAction.Result res = action.call();
	}
	
	// 7.5.7 查询账户资源引用
	public void queryAccountResourceRef(String acountUuid, String sessionId){
		QueryAccountResourceRefAction action = new QueryAccountResourceRefAction();
		action.conditions = Arrays.asList("acountUuid=" + acountUuid);
		action.sessionId = "87bbd9779dbf4db0bfa73fbc9f073bec";
		QueryAccountResourceRefAction.Result res = action.call();
	}
	
	// 7.5.8 共享资源给账户
	public void shareResource(){
		ShareResourceAction action = new ShareResourceAction();
		action.resourceUuids = Arrays.asList("91081232bea84a50af90c7da27d54442","91d6ee72dbd246a88f3004cbaac20998");
		action.accountUuids = Arrays.asList("b3dcbb40827441b5aef2fd3150baa3a9","3985fbda90044fcc8e45d1fbe002eec4");
		action.toPublic = false;
		action.sessionId = "43b022aa93aa4e63a7d11a993d1ba75d";
		ShareResourceAction.Result res = action.call();
	}
	
	// 7.5.9 创建用户组
	public void createUserGroup(){
		CreateUserGroupAction action = new CreateUserGroupAction();
		action.name = "usergroup";
		action.sessionId = "dbd9237abef344b0b0ddbb947e0c993d";
		CreateUserGroupAction.Result res = action.call();
	}
	
	// 7.5.10 删除用户组
	public void deleteUserGroup(){
		DeleteUserGroupAction action = new DeleteUserGroupAction();
		action.uuid = "c58611a13aaa4a38ac7ca35883dff419";
		action.deleteMode = "Permissive";
		action.sessionId = "d0b45ce10eb14370a8dd67c407aa4815";
		DeleteUserGroupAction.Result res = action.call();
	}
	
	// 7.5.11 查询用户组
	public void queryUserGroup(String name, String sessionId){
		QueryUserGroupAction action = new QueryUserGroupAction();
		action.conditions = Arrays.asList("name=" + name);
		action.sessionId = sessionId;
		QueryUserGroupAction.Result res = action.call();
	}
	
	// 7.5.12 更新用户组
	public void updateUserGroup(){
		UpdateUserGroupAction action = new UpdateUserGroupAction();
		action.uuid = "12a5413d1d1b4858b57edb47043dd305";
		action.name = "newname";
		action.sessionId = "3c8fc80478ed4d0c9306b6aee3186d65";
		UpdateUserGroupAction.Result res = action.call();
	}
	
	// 7.5.13 添加到用户组
	public void addUserToGroup(String userUuid, String groupUuid, String sessionId){
		AddUserToGroupAction action = new AddUserToGroupAction();
		action.userUuid = userUuid;
		action.groupUuid = groupUuid;
		action.sessionId = "27a0e742c07b4f61b3294d0b445412d6";
		AddUserToGroupAction.Result res = action.call();
	}
	
	// 7.5.14 绑定策略到用户组
	public void attachPolicyToUserGroup(String policyUuid, String groupUuid, String sessionId){
		AttachPolicyToUserGroupAction action = new AttachPolicyToUserGroupAction();
		action.policyUuid = policyUuid;
		action.groupUuid = groupUuid;
		action.sessionId = sessionId;
		AttachPolicyToUserGroupAction.Result res = action.call();
	}
	
	// 7.5.15 将策略从用户组解绑
	public void detachPolicyFromUserGroup(String policyUuid, String groupUuid, String sessionId){
		DetachPolicyFromUserGroupAction action = new DetachPolicyFromUserGroupAction();
		action.policyUuid = policyUuid;
		action.groupUuid = groupUuid;
		action.sessionId = sessionId;
		DetachPolicyFromUserGroupAction.Result res = action.call();
	}
	
	// 7.5.16 从用户组中移除用户
	public void removeUserFromGroup(String userUuid, String groupUuid, String sessionId){
		RemoveUserFromGroupAction action = new RemoveUserFromGroupAction();
		action.userUuid = userUuid;
		action.groupUuid = groupUuid;
		action.sessionId = sessionId;
		RemoveUserFromGroupAction.Result res = action.call();
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
	
	// 7.5.18 删除用户
	
	// 7.5.19 查询用户
	
	// 7.5.20 更新用户
	
	// 7.5.21 使用用户身份登录
	
	// 7.5.22 绑定一条策略到用户
	
	// 7.5.23 将一条策略从用户解绑
	
	// 7.5.24 绑定多条策略到用户
	
	// 7.5.25 将多条策略从用户解绑
	
	// 7.5.26 创建策略
	
	// 7.5.27 删除策略
	
	// 7.5.28 查询策略
	
	// 7.5.29 查询配额
	
	// 7.5.30 更新配额
	
	// 7.5.31 获取资源名称
	
	// 7.5.32 查询共享资源
	
	// 7.5.33 解除资源共享
	
	// 7.5.34 变更资源所有者
	
	// 7.5.35 检查API权限
	
	// 7.5.36 验证会话的有效性
	
	// 7.5.37 退出当前登录状态
	
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