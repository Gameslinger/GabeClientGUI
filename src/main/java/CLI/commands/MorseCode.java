/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.commands.morseLibrary.MorseLibrary;

/**
 *
 * @author Gabriel.Maxfield
 */
public class MorseCode implements ICommand {

    

    @Override
    public String getName() {
        return "Morse Code";
    }

    @Override
    public String response(String[] args) {
        char chr;
        String result = "";
        for (int i = 1; i < args.length; i++) {
            for (int e = 0; e < args[i].length(); e++) {
                chr = args[i].charAt(e);
                if (Character.isLetter(chr)) {
                    result += MorseLibrary.LETTERS[((int) Character.toLowerCase(chr)) - ((int) 'a')] + " ";
                }else if(Character.isDigit(chr)){
                    result += MorseLibrary.NUMBERS[(int) chr - ((int) '0')] + " ";
                    
                }else{
                    for(int j = 0; j < MorseLibrary.PUNCCHARS.length(); j++){
                        if(args[i].charAt(e)==MorseLibrary.PUNCCHARS.charAt(j))
                            result += MorseLibrary.PUNCTUATION[j]+" ";
                        
                    }
                }
            }
            if(i < args.length-1)
            result += "/ ";
        }
        return result;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"mc"};
    }

}
