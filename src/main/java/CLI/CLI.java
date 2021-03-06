/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import CLI.commands.*;
import Communication.Chat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Gabriel.Maxfield
 */
public class CLI implements Nameable,CommandList{
    public  List<String> list = new ArrayList();
    public  Map<String,ICommand> comMap = new HashMap();
    public  List<ICommand> commands = new ArrayList();
    MessageList messageList;
    String userName = "Root";
    Scanner scanner = new Scanner(System.in);
    public char cmdChar = '!';
    public CLI(Chat cw){
        this.messageList = cw;
         commands.add(new Echo());
         commands.add(new Reverse());
         commands.add(new Quit());
         commands.add(new Color());
         commands.add(new Help(this));
         commands.add(new Name(this));
         commands.add(new Escapes());
         commands.add(new FractionCmd());
         commands.add(new MorseCode());
         commands.add(new BinaryCode());
         commands.add(new ShiftEncrypt());
         commands.add(new XOREncrypt());
         commands.add(new HexCode());
         commands.add(new AntiMorseCode());
         commands.add(new AntiBinaryCode());
         commands.add(new AntiHexCode());
         commands.add(new RandomString());
         commands.add(new AtBash());
         commands.add(new Clear(cw));
         commands.add(new Local(messageList));
         commands.add(new Channel(cw));
         commands.add(new Spam(cw,this));
         commands.add(new LyricLookup());
         commands.add(new Replace());
         
         for(ICommand com : commands){
            for (String key : com.getKeys()) {
                comMap.put(key, com);
            }
         }
         
}
    public String processString(String line){
        try{
            return scanString(line);
        }catch(Exception e){
           //e.printStackTrace();
            messageList.getMessages().add("Improper Input");
            e.printStackTrace();
            return "";
            //return("Improper Input");
        }
    }
    public String nextString(){
        System.out.print(userName+">");
        return scanner.nextLine();
    }
    
    
    ICommand cmd;
     String test;

     @Override
    public String scanString(String str){
        boolean skip=false, found = false;
        String tokens[] = str.split(" ");
        for(int i = tokens.length-1; i >= 0; i--){
        if(tokens[i].isEmpty())return str;
        
            if(tokens[i].charAt(0)==cmdChar){
                if(tokens[i].charAt(1)==cmdChar){
                    tokens[i]="";
                    skip=true;
                    break;
                }
                found = true;
               test = tokens[i].substring(1).toLowerCase();
                if(comMap.containsKey(test)){
                    String args = "";
                    cmd = comMap.get(test);
                            for(int e = 0; e < i; e++){
                                args+=tokens[e]+" ";
                            }

                    return scanString(args+cmd.response(Arrays.copyOfRange(tokens, i, tokens.length)));
                    
                }
                
            }
        }
        if(skip){
            return comMap.get(tokens[0].substring(1)).response(tokens);
        }
      
        //Local Message
        if(found){
            messageList.getMessages().add("Command not found");
            return "";
        }
        //Send message:
        return str;
    } 
    
    
    @Override
    public void setName(String newName) {
        this.userName = newName;
    }

    @Override
    public String getName() {
        return userName;
    }

    @Override
    public Map<String, ICommand> getMap() {
        return comMap;
    }

    @Override
    public List<ICommand> getList() {
        return commands;
    }
    
}
