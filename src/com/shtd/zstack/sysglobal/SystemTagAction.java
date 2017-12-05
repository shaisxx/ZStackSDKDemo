package com.shtd.zstack.sysglobal;

import java.util.Arrays;

import org.zstack.sdk.CreateSystemTagAction;
import org.zstack.sdk.CreateUserTagAction;
import org.zstack.sdk.DeleteTagAction;
import org.zstack.sdk.QuerySystemTagAction;
import org.zstack.sdk.QueryUserTagAction;
import org.zstack.sdk.SystemTagInventory;
import org.zstack.sdk.UpdateSystemTagAction;
import org.zstack.sdk.UserInventory;
import org.zstack.sdk.UserTagInventory;

/**
 * 8 系统全局相关-标签相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class SystemTagAction {
	
	/**
	 * 8.2.1 创建系统标签
	 * @param resourceType
	 * @param resourceUuid
	 * @param tag
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateSystemTagAction.Result createSystemTag(String resourceType, String resourceUuid, String tag, String sessionId){
		CreateSystemTagAction action = new CreateSystemTagAction();
		action.resourceType = resourceType;
		action.resourceUuid = resourceUuid;
		action.tag = tag;
		action.sessionId = sessionId;
		CreateSystemTagAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createSystemTag error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SystemTagInventory inventory = res.value.inventory;
			System.out.println(String.format("createSystemTag successfully inherent:%s,uuid:%s", inventory.inherent, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 8.2.2 查询系统标签
	 * @param inherent
	 * @param resourceType
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QuerySystemTagAction.Result querySystemTag(boolean inherent, String resourceType, String sessionId){
		QuerySystemTagAction action = new QuerySystemTagAction();
		action.conditions = Arrays.asList("inherent=" + inherent, "resourceType=" + resourceType);
		action.sessionId = sessionId;
		QuerySystemTagAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("querySystemTag error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				SystemTagInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("querySystemTag successfully tag:%s,uuid:%s", inventory.tag, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 8.2.3 更新系统标签
	 * @param uuid
	 * @param tag
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateSystemTagAction.Result updateSystemTag(String uuid, String tag, String sessionId){
		UpdateSystemTagAction action = new UpdateSystemTagAction();
		action.uuid = uuid;
		action.tag = tag;
		action.sessionId = sessionId;
		UpdateSystemTagAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateSystemTag error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			SystemTagInventory inventory = res.value.inventory;
			System.out.println(String.format("updateSystemTag successfully resourceType:%s,resourceUuid:%s,uuid:%s", 
					inventory.resourceType, inventory.resourceUuid, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 8.2.4 创建用户标签
	 * @param resourceType
	 * @param resourceUuid
	 * @param tag
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateUserTagAction.Result createUserTag(String resourceType, String resourceUuid, String tag, String sessionId){
		CreateUserTagAction action = new CreateUserTagAction();
		action.resourceType = resourceType;
		action.resourceUuid = resourceUuid;
		action.tag = tag;
		action.sessionId = sessionId;
		CreateUserTagAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createUserTag error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			UserTagInventory inventory = res.value.inventory;
			System.out.println(String.format("createUserTag successfully type:%s,uuid:%s", inventory.type, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 8.2.5 查询用户标签
	 * @param resourceType
	 * @param tag
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryUserTagAction.Result queryUserTag(String resourceType, String tag, String sessionId){
		QueryUserTagAction action = new QueryUserTagAction();
		action.conditions = Arrays.asList("resourceType=" + resourceType, "tag=" + tag);
		action.sessionId = sessionId;
		QueryUserTagAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryUserTag error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				UserInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryUserTag successfully tag:%s,uuid:%s", 
						inventory.accountUuid, inventory.description, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 8.2.6 删除标签
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteTagAction.Result deleteTag(String uuid, String sessionId){
		DeleteTagAction action = new DeleteTagAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteTagAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteTag error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteTag successfully uuid:%s", uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}