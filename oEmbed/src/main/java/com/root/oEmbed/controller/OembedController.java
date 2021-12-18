package com.root.oEmbed.controller;

import com.root.oEmbed.domain.Oembed;
import com.root.oEmbed.service.OembedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class OembedController {

    final private OembedService oembedService;

    @GetMapping("/serach")
    public String urlRequest(String url, Model model) throws IOException {
        try {
            Oembed oembed = oembedService.oemRequset(url);
            model.addAttribute("oembed", oembed);
        }
        catch (Exception e){
            model.addAttribute("error", "지원하지 않는 URL입니다.");
        }
        return "index";
    }


}
