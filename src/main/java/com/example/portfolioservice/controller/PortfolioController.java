package com.example.portfolioservice.controller;


import com.example.portfolioservice.models.*;

import com.example.portfolioservice.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.base.Optional;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.client.RestTemplate;

import javax.ws.rs.*;

import static org.reflections.util.ConfigurationBuilder.build;

@Component
@Path("/portfolio")
public class PortfolioController
{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PortfolioService portfolioService;

    @GET
    @Produces("application/json")
    @Path("/{userId}")
    public ImmutableUserDBModel getUserById(@PathParam("userId") String userId)
    {
        return portfolioService.getUser(userId);
    }

//    @POST
//    @Produces("application/json")
//    @Path("/delete/{userId}")
//    public void CreateUser(User2 user)
//    {
////        return portfolioService.delete(userId);
//        portfolioService.createUser(user);
//    }
    @GET
    @Produces("application/json")
    @Path("/getBalance/{userId}")
    public float getBalanceById(@PathParam("userId") String userId)
    {
        return portfolioService.getBalanceById(userId);

    }
    @PATCH
    @Produces("application/json")
    @Path("/update/{userId}/{balance}")
    public void updateBalance(@PathParam("userId") String userId, @PathParam("balance") float balance)
    {
        portfolioService.updateBalance(userId, balance);
    }
    @DELETE
    @Produces("application/json")
    @Path("/delete/{userId}")
    public Optional<UserDBModel> deleteUserById(@PathParam("userId") String userId)
    {
        return portfolioService.delete(userId);
    }
    @PATCH
    @Produces("application/json")
    @Path("/update/{userId}")
    public Optional<UserDBModel> updateUserById(User2 user)
    {
        return portfolioService.update(user);
    }

    @POST
    @Produces("application/json")
    @Path("/addUser/{userId}/{balance}")
    public void addUser(@PathParam("userId") String userId, @PathParam("balance") float balance)
    {
        List<Fund2> empty_funds = new ArrayList<>();
        User2 user = ImmutableUser2.builder()
                .userId(userId)
                .all_funds(empty_funds)
                .balance((float)(balance))
                .build();
        portfolioService.createUser(user);
    }

    @GET
    @Produces("application/json")
    @Path("/getFunds/{userId}")
    //
    public List<Fund2> getFunds(@PathParam("userId") String userId)
    {

        ImmutableUserDBModel u = portfolioService.getUser(userId);

        if(u!=null)
        {
            return u.all_funds();

        }

        //Fund Fund = restTemplate.getForObject("http://funds-service/user/" + userId, Fund.class);
        Fund2 fun = ImmutableFund2.builder()
                .fundNumber("1")
                .fundName("number1")
                .invManager("manager")
                .originalNav((float)(3.45))
                .sAndPRating((float)(1.35))
                .moodysRating((float)(2.01))
                .invCurrency("Rupee")
                .setCycle(3)
                .build();
        List<Fund2> funds = new ArrayList<>();
        funds.add(fun);

        List<Fund2> newFunds = new ArrayList<>();
        funds.stream().map(fund -> {

            //double f1 = restTemplate.getForObject(l1.getLink1(), Double.class);

            // Fund fun2 = restTemplate.getForObject("http://localhost:8082/movies/1", Fund.class);
            //double f2 = restTemplate.getForObject(l1.getLink2(), Double.class);
            float f1 = 4.2f;
            float profit = f1-(fund.originalNav().get());
            float profitPercent = ((profit)/(fund.originalNav().get())) * 100;
            Fund2 fun2 = ImmutableFund2.builder()
                    .fundNumber(fund.fundNumber())
                    .fundName(fund.fundName())
                    .invManager(fund.invManager())
                    .originalNav(fund.originalNav())
                    .sAndPRating(fund.sAndPRating())
                    .moodysRating(fund.moodysRating())
                    .invCurrency(fund.invCurrency())
                    .setCycle(fund.setCycle())
                    .presentNav((float)(4.2))
                    .profitAmount(profit)
                    .profitPercent(profitPercent)
                    .build();
            newFunds.add(fun2);
            return fun2;

        }).collect(Collectors.toList());

        User2 user = ImmutableUser2.builder()
                    .userId(userId)
                    .all_funds(newFunds)
                    .balance((float)(10000))
                    .build();

        portfolioService.createUser(user);

        return user.all_funds().get();

    }

}
