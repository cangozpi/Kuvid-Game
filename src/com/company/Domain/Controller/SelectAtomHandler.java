package com.company.Domain.Controller;

public class SelectAtomHandler {
    private AtomSelectorFactory atomSelector;

    public SelectAtomHandler(AtomSelectorFactory atomSelector) {
        this.atomSelector = AtomSelectorFactory.getInstance();
    }

    public void selectAtom() {
        atomSelector.selectAtom();

}
