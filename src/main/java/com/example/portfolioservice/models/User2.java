package com.example.portfolioservice.models;
import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.mongo.Mongo;
import org.immutables.value.Value;
import com.example.portfolioservice.models.Fund;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Value.Immutable
@JsonSerialize(as = ImmutableUser2.class)
@JsonDeserialize(as = ImmutableUser2.class)
public interface User2
{
    String userId();
    Optional<List<Fund2>> all_funds();
}
