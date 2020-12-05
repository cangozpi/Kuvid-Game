package com.company.Domain.Models;

public class Blender {
Inventory inventory = Inventory.getInstance();
private static Blender blender = null;

    private Blender() {}
    public static Blender getInstance() {
        if (blender == null) blender = new Blender();
        return blender;
    }
    public void blendAtoms(){

    }
    public void breakAtoms(){

    }

}

