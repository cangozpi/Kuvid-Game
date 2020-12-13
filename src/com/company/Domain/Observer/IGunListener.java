package com.company.Domain.Observer;

import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Utility.Coordinate;

import java.util.List;


public interface IGunListener {
    void gunMoved(Coordinate coord, int angle, Atom atom, PowerUp powerUp);

}
