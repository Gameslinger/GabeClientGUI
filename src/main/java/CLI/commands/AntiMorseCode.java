/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.commands.morseLibrary.MorseLibrary;

/**
 *
 * @author Gabe
 */
public class AntiMorseCode implements ICommand{
    
    
    @Override
    public String getName() {
        return "Anti-Morse Code";
    }

    @Override
    public String response(String[] args) {
        String result = "";
        boolean found;
         for(int i = 1; i < args.length; i++){
             found = false;
             if(args[i].equals("/")){
                 result+=" ";
                 continue;
             }
             
             for(int e = 0; e < MorseLibrary.LETTERS.length; e++){
                 if(args[i].equals(MorseLibrary.LETTERS[e])){
                     result+=(char)(e+'a');
                     found=true;
                     break;
                 }
             }
             if(found)continue;
             for(int e = 0; e < MorseLibrary.NUMBERS.length; e++){
                 if(args[i].equals(MorseLibrary.NUMBERS[e])){
                     result+=(char)(e+'0');
                     found=true;
                     break;
                 }
             }
             if(found)continue;
             for(int e = 0; e < MorseLibrary.PUNCCHARS.length(); e++){
                 if(args[i].equals(MorseLibrary.PUNCTUATION[e])){
                     result+=MorseLibrary.PUNCCHARS.charAt(e);
                     found=true;
                     break;
                 }
             }
             if(found)continue;
             result+="("+args[i]+")";
         }
         return result;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"amc","antimorse"};
    }
    @Override
    public String getHelp(){
        return "Reverses Morse Code ex: !amc !mc Hello World";
    }
}
