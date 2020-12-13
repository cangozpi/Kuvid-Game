package com.company;

import com.company.UI.Objects.*;

public class Main {

    public static void main(String[] args) {

         //Builder Mode initialization
         BuildWindowFactory buildWindow = BuildWindowFactory.getInstance();
         buildWindow.render();




    }
}
