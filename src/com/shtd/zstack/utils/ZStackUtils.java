package com.shtd.zstack.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.zstack.sdk.GetVersionAction;
import org.zstack.sdk.LogInByAccountAction;
import org.zstack.sdk.ZSClient;
import org.zstack.sdk.ZSConfig;

public class ZStackUtils {
	
	public static String hosturl = "192.168.3.43";
	public static String username = "admin";
	public static String password = "password";
	
	@SuppressWarnings("unused")
	public static String ZStackLogin() {
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
		logInByAccountAction.password = SHA(password, "SHA-512");
		LogInByAccountAction.Result logInByAccountActionRes = logInByAccountAction.call();
		sessionId = logInByAccountActionRes.value.getInventory().getUuid();

		return sessionId;
	}

	public static String SHA(final String strText, final String strType) {
		// 返回值
		String strResult = null;

		// 是否是有效字符串
		if (strText != null && strText.length() > 0) {
			try {
				// SHA 加密开始
				// 创建加密对象 并傳入加密類型
				MessageDigest messageDigest = MessageDigest.getInstance(strType);
				// 传入要加密的字符串
				messageDigest.update(strText.getBytes());
				// 得到 byte 類型结果
				byte byteBuffer[] = messageDigest.digest();

				// 將 byte 轉換爲 string
				StringBuffer strHexString = new StringBuffer();
				// 遍歷 byte buffer
				for (int i = 0; i < byteBuffer.length; i++) {
					String hex = Integer.toHexString(0xff & byteBuffer[i]);
					if (hex.length() == 1) {
						strHexString.append('0');
					}
					strHexString.append(hex);
				}
				// 得到返回結果
				strResult = strHexString.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		return strResult;
	}
}