package com.example.adrianantonescu.qa.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReadJson {
    public static Specializare read(String JSON) throws JSONException {
        if(JSON==null)
            return null;
        JSONObject obj=new JSONObject(JSON);
        List<Colectiv> informaticaEconomica;
        Colectiv ciberneticaEconomica, statistica;
        ciberneticaEconomica=getColectivType(obj.getJSONObject("ciberneticaEconomica"));
        statistica=getColectivType(obj.getJSONObject("statistica"));
        informaticaEconomica=getListofColectivType(obj.getJSONArray("informaticaEconomica"));
        return new Specializare(informaticaEconomica,ciberneticaEconomica,statistica);
    }

    private static Colectiv getColectivType(JSONObject object) throws JSONException {
        if(object==null)
            return null;
        String subject=object.getString("subject");
        Integer group=object.getInt("group");
        String series=object.getString("series");
        Integer year=object.getInt("year");
        List<Stud> students=new ArrayList<>();
        JSONArray studs=object.getJSONArray("students");
        if(studs!=null){
            for(int i=0;i<studs.length();i++){
                String fname=studs.getJSONObject(i).getString("fname");
                String lname=studs.getJSONObject(i).getString("lname");
                Float result=(float)studs.getJSONObject(i).getDouble("result");
                students.add(new Stud(fname,lname,result));
            }
        }
        return new Colectiv(subject,group,series,year,students);
    }
    private static List<Colectiv> getListofColectivType(JSONArray array) throws JSONException {
        if(array==null)
            return null;
        List<Colectiv> list=new ArrayList<>();
        for(int i=0;i<array.length();i++){
            Colectiv colectiv=getColectivType(array.getJSONObject(i));
            if(colectiv!=null)
                list.add(colectiv);
        }
        return list;
    }
}
