package com.example.shetuancommunity.service;


import com.example.shetuancommunity.dto.PaginationDTO;
import com.example.shetuancommunity.dto.QuestionDTO;
import com.example.shetuancommunity.mapper.QuestionMapper;
import com.example.shetuancommunity.mapper.UserMapper;
import com.example.shetuancommunity.model.Question;
import com.example.shetuancommunity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        //size*(page-1)
        Integer offset=size*(page-1);
        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        PaginationDTO paginationDTO=new PaginationDTO();
        for(Question question:questions){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        Integer totalCount= questionMapper.count();
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount,page,size);
        return  paginationDTO;
    }

    public QuestionDTO getById(Integer id)
    {
        Question question= questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;

    }


    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else
        {
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }

    public void incView(Integer id) {

        questionMapper.updateView(id);
    }
}
