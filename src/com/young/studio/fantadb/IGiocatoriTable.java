/**
 * 
 */
package com.young.studio.fantadb;

import android.provider.BaseColumns;

/**
 * @author Gabero
 *
 */
public interface IGiocatoriTable extends BaseColumns {
	
	String NOME_TABELLA = "giocatori";
	String NOME_GIOCATORE = "nomeGiocatore";
	String RUOLO = "ruolo";
	String ID_SQUADRA = "idSquadra";
	String SQUADRA_REALE = "squadraReale";
	
	String[] COLONNE = new String[] { _ID, NOME_GIOCATORE, RUOLO,
			ID_SQUADRA, SQUADRA_REALE };
}
