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
//    @GET
//    @Produces("application/json")
//    @Path("/")
//    public Collection<User> getf()
//    {
//        return portfolioService.getUser();
//    }
    @GET
    @Produces("application/json")
    @Path("/{userId}")
    public ImmutableUserDBModel getUserById(@PathParam("userId") String userId)
    {
        return portfolioService.getUser(userId);
    }
    @POST
    @Produces("application/json")
    @Path("/delete/{userId}")
    public void CreateUser(User2 user)
    {
//        return portfolioService.delete(userId);
        portfolioService.createUser(user);
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
//    private User insert(User user)
//    {
//        //Fund Fund = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, Fund.class);
//        List<Fund> funds = Arrays.asList(
//                new Fund("1", "number1","onemoremanager", 3.45, 1.35, 2.01),
//                new Fund("2", "number2","onemoremanager", 4.56, 2.33, 1.98),
//                new Fund("3", "number3","onemoremanager", 4.56, 2.33, 1.98)
//
//        );
//        links l1 = new links();
//        ArrayList<Fund> new_funds = new ArrayList<>();
//

//        user.setAll_funds(new_funds);
//        return null;
//        //return portfolioService.createUser(user2);
//    }
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
//        List<Fund> funds = (Arrays.asList(
//                new Fund("1", "number1","manager", 3.45, 1.35, 2.01),
//                new Fund("2", "number2","anothermanager", 4.56, 2.33, 1.98),
//                new Fund("3", "number3","onemoremanager", 4.56, 2.33, 1.98)
//
//        ));
        List<Fund2> newFunds = new ArrayList<>();
        funds.stream().map(fund -> {

            //double f1 = restTemplate.getForObject(l1.getLink1(), Double.class);

            // Fund fun2 = restTemplate.getForObject("http://localhost:8082/movies/1", Fund.class);
            //double f2 = restTemplate.getForObject(l1.getLink2(), Double.class);
            float f1 = 4.2f;
            float profit = f1-(fund.originalNav().get());
            float profitPercent = ((profit)/(fund.originalNav().get())) * 100;
          //  System.out.println(f1+" "+profit+" "+profitPercent);
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
//
        }).collect(Collectors.toList());

        User2 user = ImmutableUser2.builder()
                    .userId(userId)
                    .all_funds(newFunds)
                    .build();

        portfolioService.createUser(user);

//
//
//        links l1 = new links();
//        User user = new User();
//        user.setUserid(userId);
//        user = insert(user);
        return user.all_funds().get();


    }

}
