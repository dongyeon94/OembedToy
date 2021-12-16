package com.root.oEmbed.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;
import com.root.oEmbed.domain.Oembed;
import com.root.oEmbed.domain.SNS;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

@Service
public class OembedService {

//https://www.youtube.com/watch?v=dBD54EZIrZo
//https://www.instagram.com/p/BUawPlPF_Rx/
//https://twitter.com/hellopolicy/status/867177144815804416
//https://vimeo.com/20097015


    final private String END_PARAMETER = "&format=json";

    private String tt = "http://flickr.com/services/oembed?url=http://flickr.com/photos/bees/2362225867/&format=json";


    public String oemRequset(String url) throws IOException {

        CloseableHttpClient hc = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(selectSnsType(url).getPrefix()+url+END_PARAMETER);
        httpGet.addHeader("Content-Type", "application/json");        
        
        CloseableHttpResponse httpResponse = hc.execute(httpGet);
        
        String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        
        JsonObject document = new Gson().fromJson(result, JsonObject.class);

        System.out.println(document);
        return  document.toString();
    }

    public Oembed convertOembedData(JsonObject jObject) {
        Oembed oembed = new Oembed();
        // jObject.get()
        oembed.setTitle(jObject.get("title").getAsString());
        return oembed;
    }

    public SNS selectSnsType(String url){    
        if (url.contains("youtube")) {
            return SNS.YOUTUBE;
        }
        else if (url.contains("vimeo")) {
            return SNS.VIMEO;
        }
        else if (url.contains("twitter")) {
            return SNS.TWITTER;
        }
        else if (url.contains("instagram")) {
            return SNS.INSTAGRAM;
        }
        return null;
    }
}
