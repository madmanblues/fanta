/**
 * 
 */
package com.young.studio.fantadb;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.widget.TextView;
import android.support.v4.widget.SimpleCursorAdapter;

/**
 * @author Gabriel Gori
 *
 */
public class RosaSimpleCursorAdapter extends SimpleCursorAdapter {

	private Context context;

	public RosaSimpleCursorAdapter(Context context, Cursor cursor) {
		super(context, R.layout.rosa_list_item, cursor, new String[] {
				IGiocatoriTable.RUOLO, IGiocatoriTable.NOME_GIOCATORE,
				IGiocatoriTable.SQUADRA_REALE }, new int[] {
				R.id.quadroRuolo, R.id.nomeGiocatore, R.id.squadraVera },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		this.context = context;
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		super.bindView(view, context, cursor);
		TextView ruoloView = (TextView) view.findViewById(R.id.quadroRuolo);
		String ruolo = ruoloView.getText().toString();
		if(ruolo.equals("P")){
			ruoloView.setTextAppearance(context, R.style.qudratoPortieri);
			ruoloView.setBackgroundResource(R.color.arancione);
		}
		if(ruolo.equals("D")){
			ruoloView.setTextAppearance(context, R.style.qudratoDifensori);
			ruoloView.setBackgroundResource(R.color.verde);
		}
		if(ruolo.equals("C")){
			ruoloView.setTextAppearance(context, R.style.qudratoCentrocampisti);
			ruoloView.setBackgroundResource(R.color.blu);
		}
		if(ruolo.equals("A")){
			ruoloView.setTextAppearance(context, R.style.qudratoAttaccanti);
			ruoloView.setBackgroundResource(R.color.rosso);
		}
	}
	

}
