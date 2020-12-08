package com.company.Domain.Controller;

import com.company.Domain.Models.Blender;
import com.company.Enums.AtomType;

public class BlenderHandler {
    private Blender blender;
    public BlenderHandler(Blender blender) {
        this.blender = Blender.getInstance();
    }
    public void breakAtoms(AtomType source, AtomType product, int sourceAmount){
        blender.breakAtoms(source, product, sourceAmount);
    }
    public void blendAtoms(AtomType source, AtomType product, int sourceAmount){
        blender.blendAtoms(source, product, sourceAmount);
    }
}
