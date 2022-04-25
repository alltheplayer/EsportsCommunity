package com.example.shetuancommunity.controller;


import com.example.shetuancommunity.dto.AccessTokenDTO;
import com.example.shetuancommunity.dto.GithubUser;
import com.example.shetuancommunity.mapper.UserMapper;
import com.example.shetuancommunity.model.User;
import com.example.shetuancommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Cteted by coder on 2022/4/23
 */
@Controller
public class AuthorizeController {

    private  final  GithubProvider githubProvider;
    private  final  UserMapper userMapper;

    public AuthorizeController(GithubProvider githubProvider,UserMapper userMapper)
    {
        this.githubProvider=githubProvider;
        this.userMapper=userMapper;
    }




    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state,
                            HttpServletRequest request,
                            HttpServletResponse response)
    {

        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode((code));
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        if(githubUser!=null)
        {
            User user=new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getLogin());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtModified());
            userMapper.insert(user);
            //登录成功，写cookie和session
            response.addCookie(new Cookie("token",token));
            request.getSession().setAttribute("user",user);//此处传入user

            return "redirect:/";
            //登录成功
        }else
        {

        }
        return "redirect:/";


    }

}
