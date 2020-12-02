package com.company.Domain.Models;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.ReactionBlockerType;

import java.util.Map;

public class BadAlienFactory {
    //Singleton Pattern
    private static BadAlienFactory instance;

    //variables
    Map<ReactionBlockerType, Integer> reactionBlockerAmount;

    private BadAlienFactory(){

    }

    public static BadAlienFactory getInstance(){
        instance =  instance == null ?  new BadAlienFactory() :  instance;
        return instance;
    }

    //decrement reactionBlockerAmount by one
    public void sendReactionBlocker(ReactionBlockerType type, Coordinate position){
        reactionBlockerAmount.put(type, reactionBlockerAmount.get(type) - 1);
        //TODO: implement something that has to do with position
    }

    //setters
    public void setReactionBlockerAmount(Map<ReactionBlockerType, Integer> reactionBlockerAmount) {
        this.reactionBlockerAmount = reactionBlockerAmount;
    }

}