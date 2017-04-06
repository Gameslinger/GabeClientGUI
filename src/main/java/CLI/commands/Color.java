/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Color implements ICommand {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    @Override
    public String getName() {
        return "Color";
    }

    @Override
    public String response(String[] args) {
        String result = "";
        for(int i = 1; i < args.length; i++){
            for(int e = 0; e < args[i].length(); e++){
                if(args[i].charAt(e)=='%'){
                    switch(args[i].charAt(e+1)){
                        case 'b':
                        result+=ANSI_BLUE;
                        break;
                        case 'l':
                        result+=ANSI_RESET;
                        break;
                        case 'c':
                        result+=ANSI_CYAN;
                        break;
                        case 'r':
                        result+=ANSI_RED;
                        break;
                        case 'y':
                        result+=ANSI_YELLOW;
                        break;
                        case 'p':
                        result+=ANSI_PURPLE;
                        break;
                        case 'w':
                        result+=ANSI_WHITE;
                        break;
                        case 'g':
                        result+=ANSI_GREEN;
                        break;
                        case '%':
                        result+='%';
                        break;
                    }
                    e++;
                }else{
                    result+=args[i].charAt(e);
                }
            }
            if(i<args.length-1)
            result+=' ';
        }
        return result+ANSI_RESET;
    }

    @Override
    public String[] getKeys() {
        return new String[] {"c","color"};
    }
    @Override
    public String getHelp(){
        return "Chat colors(does not work in GUI) ex !c %bThe %rColors %yduke%g!";
    }
}
