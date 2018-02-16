package com.example.dm2.lista_rss;

import android.provider.DocumentsContract;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dm2 on 20/12/2017.
 */

public class RssParserDom {

    private URL rssUrl;

    public RssParserDom (String url){

        try {
            this.rssUrl=new URL(url);
        }catch (MalformedURLException e){
            throw  new RuntimeException(e);
        }
    }

    public List<Noticia> parse(){
        //instanciamos la fabrica para DOM
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        List<Noticia> noticias=new ArrayList<Noticia>();

        try{
            //creamos un nuevo parser DOM
            DocumentBuilder builder=factory.newDocumentBuilder();

            //reaizamos la lectura completa del XML
            Document dom=builder.parse(this.getInputStream());

            //nos posicionamos en el nodo princiapl del arbol (<rss>)
            Element root = dom.getDocumentElement();

            //localizamos todos los elementos <item>
            NodeList items=root.getElementsByTagName("item");

            //recorremos la lista de noticias
            for (int i=0;i<items.getLength();i++){
                Noticia noticia= new Noticia();

                //obtenemos la noticia actual
                Node item= items.item(i);

                //obtenemos la lista de datos de la noticia actual
                NodeList datosNoticia= item.getChildNodes();

                //procesamos cada datos de la noticia
                for (int j=0;j<datosNoticia.getLength();j++){
                    Node dato= datosNoticia.item(j);
                    String etiqueta=dato.getNodeName();

                    if (etiqueta.equals("title")){
                        String texto=dato.getFirstChild().getNodeValue();
                        noticia.setLink(texto);
                        //noticia.setlink(dato.getFirstChild().getNodeValue());
                    }else
                        if(etiqueta.equals("link")){
                            noticia.setLink(dato.getFirstChild().getNodeValue());
                        }
                        else
                            if (etiqueta.equals("guild")){
                                noticia.setGuild(dato.getFirstChild().getNodeValue());
                            }
                            else if (etiqueta.equals("pubDate")){
                                noticia.setFecha(dato.getFirstChild().getNodeValue());
                            }
                }
            }
        } catch (Exception ex){
            throw  new RuntimeException(ex);
        }
        return noticias;
    }

    public String obtenerTexto(Node dato){
        StringBuilder texto=new StringBuilder();
        NodeList fragmentos=dato.getChildNodes();

        for (int k=0;k<fragmentos.getLength();k++){
            texto.append(fragmentos.item(k).getNodeValue());

        }
        return texto.toString();
    }

    private InputStream getInputStream(){
        try{
            return rssUrl.openConnection().getInputStream();
        }catch (IOException e){
            throw  new RuntimeException(e);
        }

    }
}
