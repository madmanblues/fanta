/**
 * 
 */
package com.young.studio.fantadb;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author Gabriel Gori
 * 
 */
public class SquadreSimpleCursorAdapter extends SimpleCursorAdapter {

	private Context context;

	public SquadreSimpleCursorAdapter(Context context, Cursor cursor) {
		super(context, R.layout.list_item, cursor, new String[] {
				ISquadreTable.NOME_SQUADRA, ISquadreTable.LEGA }, new int[] {
				R.id.squadra, R.id.lega },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		this.context = context;
	}

	@Override
	public void bindView(View view, final Context context, final Cursor cursor) {
		super.bindView(view, context, cursor);

		ImageView eliminaBtn = (ImageView) view.findViewById(R.id.modificaRosa);
			eliminaBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int id = cursor.getInt(cursor.getColumnIndex(ISquadreTable._ID));
					Toast.makeText(context, String.valueOf(id), Toast.LENGTH_LONG).show();
				}
			});
//		String nomeRegione = cursor.getString(cursor
//				.getColumnIndex(ISquadreTable.NOME_SQUADRA));
//		String nomeImmagine = nomeRegione.toLowerCase().replace(' ', '_')
//				.replace('\'', '_')
//				+ ".png";
//		Bitmap bitmap = readBitmap(nomeImmagine);
//		logo.setImageBitmap(bitmap);
	}

	private Bitmap readBitmap(String nomeImmagine) {
		InputStream is = null;
		try {
			is = context.getAssets().open(nomeImmagine);
			return BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ignored) {
				}
			}
		}
	}

}
