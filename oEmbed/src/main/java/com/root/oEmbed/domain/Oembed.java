package com.root.oEmbed.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Oembed {
    private String title;
    private String authorName;
    private String authorUrl;
    private String type;
    private String height;
    private String width;
    private String version;
    private String providerName;
    private String providerUrl;
    private String thumbnailHeight;
    private String thumbnailWidth;
    private String thumbnailUrl;
    private String htmlTag;
}
