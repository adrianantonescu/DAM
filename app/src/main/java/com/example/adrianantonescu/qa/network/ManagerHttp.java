package com.example.adrianantonescu.qa.network;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ManagerHttp extends AsyncTask<URL,Void,String> {
    private URL url;
    private HttpURLConnection conexiune;
    private InputStream inStream;
    private InputStreamReader inStreamReader;
    private BufferedReader bf;

    @Override
    protected String doInBackground(URL... urls) {
        StringBuilder sb=new StringBuilder();
        try {
            url=urls[0];
            conexiune= (HttpURLConnection) url.openConnection();
            inStream=conexiune.getInputStream();
            inStreamReader=new InputStreamReader(inStream);
            bf=new BufferedReader(inStreamReader);
            String linie;
            while((linie=bf.readLine())!=null)
                sb.append(linie);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(bf!=null){
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inStreamReader!=null){
                try {
                    inStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inStream!=null){
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conexiune!=null){
                conexiune.disconnect();
            }
        }
        return sb.toString();
    }
}
