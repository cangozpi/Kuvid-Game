package com.company.Domain.Controller;
import com.company.Domain.Models.AtomSelectorFactory;

public class SelectAtomHandler {
    private AtomSelectorFactory atomSelector;

    public SelectAtomHandler(AtomSelectorFactory atomSelector) {
        this.atomSelector = AtomSelectorFactory.getInstance();
    }

    public void selectAtom() {
        atomSelector.selectAtom();

    }
}