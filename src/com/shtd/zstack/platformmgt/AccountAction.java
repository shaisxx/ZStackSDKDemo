package com.shtd.zstack.platformmgt;

import java.util.Arrays;

import org.zstack.sdk.AccountInventory;
import org.zstack.sdk.CreateAccountAction;
import org.zstack.sdk.CreateUserAction;
import org.zstack.sdk.DeleteAccountAction;
import org.zstack.sdk.QueryAccountAction;
import org.zstack.sdk.UpdateAccountAction;
import org.zstack.sdk.UserInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 平台管理-用户管理相关接口
 * @author Josh
 * @date 2017-11-29
 */
public class AccountAction {

	/**
	 * 创建账户
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
	 * 查询账户
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
	 * 更新账户信息
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
	 * 删除账户
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
	 * 创建用户
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
	
	public static void main(String[] args) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		AccountAction account = new AccountAction();
		
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