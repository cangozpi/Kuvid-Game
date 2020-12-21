package com.company.Domain.Models;

import com.company.UI.Objects.BuildWindowFactory;

public class Menu {
    private static Menu menu = null;
    public static Menu getInstance() {
        if (menu == null) menu = new Menu();
        return menu;
    }
    public void restart(){
        // buildwindowun instancelarını mı resetliycez buildwindowu mu?
        // BuildWindowFactory buildWindow = BuildWindowFactory.getInstance();
        //Restart gamei buildgamein içinde callayıp sonra bastıgımızda yeniden callayabiliriz
        //buildWindow.render();
        // kapama tuşu ve R nasıl halledilecek otomatik kapama var mı
    }
}