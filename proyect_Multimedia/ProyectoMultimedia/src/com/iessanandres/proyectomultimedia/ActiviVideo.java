package com.iessanandres.proyectomultimedia;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ActiviVideo extends ListActivity {

	           
	SimpleCursorAdapter adapter;
 	final Uri mediaSrc = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//coger los titulos de los videos 
		String[] titulos = { MediaStore.MediaColumns.TITLE };
		int[] to = { android.R.id.text1 };

		Cursor cursor = managedQuery(mediaSrc, null, null, null,
				MediaStore.Audio.Media.TITLE);

		adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, titulos, to);
		setListAdapter(adapter);
	}

		
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Cursor cursor = adapter.getCursor();
		cursor.moveToPosition(position);

		String num = cursor.getString(cursor
				.getColumnIndex(MediaStore.Audio.Media._ID));

		Uri playableUri = Uri.withAppendedPath(mediaSrc, num);

		Toast.makeText(this, "Play: " + playableUri.toString(),
				Toast.LENGTH_SHORT).show();

		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(playableUri);
		startActivity(intent);
	}

}