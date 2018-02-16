package com.example.dm2.lista_rss;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView not;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        not=(TextView) findViewById(R.id.txtnoticia);

        //carga del xml mediante la tarea asincrona
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://www.europa.es/rss/rss.aspx");

    }

    private   class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        List<Noticia> noticias;

        protected Boolean doInBackground (String... params){
            RssParserDom domparser=new RssParserDom(params[0]);
            noticias=domparser.parse();
            return true;
        }

        protected void onPostExecute (Boolean result){

            //tratamos la lista de noticias
            //por ejemplo:los titulos en pantalla
            not.setText("");
            for (int i=0;i<noticias.size();i++){
                not.setText(not.getText().toString()+System.getProperty("line.separator")+noticias.get(i).getTitulo());
            }
        }
    }




}
