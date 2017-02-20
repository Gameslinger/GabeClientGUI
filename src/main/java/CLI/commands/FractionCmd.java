/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;
import CLI.commands.frac.Fraction;
/**
 *
 * @author Gabriel.Maxfield
 */
public class FractionCmd implements ICommand{

    @Override
    public String getName() {
        return "Fraction";
    }

    @Override
    public String response(String[] args) {
        String result = "";
        for(int i = 1; i < args.length; i++){
            result += args[i]+" ";
        }
        Fraction frac = Fraction.parseFrac(Fraction.exStr(result).trim());
        return result+"= " + frac.reduce();
    }

    @Override
    public String[] getKeys() {
        return new String[]{"f","frac","fraction"};
    }
    
}
