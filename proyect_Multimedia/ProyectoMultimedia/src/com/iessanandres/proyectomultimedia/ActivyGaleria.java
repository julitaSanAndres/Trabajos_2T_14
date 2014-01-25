package com.iessanandres.proyectomultimedia;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ActivyGaleria extends Activity implements OnTouchListener {

	public int code = 1;
	private Intent intent;
	private ImageView iv;

	private static final String TAG = "Touch";

	/**
	 * Estas matrices se utilizan para mover y ampliar imagen tines que importat
	 * // la grafic sino no funciona tenemos que declarar dos matrices como
	 * variables (una para el valor de la corriente y otro para el valor inicial
	 * antes de la transformación)
	 */
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();

	// Se puede estar en uno de estos 3 estados cuando estemos utilizando la
	// matrix
	static final int NINGUNO = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = 0;

	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visor);
		iv = (ImageView) findViewById(R.id.imageView1);
		iv.setOnTouchListener(this);
		galeria();
	}

	/**
	 * para ir galería necesitamos un intent ya que utilizaremos la galeria del
	 * dispositivo y código distintos que asignamos en nuestro caso un 1 simpre
	 * tienen que ser mayor que 0
	 */
	private void galeria() {
		intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

		startActivityForResult(intent, code); // mandarlo la galeria de imágenes

	}

	/**
	 * 
	 * Actividades que devuelven resultados la activida padre sobrescribira
	 * onActivityResult(int requestCode, int resultCode, Intent data)
	 * 
	 * recibimos el URI de la imágen y construimos un Bitmap a partir de un
	 * stream de bytes por eso utilizamos en InputStream que seria para la
	 * apertura del canal de comunicación el BufferedInputStream nuevo,
	 * proporcionando la posibilidad de leer bytes. el paso de una fuente null
	 * crea un BufferedInputStream cerrado. Todas las operaciones de lectura en
	 * tal corriente se producirá una IOException. en caso de que ocurra la
	 * exception llamara el método salir que nos devolvera a la main principal
	 */

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Uri selectedImage = null;
		InputStream is;

		selectedImage = data.getData();

		if (resultCode != RESULT_CANCELED) {
			if (selectedImage != null) {
				try {
					is = getContentResolver().openInputStream(selectedImage);
					BufferedInputStream bis = new BufferedInputStream(is);
					Bitmap bitmap = BitmapFactory.decodeStream(bis);

					iv.setImageBitmap(bitmap);

				} catch (FileNotFoundException e) {
					Toast.makeText(this, "tienes que elegir una imagen",
							Toast.LENGTH_SHORT).show();

				}
			} else {
				finish();
			}
		} else {
			Toast.makeText(this, "galeria cancelada", Toast.LENGTH_SHORT)
					.show();
		}

	}

//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		iv = (ImageView) v;

		// Descarga evento táctil para iniciar
		dumpEvent(event);
		// Procesar eventos de toque aquí
		/**
		 * Cuando se inicia el gesto recordamos el valor actual de la matriz de
		 * transformación y la posición inicial del puntero. Cada vez que el
		 * dedo se mueve, se inicia la matriz de transformación a lo largo de su
		 * valor original .Un gesto de arrastre empieza cuando el primer dedo se
		 * presiona a la pantalla (ACTION_DOWN) y termina cuando se retira (o
		 * ACTION_UP ACTION_POINTER_UP).
		 */
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			Log.d(TAG, "mode=DRAG");
			mode = DRAG;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			mode = NINGUNO;
			Log.d(TAG, "mode=NINGUNO");
			break;
		 
			
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			Log.d(TAG, "oldDist=" + oldDist);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode = ZOOM;
				Log.d(TAG, "mode=ZOOM");
			}
			break;

		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) {
				 matrix.set(savedMatrix);
				 matrix.postTranslate(event.getX() - start.x, event.getY()
				 - start.y);
			} else if (mode == ZOOM) {
				float newDist = spacing(event);
				Log.d(TAG, "newDist=" + newDist);
				if (newDist > 10f) {
					matrix.set(savedMatrix);
					float scale = newDist / oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
			}
			break;

		}

		//
		/**
		 * Realizar la transformación // La variable de matriz se calcula dentro
		 * de la sentencia switch cuando // ponemos en práctica los gestos. es
		 * decir la imagen cambian cuando la tocamos
		 */
		iv.setImageMatrix(matrix);

		return true;// indica evento estuvo a cargo
	}

	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);

	}

	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	// ** Muestra un evento en la vista Logcat, para la depuración aqui es de
	// donde se toma del movil o table la posición donde se a pulsado* /

	private void dumpEvent(MotionEvent event) {
		String[] nombre = { "ABAJO", "UP", "movimiento", "CANCELAR", "fuera",
				"POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };

		StringBuilder sb = new StringBuilder();
		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;
		sb.append("event ACTION_").append(nombre[actionCode]);
		if (actionCode == MotionEvent.ACTION_POINTER_DOWN
				|| actionCode == MotionEvent.ACTION_POINTER_UP) {
			sb.append("(pid ").append(
					action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
			sb.append(")");
		}
		sb.append("[");
		for (int i = 0; i < event.getPointerCount(); i++) {
			sb.append("#").append(i);
			sb.append("(pid ").append(event.getPointerId(i));
			sb.append(")=").append((int) event.getX(i));
			sb.append(",").append((int) event.getY(i));
			if (i + 1 < event.getPointerCount())
				sb.append(";");
		}
		sb.append("]");
		Log.d(TAG, sb.toString());
	}

}
