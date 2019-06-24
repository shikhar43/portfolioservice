package com.example.portfolioservice.service;


import java.util.*;

import com.example.portfolioservice.DAO.FundDAO;
import com.example.portfolioservice.DAO.UserDAO;
import com.example.portfolioservice.models.*;
import com.google.common.base.Optional;
import org.immutables.mongo.repository.RepositorySetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//
@Service
public class PortfolioService
{
//    @Autowired
//    private FundDAO fundDAO;

    @Autowired
    UserDAO userDAO;

//    public Collection<Fund> getFunds()
//    {
//        return fundDAO.getFunds();
//    }
//    public List<ImmutableFund2> getAll(){
//        return fundDAO.getAll();
//    }




//    public User createUser(User user)
//    {
//         userDAO.createUser(user);
//         return user;
//    }

    public void createUser(User2 user)
    {
        userDAO.createUser(user);
    }

    public ImmutableUserDBModel getUser(String id)
    {
        return userDAO.getUser(id);
    }

    public Optional<UserDBModel> update(User2 user)
    {
        return userDAO.update(user);
    }

    public Optional<UserDBModel> delete(String userId)
    {
        return userDAO.delete(userId);
    }

//    public Optional<User> getUserById(String id)
//    {
//        return userDAO.getUserById(id);
//    }
//
//    public Optional<User> deleteUserById(String id)
//    {
//        return userDAO.deleteUserById(id);
//    }
//
//    public Optional<User> updateUserById(String userId, UserUpdate userUpdate)
//    {
//        return userDAO.updateUser(userId, userUpdate);
//    }
}
