package com.root.oEmbed.controller;

import com.root.oEmbed.service.OembedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class OembedController {

    final private OembedService oembedService;

    @GetMapping("/test")
    @ResponseBody
    public  String urlRequest(String url) throws IOException {
        return oembedService.oemRequset(url);
    }
}
