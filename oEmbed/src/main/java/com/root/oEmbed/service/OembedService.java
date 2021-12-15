package com.root.oEmbed.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class OembedService {

//https://www.youtube.com/watch?v=dBD54EZIrZo
//https://www.instagram.com/p/BUawPlPF_Rx/
//https://twitter.com/hellopolicy/status/867177144815804416
//https://vimeo.com/20097015

    private String YOUTUBE = "https://www.youtube.com/oembed?url=";
    private String TWITTER = "https://publish.twitter.com/oembed?url=";
    private String VIMEO = "https://vimeo.com/api/oembed.json?url=";
    private String INSTAGRAM = "https://www.instagram.com/p/BUawPlPF_Rx/";

    final private String END_PARAMETER = "&format=json";

    private String tt = "http://flickr.com/services/oembed?url=http://flickr.com/photos/bees/2362225867/&format=json";
    public String oemRequset(String url) throws IOException {
        CloseableHttpClient hc = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(YOUTUBE+url+END_PARAMETER);
        httpGet.addHeader("Content-Type", "application/json");
        CloseableHttpResponse httpResponse = hc.execute(httpGet);
        String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        System.out.println(result);
        return  result;
    }
}
