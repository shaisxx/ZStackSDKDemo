package com.shtd.zstack.platformmgt;

import java.util.Arrays;

import org.zstack.sdk.CalculateAccountSpendingAction;
import org.zstack.sdk.CreateResourcePriceAction;
import org.zstack.sdk.DeleteResourcePriceAction;
import org.zstack.sdk.PriceInventory;
import org.zstack.sdk.QueryResourcePriceAction;

/**
 * 7 平台管理-计费相关接口
 * @author Josh
 * @date 2017-12-05
 */
public class ResourcePriceAction {

	/**
	 * 7.2.1 创建资源价格
	 * @param resourceName
	 * @param timeUnit
	 * @param price
	 * @param dateInLong
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CreateResourcePriceAction.Result createResourcePrice(String resourceName, String timeUnit, double price, Long dateInLong, String sessionId) {
		CreateResourcePriceAction action = new CreateResourcePriceAction();
		action.resourceName = resourceName;
		action.timeUnit = timeUnit;
		action.price = price;
		action.dateInLong = dateInLong;
		action.sessionId = sessionId;
		CreateResourcePriceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("createResourcePrice error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			PriceInventory inventory = res.value.inventory;
			System.out.println(String.format("createResourcePrice successfully uuid:%s,resourceUnit:%s,timeUnit:%s", 
					inventory.uuid, inventory.resourceUnit, inventory.timeUnit));
		}
		return res;
	}

	/**
	 * 7.2.2 删除资源价格
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteResourcePriceAction.Result deleteResourcePrice(String uuid, String sessionId) {
		DeleteResourcePriceAction action = new DeleteResourcePriceAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteResourcePriceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteResourcePrice error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("deleteResourcePrice successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 7.2.3 查询资源价格
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryResourcePriceAction.Result queryResourcePrice(String uuid, String sessionId) {
		QueryResourcePriceAction action = new QueryResourcePriceAction();
		action.conditions = Arrays.asList("uuid=" + uuid);
		action.sessionId = sessionId;
		QueryResourcePriceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryResourcePrice error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				PriceInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryResourcePrice successfully uuid:%s,dateInLong:%s,price:%s,resourceName:%s,resourceUnit:%s", 
						inventory.uuid, inventory.dateInLong, inventory.price, inventory.resourceName, inventory.resourceUnit));
			}
		}
		return res;
	}

	/**
	 * 7.2.4 计算账户花费
	 * @param accountUuid
	 * @param dateStart
	 * @param dateEnd
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public CalculateAccountSpendingAction.Result calculateAccountSpending(String accountUuid, Long dateStart, Long dateEnd, String sessionId) {
		CalculateAccountSpendingAction action = new CalculateAccountSpendingAction();
		action.accountUuid = accountUuid;
		action.dateStart = dateStart;
		action.dateEnd = dateEnd;
		action.sessionId = sessionId;
		CalculateAccountSpendingAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("calculateAccountSpending error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			System.out.println(String.format("calculateAccountSpending successfully total:%s", res.value.total));
		}
		return res;
	}

	public static void main(String[] args) {

	}
}