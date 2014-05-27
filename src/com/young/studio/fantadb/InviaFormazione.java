package com.young.studio.fantadb;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InviaFormazione extends Activity {

	private static final String CHIAVE_SQUADRA = "com.young.studio.idSquadra";
	private Spinner mModuliSpinner;
	private String mModulo;
	private long mIdSquadra;
	private DatabaseHelper mDatabaseHelper;
	
	private String[] mRuoloGiocatore;
	private String[] mGiocatoriSelezionati;
	private String[] mGiocatoriPanchina;
	private String[] mPortieri;
	private String[] mDifensori;
	private String[] mCentrocampisti;
	private String[] mAttaccanti;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invia_formazione);
		
		mDatabaseHelper = new DatabaseHelper(this);
		mIdSquadra = getIntent().getLongExtra(CHIAVE_SQUADRA, 0);
		
		mModuliSpinner = (Spinner) findViewById(R.id.moduliSpinner);
		final String[] itemsModuli = new String[] { "3-4-3", "3-5-2", "4-4-2", "5-3-2", "4-3-3" };
		ArrayAdapter<String> adapterModuli = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itemsModuli);
		mModuliSpinner.setAdapter(adapterModuli);
		mModuliSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mModulo = itemsModuli[arg2];
						/*
						 *  YourFragment fragment = new YourFragment();
						 *	FragmentTransaction ft = getFragmentManager().beginTransaction();
						 *	ft.replace(R.id.fragment_placeholder, fragment);
						 *	ft.commit();
						 */
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
		
	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fanta_db, menu);
		return true;
	}
	
}
