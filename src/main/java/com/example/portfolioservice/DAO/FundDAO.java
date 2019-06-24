package com.example.portfolioservice.DAO;


import com.example.portfolioservice.models.Fund;
import com.example.portfolioservice.models.Fund2;
import com.example.portfolioservice.models.ImmutableFund2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class FundDAO
{

    @Autowired
    private FundRepository repository;

    public Collection<Fund> getFunds()
    {
        return repository.findAll();
    }

    public List<ImmutableFund2> getAll(){
       // List<Fund2> funds = repository.findAll().fetchAll().getUnchecked();
        List<ImmutableFund2> f = new ArrayList<>();
//        for (Fund2 fund: funds){
//            f.add(ImmutableFund2.builder().from(fund).build());
//        }
        return f;
    }
}
