/**
 * 
 */
package com.young.studio.fantadb;

import java.text.MessageFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Gabriel Gori
 * 
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "fantaapp.db";
	private static final int SCHEMA_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// Creazione tabella squadre
		String sql = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " {2} TEXT NOT NULL,{3} TEXT NOT NULL, {4} INTEGER,"
				+ " {5} INTEGER, {6} INTEGER, {7} INTEGER);";
		db.execSQL(MessageFormat.format(sql, ISquadreTable.NOME_TABELLA,
				ISquadreTable._ID, ISquadreTable.NOME_SQUADRA,
				ISquadreTable.LEGA, ISquadreTable.NUM_PORTIERI,
				ISquadreTable.NUM_DIFENSORI, ISquadreTable.NUM_CENTROCAMPISTI,
				ISquadreTable.NUM_ATTACCANTI));

		// Creazione tabella giocatori
		String sqlGiocatori = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " {2} TEXT NOT NULL,{3} TEXT NOT NULL, {4} INTEGER,"
				+ " {5} TEXT);";
		db.execSQL(MessageFormat.format(sqlGiocatori,
				IGiocatoriTable.NOME_TABELLA, IGiocatoriTable._ID,
				IGiocatoriTable.NOME_GIOCATORE, IGiocatoriTable.RUOLO,
				IGiocatoriTable.ID_SQUADRA, IGiocatoriTable.SQUADRA_REALE));

		// inserisciSquadra(db, "Boca Jrs.", "Serie A", 3, 8, 8, 6);

	}

	/**
	 * Questa funzione inserisce le nuove squadre nel db
	 * 
	 * @param nomeSquadra
	 * @param lega
	 * @param numPortieri
	 * @param numDifensori
	 * @param numCentrocampisti
	 * @param numAttaccanti
	 */
	public void inserisciSquadra(String nomeSquadra, String lega,
			int numPortieri, int numDifensori, int numCentrocampisti,
			int numAttaccanti) {
		ContentValues valori = new ContentValues();
		valori.put(ISquadreTable.NOME_SQUADRA, nomeSquadra);
		valori.put(ISquadreTable.LEGA, lega);
		valori.put(ISquadreTable.NUM_PORTIERI, numPortieri);
		valori.put(ISquadreTable.NUM_DIFENSORI, numDifensori);
		valori.put(ISquadreTable.NUM_CENTROCAMPISTI, numCentrocampisti);
		valori.put(ISquadreTable.NUM_ATTACCANTI, numAttaccanti);

		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(ISquadreTable.NOME_TABELLA, null, valori);

		db.close();
	}

	/**
	 * Questa funzione serve a richiamare tutte le suqdre presenti nel DB
	 * 
	 * @return Un cursore con tutte le squadre presenti nel DB
	 */
	public Cursor getSquadre() {
		return (getReadableDatabase().query(ISquadreTable.NOME_TABELLA,
				ISquadreTable.COLONNE, null, null, null, null, null));
	}

	/**
	 * Questa funzione prende in ingresso il nome di una squadra e restituisce
	 * il suo ID all'interno del DB L'ID è il campo chiave e può essere
	 * utilizzato per reperire i giocatori di una squadra dalla tabella
	 * giocatori
	 * 
	 * @param nomeSquadra
	 * @return int un intero che identifica l'ID della squadra richiesto
	 */
	public int getIdSquadra(String nomeSquadra) {
		Cursor cursor = (getReadableDatabase().query(
				ISquadreTable.NOME_TABELLA, ISquadreTable.COLONNE,
				ISquadreTable.NOME_SQUADRA + " = " + nomeSquadra, null, null,
				null, null));

		return cursor.getInt(cursor.getColumnIndex(ISquadreTable._ID));
	}

	/**
	 * 
	 * @param nomeGiocatore
	 * @param ruolo
	 * @param idSquadra
	 * @param squadraReale
	 */
	public void inserisciGiocatore(String nomeGiocatore, String ruolo,
			long idSquadra, String squadraReale) {
		ContentValues valori = new ContentValues();
		valori.put(IGiocatoriTable.NOME_GIOCATORE, nomeGiocatore);
		valori.put(IGiocatoriTable.RUOLO, ruolo);
		valori.put(IGiocatoriTable.ID_SQUADRA, idSquadra);
		valori.put(IGiocatoriTable.SQUADRA_REALE, squadraReale);

		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(IGiocatoriTable.NOME_TABELLA, null, valori);

		db.close();
	}

	/**
	 * 
	 * @param id
	 */
	public void eliminaGiocatore(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(IGiocatoriTable.NOME_TABELLA, IGiocatoriTable._ID + " = ?",
				new String[] { String.valueOf(id) });

		db.close();
	}

	/**
	 * 
	 * @param squadra
	 * @return
	 */
	public Cursor getGiocatori(long squadra) {
		return (getReadableDatabase().query(IGiocatoriTable.NOME_TABELLA,
				IGiocatoriTable.COLONNE, IGiocatoriTable.ID_SQUADRA + " = "
						+ squadra, null, null, null, null));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop old
		db.execSQL("DROP TABLE IF EXISTS " + ISquadreTable.NOME_TABELLA);
		db.execSQL("DROP TABLE IF EXISTS " + IGiocatoriTable.NOME_TABELLA);
		// create fresh table
		this.onCreate(db);
	}

}
