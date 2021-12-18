package com.root.oEmbed.service;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;
import com.root.oEmbed.domain.Oembed;
import com.root.oEmbed.domain.SNS;
import com.google.gson.Gson;
import org.springframework.validation.Errors;

import java.io.IOException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class OembedService {

    final private String END_PARAMETER = "&format=json";

    public Oembed oemRequset(String url) throws IOException {

        CloseableHttpClient hc = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(selectSnsType(url).getPrefix() + url + END_PARAMETER);
        httpGet.addHeader("Content-Type", "application/json");

        CloseableHttpResponse httpResponse = hc.execute(httpGet);

        String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        if (result == null) {
            throw new IOException();
        }
        JsonObject document = new Gson().fromJson(result, JsonObject.class);
        Oembed oembed = convertOembedData(document);
        return oembed;
    }

    public Oembed convertOembedData(JsonObject jObject) {

        Oembed oembed = new Oembed();
        for (String key : jObject.keySet()) {
            try {
                if (key.equals("title")) oembed.setTitle(jObject.get("title").getAsString());
                else if (key.equals("type")) oembed.setType(jObject.get("type").getAsString());
                else if (key.equals("provider_name")) oembed.setProviderName(jObject.get("provider_name").getAsString());
                else if (key.equals("provider_url")) oembed.setProviderUrl(jObject.get("provider_url").getAsString());
                else if (key.equals("author_name")) oembed.setAuthorName(jObject.get("author_name").getAsString());
                else if (key.equals("author_url")) oembed.setAuthorUrl(jObject.get("author_url").getAsString());
                else if (key.equals("is_plus")) oembed.setIsPlus(jObject.get("is_plus").getAsString());
                else if (key.equals("html")) oembed.setHtmlTag(jObject.get("html").getAsString().replace("\\", ""));
                else if (key.equals("width")) oembed.setWidth(jObject.get("width").getAsString());
                else if (key.equals("height")) oembed.setHeight(jObject.get("height").getAsString());
                else if (key.equals("duration")) oembed.setDuration(jObject.get("duration").getAsString());
                else if (key.equals("description")) oembed.setDescription(jObject.get("description").getAsString());
                else if (key.equals("thumbnail_url")) oembed.setThumbnailUrl(jObject.get("thumbnail_url").getAsString());
                else if (key.equals("thumbnail_width")) oembed.setThumbnailWidth(jObject.get("thumbnail_width").getAsString());
                else if (key.equals("thumbnail_height")) oembed.setThumbnailHeight(jObject.get("thumbnail_height").getAsString());
                else if (key.equals("thumbnail_url_with_play_button")) oembed.setThumbnailUrlPlay(jObject.get("thumbnail_url_with_play_button").getAsString());
                else if (key.equals("video_id")) oembed.setVideoId(jObject.get("video_id").getAsString());
                else if (key.equals("upload_date")) oembed.setUploadDate(jObject.get("upload_date").getAsString());
                else if (key.equals("uri")) oembed.setUri(jObject.get("uri").getAsString());
            } catch (Exception e) {
                System.out.println("[" + key + "] Error " + e);
            }
        }

        return oembed;
    }


    public SNS selectSnsType(String url) {
        if (url.contains("youtube")) {
            return SNS.YOUTUBE;
        } else if (url.contains("vimeo")) {
            return SNS.VIMEO;
        } else if (url.contains("twitter")) {
            return SNS.TWITTER;
        } else if (url.contains("instagram")) {
            return SNS.INSTAGRAM;
        }
        return null;
    }
}