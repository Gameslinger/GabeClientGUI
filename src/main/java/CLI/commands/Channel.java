/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import gabeclientgui.ChatWindow;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Channel implements ICommand{
    ChatWindow cw;
    public Channel(ChatWindow cw){
        this.cw = cw;
    }
    @Override
    public String getName() {
        return "Set Channel";
    }

    @Override
    public String response(String[] args) {
        cw.getIcom().connect(cw.getIcom().getAddress(), args[1]);
        return "";
    }

    @Override
    public String[] getKeys() {
        return new String[]{"sc","SetChannel"};
    }
    @Override
    public String getHelp(){
        return "Change Redis Channel ex: !sc cool";
    }
}
