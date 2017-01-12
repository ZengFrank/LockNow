package com.ben.locknow;



import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	private DevicePolicyManager policyManager;
	private ComponentName componentName;
	private static final int MY_REQUEST_CODE = 9999;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		componentName = new ComponentName(this, AdminReceiver.class);

		// 判断是否有锁屏权限，若有则立即锁屏并结束自己，若没有则获取权限
		if (policyManager.isAdminActive(componentName)) {
			policyManager.lockNow();
			//moveTaskToBack(true);
			finish();
		} else {
			activeManage();
		}
	}
	
	// 获取权限
		private void activeManage() {
			// TODO Auto-generated method stub
			// 启动设备管理(隐式Intent) - 在AndroidManifest.xml中设定相应过滤器
			Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			// 权限列表
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);

			// 描述(additional explanation)
			intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
					getString(R.string.home_lock_msg));

			startActivityForResult(intent, MY_REQUEST_CODE);

		}

}
