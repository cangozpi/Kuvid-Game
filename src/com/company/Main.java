package com.company;

import com.company.Domain.Models.GameFactory;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.AtomType;
import com.company.Enums.ProjectileType;
import com.company.UI.Objects.*;

public class Main {

    public static void main(String[] args) {
        ProjectileType a = AtomType.ALPHA;


         //Builder Mode initialization
         BuildWindowFactory buildWindow = BuildWindowFactory.getInstance();
         buildWindow.render();




    }
}
