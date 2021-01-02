package com.company;

import com.company.UI.*;

public class Main {

    public static void main(String[] args) {

         //Builder Mode initialization
         MainMenuWindowFactory mainmenuWindowFactory  = MainMenuWindowFactory.getInstance();
         mainmenuWindowFactory.render();




    }
}
