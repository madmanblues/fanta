package com.young.studio.fantadb;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class RosaGiocatori extends ListActivity {
	
	private static final String CHIAVE_SQUADRA = "com.young.studio.idSquadra"; 
	
	private DatabaseHelper mDatabaseHelper;
	private ListView mList = null;
	private long mIdSquadra;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rosa_giocatori);
		
		mDatabaseHelper = new DatabaseHelper(this);
		
		mIdSquadra = getIntent().getLongExtra(CHIAVE_SQUADRA, 0);
		
		if(mIdSquadra == 0){
			// Errore, tornare indietro squadra non esiste
		}
		
		Cursor c = mDatabaseHelper.getGiocatori(mIdSquadra);
		
		mList = (ListView) findViewById(android.R.id.list);
		
		TextView footerView = (TextView) getLayoutInflater().inflate(
				R.layout.rosa_footer, null);
		mList.addFooterView(footerView);

		startManagingCursor(c);
		setListAdapter(new RosaSimpleCursorAdapter(this, c));
		
		footerView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RosaGiocatori.this, AggiungiGiocatore.class);
				intent.putExtra(CHIAVE_SQUADRA, mIdSquadra);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rosa_giocatori, menu);
		return true;
	}

}
