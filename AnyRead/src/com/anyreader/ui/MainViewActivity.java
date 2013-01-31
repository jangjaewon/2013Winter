package com.anyreader.ui;

import com.anyread.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

public class MainViewActivity extends Activity {

	private ListView listview;
	private static final int Theme_Black=0;
	private int Theme;
	private SharedPreferences mSharedPreferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(android.R.style.Theme_Translucent_NoTitleBar);
		
		
		mSharedPreferences = getSharedPreferences("theme", MODE_PRIVATE);
		Theme = mSharedPreferences.getInt("theme", Theme_Black);
		
		if(Theme == Theme_Black){
			setTheme(android.R.style.Theme_Black);
			setTheme(android.R.style.Theme_Black_NoTitleBar);
		}
		else{
			setTheme(android.R.style.Theme_Light);
			setTheme(android.R.style.Theme_Light_NoTitleBar);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);

		// Tab

		TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();

		TabHost.TabSpec spec = tabHost.newTabSpec("tag1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("���");
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("ģ��");
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("tag3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("��õ");
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("tag4");
		spec.setContent(R.id.tab4);
		spec.setIndicator("����");
		tabHost.addTab(spec);

		// ListView
		String[] Menu = { "���� ����", "�׸� ����" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, Menu);
		listview = (ListView) findViewById(R.id.tab4);
		listview.setChoiceMode(ListView.CHOICE_MODE_NONE);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				if (position == 0)
					setting_font();
				else {
					setting_theme();
				}
			}
		});

		tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_view, menu);
		return true;
	}

	private void setting_font() {
		Intent i = new Intent(this, Setting.class);

		startActivity(i);
	}

	private void setting_theme() {
		final String theme_group[] = { "Black", "White" };
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setTitle("�׸� ����");
		ab.setSingleChoiceItems(theme_group, 0,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// �� ����Ʈ�� ����������
					}
				})
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// OK ��ư Ŭ���� , ���⼭ ������ ���� ���� Activity �� �ѱ�� �ȴ�.
						SharedPreferences.Editor editor = mSharedPreferences.edit();
						if(whichButton ==0){
							Theme = 0;
							editor.putInt("theme", 0);
						}
						else{
							Theme = 1;
							editor.putInt("theme", 1);
						}
						editor.commit();
						
						finish();
						startActivity(getIntent());
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Cancel ��ư Ŭ����
							}
						});
		ab.show();
	}
}
