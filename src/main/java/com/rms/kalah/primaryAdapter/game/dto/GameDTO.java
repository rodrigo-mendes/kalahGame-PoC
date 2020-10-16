package com.rms.kalah.primaryAdapter.game.dto;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

public final class GameDTO {

    private String url;
    private Integer gameId;

    public GameDTO(Integer gameId, String servername, String port){
        this.gameId = gameId;
        this.url = "http://"
                +servername
                + ":"
                + port
                + "/"
                + gameId;
    }

    public GameDTO(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public MultiValueMap<String, String> toMultiValueMap(){
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("gameId", getGameId().toString());
        map.add("url", getUrl());
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDTO gameDTO = (GameDTO) o;
        return url.equals(gameDTO.url) &&
                gameId.equals(gameDTO.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, gameId);
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "url='" + url + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}
