package com.gordon.design;

import javax.swing.text.html.HTML;

final class MyUserOuter {
    private static final String tag = "myUser";
}

public class MyUser {
    public void set(){
        System.out.println(MyUserOuter.tag);
    }
}
