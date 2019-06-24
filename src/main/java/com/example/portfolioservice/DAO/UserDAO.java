package com.example.portfolioservice.DAO;

import java.util.*;
import com.google.common.base.Optional;
//import java.util.Optional;
import com.example.portfolioservice.models.*;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.immutables.mongo.repository.RepositorySetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import static org.reflections.util.ConfigurationBuilder.build;


public class UserDAO
{

    private UserDBModelRepository repository;
    private UserDBModelRepository.Criteria where;



    public UserDAO()
    {
        repository =new UserDBModelRepository(RepositorySetup.forUri("mongodb://localhost:27017/portfolio"));
        where = repository.criteria();
    }


    public void createUser(User2 user)
    {
        UserDBModel userDB;
        userDB = ImmutableUserDBModel.builder()
                .userId(user.userId())
                .all_funds(user.all_funds().get()).build();

        repository.insert(userDB);

    }

    public ImmutableUserDBModel getUser(String id)
    {
        Optional<UserDBModel> user =repository.find(where.userId(id)).fetchFirst().getUnchecked();
        if(user.isPresent())
        {
            ImmutableUserDBModel userd =ImmutableUserDBModel.builder().from(user.get()).build();
            return userd;
        }
        return null;
    }

    public Optional<UserDBModel> update(User2 user)
    {
        Optional<UserDBModel> u = repository.find(where.userId(user.userId())).fetchFirst().getUnchecked();
        if(u.isPresent())
        {
            UserDBModel updated_user = u.get();
            repository.upsert(
                    ImmutableUserDBModel.builder()
                    .userId(user.userId())
                    .all_funds(user.all_funds().isPresent()?user.all_funds().get():updated_user.all_funds())
                    .build()
            );
        }
        return u;
    }

    public Optional<UserDBModel> delete(String userId)
    {
        Optional<UserDBModel> u = repository.findByUserId(userId).deleteFirst().getUnchecked();
        return u;
    }

//    public User createUser(User user)
//    {
//        userRepository.deleteAll();
//       // userRepository.insert(user);
//        return user;
//    }
//    public Optional<User> getUserById(String id)
//    {
//        //userRepository.deleteAll();
//        return userRepository.findById(id);
//    }
//    public Optional<User> deleteUserById(String id)
//    {
//        //userRepository.deleteAll();
//        Optional<User> user = userRepository.findById(id);
//        user.ifPresent(u -> userRepository.delete(u));
//        return user;
//    }
//
//    public Optional<User> updateUser(String userId, UserUpdate userUpdate)
//    {
//        Optional<User> user = userRepository.findById(userId);
//        user.ifPresent(u -> u.setAll_funds(userUpdate.getAll_funds()));
//        user.ifPresent(u -> userRepository.save(u));
//        return user;
//    }
}
