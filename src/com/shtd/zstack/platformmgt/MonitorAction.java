package com.shtd.zstack.platformmgt;

import java.util.Map;
import java.util.Map.Entry;

import org.zstack.sdk.PrometheusQueryLabelValuesAction;
import org.zstack.sdk.PrometheusQueryMetadataAction;
import org.zstack.sdk.PrometheusQueryVmMonitoringDataAction;

/**
 * 7 平台管理-监控报警相关接口
 * @author Josh
 * @date 2017-12-06
 */
public class MonitorAction {

	/**
	 * 7.1.2 查询虚拟机监控数据
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	@SuppressWarnings("unchecked")
	public PrometheusQueryVmMonitoringDataAction.Result prometheusQueryVmMonitoringData(String sessionId){
		PrometheusQueryVmMonitoringDataAction action = new PrometheusQueryVmMonitoringDataAction();
		action.instant = false;
		action.sessionId = sessionId;
		PrometheusQueryVmMonitoringDataAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("prometheusQueryVmMonitoringData error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			Map<String, Object> inventory = (Map<String, Object>)res.value.inventories;
			for(Entry<String, Object> entry: inventory.entrySet()){
				System.out.println(String.format("prometheusQueryVmMonitoringData successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	/**
	 * 7.1.3 查询一个label有多少个value
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	@SuppressWarnings("unchecked")
	public PrometheusQueryLabelValuesAction.Result prometheusQueryLabelValues(String sessionId){
		PrometheusQueryLabelValuesAction action = new PrometheusQueryLabelValuesAction();
		action.sessionId = sessionId;
		PrometheusQueryLabelValuesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("prometheusQueryLabelValues error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			Map<String, Object> inventory = (Map<String, Object>)res.value.inventories;
			for(Entry<String, Object> entry: inventory.entrySet()){
				System.out.println(String.format("prometheusQueryLabelValues successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	/**
	 * 7.1.4 查询一个metric有多少个label
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	@SuppressWarnings("unchecked")
	public PrometheusQueryMetadataAction.Result prometheusQueryMetadata(String sessionId){
		PrometheusQueryMetadataAction action = new PrometheusQueryMetadataAction();
		action.sessionId = sessionId;
		PrometheusQueryMetadataAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("prometheusQueryMetadata error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else{
			Map<String, Object> inventory = (Map<String, Object>)res.value.inventories;
			for(Entry<String, Object> entry: inventory.entrySet()){
				System.out.println(String.format("prometheusQueryMetadata successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

	}
}