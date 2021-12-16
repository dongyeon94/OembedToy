package com.root.oEmbed.controller;

import com.root.oEmbed.domain.Oembed;
import com.root.oEmbed.service.OembedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class OembedController {

    final private OembedService oembedService;

    @GetMapping("/test")
    public String urlRequest(String url, Model model) throws IOException {
        Oembed oembed = oembedService.oemRequset(url);
        model.addAttribute("oembed" ,oembed);

        return "index";
    }


}
