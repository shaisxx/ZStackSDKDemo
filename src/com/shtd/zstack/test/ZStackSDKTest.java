package com.shtd.zstack.test;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.zstack.sdk.CloneVmInstanceAction;
import org.zstack.sdk.CreateVmInstanceAction;
import org.zstack.sdk.CreateWebhookAction;
import org.zstack.sdk.CreateZoneAction;
import org.zstack.sdk.GetTaskProgressAction;
import org.zstack.sdk.GetVersionAction;
import org.zstack.sdk.LogInByAccountAction;
import org.zstack.sdk.QueryVmInstanceAction;
import org.zstack.sdk.TaskProgressInventory;
import org.zstack.sdk.VmInstanceInventory;
import org.zstack.sdk.ZSClient;
import org.zstack.sdk.ZSConfig;
import org.zstack.sdk.ZoneInventory;

import com.shtd.zstack.utils.ZStackUtils;

//https://www.zybuluo.com/meilei007/note/675498
public class ZStackSDKTest {

	private String mSessionID;

	private String ZStackLogin(String hosturl, String username, String passwd) {
		// 声明sessionId；每个action均需要sessionId
		String sessionId;
		// 设置登录zstack的地址
		ZSConfig.Builder zBuilder = new ZSConfig.Builder();
		zBuilder.setContextPath("zstack");
		zBuilder.setHostname(hosturl);
		ZSClient.configure(zBuilder.build());

		GetVersionAction action = new GetVersionAction();
		GetVersionAction.Result res = action.call();

		// 登录zstack；获取session；需要对密码进行SHA-512算法加密
		LogInByAccountAction logInByAccountAction = new LogInByAccountAction();
		logInByAccountAction.accountName = username;
		logInByAccountAction.password = ZStackUtils.SHA(passwd, "SHA-512");
		LogInByAccountAction.Result logInByAccountActionRes = logInByAccountAction.call();
		sessionId = logInByAccountActionRes.value.getInventory().getUuid();

		return sessionId;
	}

	public void createwebhook() {
		CreateWebhookAction cwaction = new CreateWebhookAction();
		cwaction.sessionId = this.mSessionID;
		cwaction.name = "test";
		cwaction.url = "http://localhost/webhook";
		// cwaction.
		CreateWebhookAction.Result res = cwaction.call();

	}

	public void GetTaskProgress() throws InterruptedException {

		for (int j = 0; j < 10; j++) {
			GetTaskProgressAction action = new GetTaskProgressAction();
			action.apiId = "11c035b586634edeb3bfb21f17e02b40";
			action.all = false;
			action.sessionId = this.mSessionID;
			GetTaskProgressAction.Result res = action.call();

			for (int i = 0; i < res.value.getInventories().size(); i++) {
				TaskProgressInventory tpi = res.value.getInventories().get(i);

				System.out.println("Task Progress content :" + tpi.getContent());
				// res.value.inventories.
			}
			Thread.sleep(1000);
		}

	}
	
	public String CreateZone(String sessionId, String zoneName, String description){
		CreateZoneAction createZoneAction = new CreateZoneAction();
		createZoneAction.name = zoneName;
		createZoneAction.description = description;
		createZoneAction.sessionId = sessionId;
		
		System.out.println("APIID:" + createZoneAction.apiId);
		CreateZoneAction.Result res = createZoneAction.call();

		String vmuuid = "";
		if (res.error != null) {
			System.out.println("Error:" + res.error.code + " " + res.error.description + " " + res.error.details);
		} else {
			ZoneInventory vm = res.value.inventory;
			vmuuid = vm.uuid;
		}
		return vmuuid;
	}

	public String CreateVM(String sessionId, String vmname, String zoneUuid, String instanceOfferingUuid, String ImageUUID, String clusterUuid, String l3NetworkUuids, String description) {
		// 创建虚拟机
		CreateVmInstanceAction createVmInstanceAction = new CreateVmInstanceAction();
		createVmInstanceAction.name = vmname;
		createVmInstanceAction.instanceOfferingUuid = instanceOfferingUuid;
		createVmInstanceAction.imageUuid = ImageUUID;
		if (zoneUuid != "") {
			createVmInstanceAction.zoneUuid = zoneUuid; // 可选字段
		}
		createVmInstanceAction.clusterUuid = clusterUuid;
		// "c11ae3711dd741d2902c87b5a18ba3b7";

		createVmInstanceAction.l3NetworkUuids = Arrays.asList(l3NetworkUuids);
		createVmInstanceAction.rootDiskOfferingUuid = "415291baa51441668f4e96678a442d4b";
		createVmInstanceAction.description = description;
		createVmInstanceAction.sessionId = sessionId;
		System.out.println("APIID:" + createVmInstanceAction.apiId);
		CreateVmInstanceAction.Result res = createVmInstanceAction.call();

		String vmuuid = "";
		if (res.error != null) {
			System.out.println("Error:" + res.error.code + " " + res.error.description + " " + res.error.details);
		} else {
			VmInstanceInventory vm = res.value.inventory;
			vmuuid = vm.uuid;
		}
		return vmuuid;
	}

	public void CloneVm(String fromvmid) {

		CloneVmInstanceAction action = new CloneVmInstanceAction();
		action.apiId = "11c035b586634edeb3bfb21f17e02b40";
		action.vmInstanceUuid = fromvmid;
		action.strategy = "InstantStart";
		action.names = Arrays.asList("vm1110", "vm2220", "vm3330", "vm4440");
		action.sessionId = this.mSessionID;
		CloneVmInstanceAction.Result res = action.call();
	}

	public QueryVmInstanceAction.Result listvm() {
		
		QueryVmInstanceAction action = new QueryVmInstanceAction();

		// action.conditions =
		// Arrays.asList("name=vm1","vmNics.ip=192.168.20.100");
		action.sessionId = this.mSessionID;
		QueryVmInstanceAction.Result res = action.call();

		for (int i = 0; i < res.value.getInventories().size(); i++) {
			VmInstanceInventory inventory = res.value.getInventories().get(i);
			String hostid = inventory.getUuid();
			String vmname = inventory.getName();
			System.out.println("vmuuid:" + hostid + " name:" + vmname);
			// res.value.inventories.
		}

		return res;
	}

	public String getVersion() {
		GetVersionAction action = new GetVersionAction();
		GetVersionAction.Result res = action.call();
		return "";
	}

	public static void main(String[] argv) {

		ZStackSDKTest zst = new ZStackSDKTest();

		zst.mSessionID = zst.ZStackLogin("192.168.3.43", "admin", "password");

		// String vmuuid =
		// zst.CreateVM(zst.mSessionID,"testvmname2","","b22c052db07647b9a76cd36e36da1b0e","755a08d393701f7a86c1d0cbcfb98132","c11ae3711dd741d2902c87b5a18ba3b7","9bd019c111da48a091d8bb84003817ed","desc");
		System.out.println("CloneVm Start");
		zst.CloneVm("ce508ce4f2e944168b6da494747a47c8");
		System.out.println("CloneVm end");

		// System.out.println("vmuuid:"+vmuuid);
		zst.listvm();
		
		zst.CreateZone(zst.mSessionID, "ZONE-2", "This is a zone, Create by call API!");
	}
}