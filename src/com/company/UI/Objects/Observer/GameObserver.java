package com.company.UI.Objects.Observer;

import com.company.Domain.Models.Projectile.*;

import java.util.ArrayList;
import java.util.List;

public class GameObserver {
    //Observer class implement this class to have it as the observed class

    ArrayList<IGameListener> subscribers; // array holds in subscribed elements

    public GameObserver(){
        subscribers = new ArrayList<>();
    }

    public void addListener(IGameListener listener){ // use to add new listeners
        subscribers.add(listener);
    }

    public void positionUpdateEvent(List<Molecule> moleculeList,List<Atom> atomList,  List<PowerUp> shotPowerUpList,  List<ReactionBlocker> reactionBlockerList, List<PowerUp> powerUpList){ // use to fire action performed method of all the subscribers
        for(IGameListener listener : subscribers){
            listener.positionChanged(moleculeList, atomList, shotPowerUpList,reactionBlockerList, powerUpList);
        }
    }
}
