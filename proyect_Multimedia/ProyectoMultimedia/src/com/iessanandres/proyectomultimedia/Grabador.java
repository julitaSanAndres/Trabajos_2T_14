package com.iessanandres.proyectomultimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Grabador extends Activity implements TextToSpeech.OnInitListener {
	private Button grabar;
	private Button repro;
	 int peticion=2;
	    Uri url1;
	    Intent intent=null;
	    private TextToSpeech voz;
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabador);
        
        grabar=(Button)findViewById(R.id.boton1);
        
        repro=(Button)findViewById(R.id.boton2);
        voz = new TextToSpeech(this, this);
    }

  
    
   /**
    * 
    * mediante el Intent activamos la
    *  aplicación de grabación propia de Android.
Seguidamente llamamos al método startActivityForResult para 
poder recuperar la grabación luego de finalizada a través del método onActivityResult:
lo de la peticion es que hay que pasarle un valor positivo mayor que 0
    */
    public void grabar(View v){
    	 intent =   new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
         startActivityForResult(intent, peticion);
    	
    }
  /**
   * para poder reproducir el audio utilizamos la clase mediaplayer y hacemos que funcione mediante
   * el start
   */
    public void repoducir(View v){
    	if(url1==null){
    		voz.speak("no hay grabación", TextToSpeech.QUEUE_FLUSH, null);
    		Toast.makeText(this,"no hay grabación",Toast.LENGTH_SHORT).show();
    	}else{
    	 MediaPlayer mediaPlayer = MediaPlayer.create(this, url1);
         mediaPlayer.start(); 
    	}
    }
    
    /**
     * esto es enla ruta a guardar la el audio y depues reproducirlo mediante la uri que se le dara al Mediaplayer
     */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == peticion) {
        url1 = data.getData();
        }
    }



	//@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}
    
}
