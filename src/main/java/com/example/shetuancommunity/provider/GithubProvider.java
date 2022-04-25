package com.example.shetuancommunity.provider;

import com.alibaba.fastjson.JSON;
import com.example.shetuancommunity.dto.AccessTokenDTO;
import com.example.shetuancommunity.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by coder on 2022/4/23
 */

@Component
public class GithubProvider {
  public  String getAccessToken(AccessTokenDTO accessTokenDTO)
  {
      accessTokenDTO.setClient_id("c92e3be9f997d554e11f");
      accessTokenDTO.setClient_secret("15908a7b2a487a94504fc4685d718892dfb38340");
      accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
       MediaType mediaType = MediaType.get("application/json; charset=utf-8");
      OkHttpClient client = new OkHttpClient();
      RequestBody body=RequestBody.Companion.create(JSON.toJSONString(accessTokenDTO),mediaType);
      //RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
      Request request = new Request.Builder()
              .url("https://github.com/login/oauth/access_token")
              .post(body)
              .build();
      try (Response response = client.newCall(request).execute()) {
          String string=response.body().string();
          String token = string.split("&")[0].split("=")[1];
          System.out.println("yes");
          System.out.println(string);
          return token;
      } catch (IOException e) {

      }
          return  null;
  }
  public GithubUser getUser(String accessToken)
  {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
              .url("https://api.github.com/user")
              .header("Authorization","token "+accessToken)
              .build();
      try {
          Response response=client.newCall(request).execute();
          String string=response.body().string();
          GithubUser githubUser =JSON.parseObject(string,GithubUser.class);
          System.out.println(string);
          System.out.println(githubUser.getLogin());

          return  githubUser;
      } catch (IOException e) {

      }
      return  null;
  }

}
