/**
 * 
 */
package com.young.studio.fantadb;

import android.provider.BaseColumns;

/**
 * @author Gabriel Gori
 * 
 */
public interface ISquadreTable extends BaseColumns {
	String NOME_TABELLA = "squadre";
	String NOME_SQUADRA = "nomeSquadra";
	String LEGA = "lega";
	String NUM_PORTIERI = "numPortieri";
	String NUM_DIFENSORI = "numDifensori";
	String NUM_CENTROCAMPISTI = "numCentrocampisti";
	String NUM_ATTACCANTI = "numAttaccanti";

	String[] COLONNE = new String[] { _ID, NOME_SQUADRA, LEGA,
			NUM_PORTIERI, NUM_DIFENSORI, NUM_CENTROCAMPISTI, NUM_ATTACCANTI };

}
