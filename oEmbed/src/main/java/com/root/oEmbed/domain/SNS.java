package com.root.oEmbed.domain;

import lombok.Getter;

@Getter
public enum SNS {
    YOUTUBE("https://www.youtube.com/oembed?url="), 
    TWITTER("https://publish.twitter.com/oembed?url="), 
    VIMEO("https://vimeo.com/api/oembed.json?url="), 
    INSTAGRAM("https://www.instagram.com/p/BUawPlPF_Rx/"),;

    private final String prefix;

    SNS(String prefix) {
        this.prefix = prefix;
    }
}
