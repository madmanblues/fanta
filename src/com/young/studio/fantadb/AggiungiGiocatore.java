package com.young.studio.fantadb;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class AggiungiGiocatore extends Activity {

	private static final String CHIAVE_SQUADRA = "com.young.studio.idSquadra";

	private long mIdSquadra;
	private Spinner mRuoliSpinner;
	private String mRuoloSelezionato;
	private TextView mNomeGiocatoreField;
	private String mNomeGiocatore;
	private TextView mSquadraRealeField;
	private String mSquadraReale;
	private Button mAggiungiGiocatore;
	private DatabaseHelper mDatabaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aggiungi_giocatore);

		mIdSquadra = getIntent().getLongExtra(CHIAVE_SQUADRA, 0);
		mDatabaseHelper = new DatabaseHelper(this);

		// TODO verifica se mIdSquadra è 0 per verifica errore

		mRuoliSpinner = (Spinner) findViewById(R.id.ruoloSpinner);
		// Crea un ArrayAdapter utilizzando un array di stringhe ed il layout
		// predefinito
		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.ruoli_array,
						android.R.layout.simple_spinner_item);
		// Specifico il layout da utilizzare quando compaiono le scelte
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Applico l'adapter allo spinner
		mRuoliSpinner.setAdapter(adapter);

		// TODO aggiungere il listener per onItemChange allo spinner per salvare
		// la scelta dentro mRuoloSelezionato
		mRuoliSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {
						// nessun item selezionato
					}

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						String aux = mRuoliSpinner.getSelectedItem()
								.toString();
						mRuoloSelezionato = aux.substring(0, 1);
					}

				});

		// aggiungere textWatcher al nome del giocatore
		mNomeGiocatoreField = (TextView) findViewById(R.id.nomeGiocatore);
		mNomeGiocatoreField.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				mNomeGiocatore = s.toString();
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		// aggiungere textWatcher alla squadra reale nella quale gioca
		mSquadraRealeField = (TextView) findViewById(R.id.squadraDoveGioca);
		mSquadraRealeField.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				mSquadraReale = s.toString();
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		// aggiungere il listener al button per salvare il giocatore
		mAggiungiGiocatore = (Button) findViewById(R.id.salvaGiocatoreBtn);
		mAggiungiGiocatore.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO controlli sui campi prima di chiamare insert
				mDatabaseHelper.inserisciGiocatore(mNomeGiocatore,
						mRuoloSelezionato, mIdSquadra, mSquadraReale);
				// TODO Toast con nome giocatore inserito
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aggiungi_giocatore, menu);
		return true;
	}

}
