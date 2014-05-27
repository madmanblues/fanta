package com.young.studio.fantadb;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DettagliSquadra extends Activity {

	private Spinner mSpinPortieri;
	private Spinner mSpinDifensori;
	private Spinner mSpinCentrocampisti;
	private Spinner mSpinAttaccanti;
	private int mNumPortieri;
	private int mNumDifensori = 0;
	private int mNumCentrocampisti = 0;
	private int mNumAttaccanti = 0;
	private String mNomeSquadra = "";
	private String mNomeLega = "";
	private DatabaseHelper mDatabaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettagli_squadra);

		// Inizializzazione del DB
		mDatabaseHelper = new DatabaseHelper(this);

		// Comportamento della combobox per la scelta del numero di portieri
		mSpinPortieri = (Spinner) findViewById(R.id.spinner_portieri);
		final Integer[] itemsPort = new Integer[] { 1, 2, 3, 4, 5 };
		ArrayAdapter<Integer> adapterPort = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, itemsPort);
		mSpinPortieri.setAdapter(adapterPort);
		mSpinPortieri
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mNumPortieri = (int) itemsPort[arg2];
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		// Comportamento della combobox per la scelta del numero di difensori
		mSpinDifensori = (Spinner) findViewById(R.id.spinner_difensori);
		final Integer[] itemsDif = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				10 };
		ArrayAdapter<Integer> adapterDif = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, itemsDif);
		mSpinDifensori.setAdapter(adapterDif);
		mSpinDifensori
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mNumDifensori = (int) itemsDif[arg2];
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		// Comportamento della combobox per la scelta del numero di
		// Centrocampisti
		mSpinCentrocampisti = (Spinner) findViewById(R.id.spinner_centrocampisti);
		final Integer[] itemsCentro = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8,
				9, 10 };
		ArrayAdapter<Integer> adapterCentro = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, itemsCentro);
		mSpinCentrocampisti.setAdapter(adapterCentro);
		mSpinCentrocampisti
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mNumCentrocampisti = (int) itemsCentro[arg2];
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		// Comportamento della combobox per la scelta del numero di Attaccanti
		mSpinAttaccanti = (Spinner) findViewById(R.id.spinner_attaccanti);
		final Integer[] itemsAttac = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				10 };
		ArrayAdapter<Integer> adapterAttac = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, itemsAttac);
		mSpinAttaccanti.setAdapter(adapterAttac);
		mSpinAttaccanti
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mNumAttaccanti = (int) itemsAttac[arg2];
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		// Comportamento del EditText nome Squadra
		EditText nomeSquadraEdit = (EditText) findViewById(R.id.nome_squadra);
		nomeSquadraEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				mNomeSquadra = s.toString();
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		// Comportamento del EditText per il nome Lega
		EditText nomeLegaEdit = (EditText) findViewById(R.id.nome_lega);
		nomeLegaEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				mNomeLega = s.toString();
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		// Comportamento del pulsante per salvare la nuova squadra sul DB
		Button salva = (Button) findViewById(R.id.salva_squadra_button);
		salva.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Inserimento della squadra su DB
				// Toast.makeText(getApplicationContext(), mNomeLega,
				// Toast.LENGTH_LONG).show();
				mDatabaseHelper.inserisciSquadra(mNomeSquadra, mNomeLega,
						mNumPortieri, mNumDifensori, mNumCentrocampisti,
						mNumAttaccanti);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dettagli_squadra, menu);
		return true;
	}

}
