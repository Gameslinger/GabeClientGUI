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
public class AtBash implements ICommand{

    @Override
    public String getName() {
        return "At Bash";
    }

    @Override
    public String response(String[] args) {
        String result = "";
        char chr;
        for(int i = 1; i < args.length; i++){
            for(int e = 0; e < args[i].length(); e++){
                chr = Character.toLowerCase(args[i].charAt(e));
                if(Character.isLetter(chr)){
                    result+=(char)('a'+(25-(chr-'a')));
                }else{
                    result+=chr;
                }
            }
            result+=" ";
                
        }
        return result;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"ab","atbash"};
    }
    @Override
    public String getHelp(){
        return "AtBash code ex: !ab Hello World";
    }
}
