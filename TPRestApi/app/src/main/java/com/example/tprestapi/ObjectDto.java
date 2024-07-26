package com.example.tprestapi;

import com.google.gson.annotations.SerializedName;

public class ObjectDto {
    @SerializedName("albumId")
     public int albumId;
    @SerializedName("id")

    public int id;
    @SerializedName("title")

    public String title;
    @SerializedName("url")

    public String url;
    @SerializedName("thumbnailUrl")

    public String thumbnailUrl;


    public ObjectDto(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
}
