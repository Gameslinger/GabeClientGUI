/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import Communication.Chat;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Channel implements ICommand{
    Chat cw;
    public Channel(Chat cw){
        this.cw = cw;
    }
    @Override
    public String getName() {
        return "Set Channel";
    }

    @Override
    public String response(String[] args) {
        cw.getCom().connect(cw.getAddress(), args[1]);
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
