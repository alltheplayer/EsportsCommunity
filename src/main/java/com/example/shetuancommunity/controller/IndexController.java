package com.example.shetuancommunity.controller;


import com.example.shetuancommunity.dto.QuestionDTO;
import com.example.shetuancommunity.mapper.QuestionMapper;
import com.example.shetuancommunity.mapper.UserMapper;
import com.example.shetuancommunity.model.Question;
import com.example.shetuancommunity.model.User;
import com.example.shetuancommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;



    @GetMapping("/")
    public  String index(
            HttpServletRequest request,
            Model model
                           )
    {
        model.addAttribute("question","nihao");
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies)
        {

            if (cookie.getName().equals("token"))
            {
                String token=cookie.getValue();
                User user=userMapper.findByToken(token);
               if(user!=null)
               {
                   request.getSession().setAttribute("user",user);
                   break;
               }

            }
        }

        List<QuestionDTO> questionList=questionService.list();
        model.addAttribute("questions",questionList);

         return  "index";
    }
}
