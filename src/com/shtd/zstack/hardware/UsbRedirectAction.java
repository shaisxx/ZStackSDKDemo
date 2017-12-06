package com.shtd.zstack.hardware;

import org.zstack.sdk.GetVmUsbRedirectAction;
import org.zstack.sdk.SetVmUsbRedirectAction;

/**
 * 4 硬件设施-物理机相关接口-USB重定向相关接口
 * @author Josh
 * @date 2017-12-06
 */
public class UsbRedirectAction {
	
	/**
	 * 4.3.11.1 获取云主机usb重定向开关状态
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmUsbRedirectAction.Result getVmUsbRedirect(String uuid, String sessionId){
		GetVmUsbRedirectAction action = new GetVmUsbRedirectAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmUsbRedirectAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmUsbRedirect error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getVmUsbRedirect successfully enable:%s", res.value.enable));
		}
		return res;
	}
	
	/**
	 * 4.3.11.2 设置云主机usb重定向开关
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SetVmUsbRedirectAction.Result setVmUsbRedirect(String uuid, String sessionId){
		SetVmUsbRedirectAction action = new SetVmUsbRedirectAction();
		action.uuid = uuid;
		action.enable = true;
		action.sessionId = sessionId;
		SetVmUsbRedirectAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVmUsbRedirect error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("setVmUsbRedirect successfully uuid:%s", uuid));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}