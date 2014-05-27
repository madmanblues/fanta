package com.young.studio.fantadb;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GestioneSquadra extends Activity {
	
	private static final String CHIAVE_SQUADRA = "com.young.studio.idSquadra";

	private DatabaseHelper mDatabaseHelper;
	private Button mAggiungiGiocatore;
	private Button mInviaFormazione;
	private Button mModificaSquadra;
	private Button mEliminaSquadra;
	private long mIdSquadra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestione_squadra);
	
		mDatabaseHelper = new DatabaseHelper(this);
		mIdSquadra = getIntent().getLongExtra(CHIAVE_SQUADRA, 0);
		
		mAggiungiGiocatore = (Button)findViewById(R.id.aggiungiGiocatoreBtn); 
		mAggiungiGiocatore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GestioneSquadra.this, RosaGiocatori.class);
				intent.putExtra(CHIAVE_SQUADRA, mIdSquadra);
				startActivity(intent);
			}
		});
		
		mInviaFormazione = (Button)findViewById(R.id.inviaFormazioneBtn);
		mInviaFormazione.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GestioneSquadra.this, InviaFormazione.class);
				intent.putExtra(CHIAVE_SQUADRA, mIdSquadra);
				startActivity(intent);
			}
		});
		
		mModificaSquadra = (Button)findViewById(R.id.modificaSquadraBtn);
		
		mEliminaSquadra = (Button)findViewById(R.id.eliminaSquadraBtn);
		
		
		// TODO creare i 4 listener per i pulsanti di gestione
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestione_squadra, menu);
		return true;
	}

}
