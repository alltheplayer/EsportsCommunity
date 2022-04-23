package com.example.shetuancommunity.controller;


import com.example.shetuancommunity.dto.AccessTokenDTO;
import com.example.shetuancommunity.dto.GithubUser;
import com.example.shetuancommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Cteted by coder on 2022/4/23
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state)
    {

        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id("c92e3be9f997d554e11f");
        accessTokenDTO.setClient_secret("15908a7b2a487a94504fc4685d718892dfb38340");
        accessTokenDTO.setCode((code));
        accessTokenDTO.setRedirect_uri("http://localhost:8088/callback");
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user=githubProvider.getUser(accessToken);


        return  "index";
    }

}
