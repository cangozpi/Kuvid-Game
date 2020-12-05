package com.company.Domain.Controller;

import com.company.Domain.Models.Blender;
import com.company.Enums.AtomType;

public class BlenderHandler {
    Blender blender = Blender.getInstance();
    public BlenderHandler(Blender blender) {
        this.blender = blender;
    }
    public void breakAtoms(AtomType source, AtomType product, int sourceAmount){
        blender.breakAtoms(source, product, sourceAmount);
    }
    public void blendAtoms(AtomType source, AtomType product, int sourceAmount){
        blender.blendAtoms(source, product, sourceAmount);
    }
}
