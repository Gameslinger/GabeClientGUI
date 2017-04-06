/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Gabe
 */
public class LyricLookup implements ICommand{
    
    String source = "http://www.azlyrics.com/lyrics/";
    String search = "http://search.azlyrics.com/search.php?q=";
    
    @Override
    public String getName() {
    return "Lyric Look-up";
    }
    //Future me... I'm sorry...
    @Override
    public String response(String[] args) {
        StringBuilder result = new StringBuilder();
        //Search and grab first result:
        if(args[1].equals("search")){
            StringBuilder str = new StringBuilder();
            for(int i = 2; i < args.length; i++){
                str.append(args[i]);
                if(i<args.length-1)
                str.append("+");
            }
            URLConnection url;
        try {
            System.out.println(search+str.toString());
            url = new URL(search+str.toString()).openConnection();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(url.getInputStream()));
        String line;
        while(!(line=br.readLine()).contains("<body"));
        while(!(line=br.readLine()).contains("<a href=\"http://www.azlyrics.com/lyrics/"));
        String[] parts = line.split("\"")[1].split("/");
        return "!l "+parts[5].substring(0,parts[5].length()-5)+" by "+parts[4];
        }catch (Exception ex) {
           ex.printStackTrace();
           return "!l Result not found";
        }
        }
        String artist = args[2];
        String song = args[1].replaceAll(" ", "");
        URLConnection url;
        try {
            url = new URL(source+artist+"/"+song+".html").openConnection();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(url.getInputStream()));
        String line;
        while(!br.readLine().equals("<!-- Usage of azlyrics.com content by any third-party lyrics provider is prohibited by our licensing agreement. Sorry about that. -->"));
        result.append(song);
        result.append(" by ");
        result.append(artist);
        result.append("\n");
        while(!(line=br.readLine().replaceAll("<br>", "\n").replaceAll("</?i>","").replaceAll("&quot;","\"")).equals("</div>")){
            result.append(line);
        }
        }catch (Exception ex) {
           ex.printStackTrace();
           return "!l Song not found";
        }
        return result.toString();
    }

    @Override
    public String[] getKeys() {
        return new String[]{"llu","lyrics"};
    }
    @Override
    public String getHelp(){
        return "Looks up lyrics on azlyrics (put songs as one word and only\n"
                + " alphabetic/numeric characters) ex !llu sevennationarmy whitestripes\n"
                + "ex !llu search [song]";
    }
}
