package com.company.Domain.Observer;

import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Utility.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class GunObserver {
    //Observer class implement this class to have it as the observed class

    ArrayList<IGunListener> subscribers; // array holds in subscribed elements

    public GunObserver(){
        subscribers = new ArrayList<>();
    }

    public void addListener(IGunListener listener){ // use to add new listeners
        subscribers.add(listener);
    }

    public void gunMovedEvent(Coordinate coordinate, int angle, Atom atom, PowerUp powerUp){ // use to fire action performed method of all the subscribers
        for(IGunListener listener : subscribers){
            listener.gunMoved(coordinate, angle, atom, powerUp);
        }
    }
}
