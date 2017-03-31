/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabe
 */
public class LyricLookup implements ICommand{
    
    String source = "http://www.azlyrics.com/lyrics/";
    
    @Override
    public String getName() {
    return "Lyric Look-up";
    }

    @Override
    public String response(String[] args) {
        StringBuilder result = new StringBuilder();
        String artist = args[2];
        String song = args[1].replaceAll(" ", "");
        URLConnection url;
        try {
            url = new URL(source+artist+"/"+song+".html").openConnection();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(url.getInputStream()));
        String line;
        while(!(line=br.readLine().replaceAll("<br>", "\n")).equals("<!-- Usage of azlyrics.com content by any third-party lyrics provider is prohibited by our licensing agreement. Sorry about that. -->"));
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
    
}
