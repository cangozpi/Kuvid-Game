package com.company.UI.Objects.Observer;

import com.company.Domain.Models.Projectile.Projectile;

import java.util.List;


public interface IGameListener {
    void positionChanged(List<Projectile> objectList);

}
