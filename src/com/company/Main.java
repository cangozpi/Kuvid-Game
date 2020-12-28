package com.company;

import com.company.Domain.SaveAndLoadGame.SaveGame;
import com.company.UI.*;

public class Main {

    public static void main(String[] args) {

         //Builder Mode initialization
         BuildWindowFactory buildWindow = BuildWindowFactory.getInstance();
         buildWindow.render();




    }
}
