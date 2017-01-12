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

		// �ж��Ƿ�������Ȩ�ޣ����������������������Լ�����û�����ȡȨ��
		if (policyManager.isAdminActive(componentName)) {
			policyManager.lockNow();
			//moveTaskToBack(true);
			finish();
		} else {
			activeManage();
		}
	}
	
	// ��ȡȨ��
		private void activeManage() {
			// TODO Auto-generated method stub
			// �����豸����(��ʽIntent) - ��AndroidManifest.xml���趨��Ӧ������
			Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			// Ȩ���б�
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);

			// ����(additional explanation)
			intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
					getString(R.string.home_lock_msg));

			startActivityForResult(intent, MY_REQUEST_CODE);

		}

}
