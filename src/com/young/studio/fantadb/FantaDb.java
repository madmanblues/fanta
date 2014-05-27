package com.young.studio.fantadb;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FantaDb extends ListActivity {

	private static final String CHIAVE_SQUADRA = "com.young.studio.idSquadra";

	private DatabaseHelper mDatabaseHelper;
	private ListView mList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fanta_db);
		
		mDatabaseHelper = new DatabaseHelper(this);
		Cursor c = mDatabaseHelper.getSquadre();

		mList = (ListView) findViewById(android.R.id.list);

		TextView footerView = (TextView) getLayoutInflater().inflate(
				R.layout.squadra_footer, null);
		mList.addFooterView(footerView);

		startManagingCursor(c);
		setListAdapter(new SquadreSimpleCursorAdapter(this, c));

		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO creare intent per aprire activity di gestione squadra
				Intent intent = new Intent(FantaDb.this, GestioneSquadra.class);
				intent.putExtra(CHIAVE_SQUADRA, id);
				startActivity(intent);
				/*String text = "id = " + String.valueOf(id) + " " + "position = " + String.valueOf(position);
				Toast.makeText(getApplicationContext(),
						text,
						Toast.LENGTH_LONG).show();*/
			}

		});

		footerView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FantaDb.this, DettagliSquadra.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mDatabaseHelper.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fanta_db, menu);
		return true;
	}

}
