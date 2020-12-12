package com.company.Domain.Controller;

import com.company.Domain.Models.Blender;
import com.company.Enums.AtomType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BlenderHandler  {
    private Blender blender;
    public BlenderHandler() {
        this.blender = Blender.getInstance();
    }
    public void breakAtoms(AtomType source, AtomType product, int sourceAmount){
        blender.breakAtoms(source, product, sourceAmount);
    }
    public void blendAtoms(AtomType source, AtomType product, int sourceAmount){
        blender.blendAtoms(source, product, sourceAmount);
    }


}
