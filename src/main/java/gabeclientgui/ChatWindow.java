/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gabeclientgui;

import CLI.CLI;
import Communication.ICommunication;
import Communication.MocCommunication;
import Communication.RedisCom;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import javafx.scene.input.MouseEvent;

/**
 *
 * @author Gabe
 */
public class ChatWindow{
    ICommunication icom;
    String sendText;
    TextField input;
    
    CLI cli;
    
    List<String> past = new ArrayList();
    int index = 0;
    String messText;
    public ListView<String> messages;
    ChatWindow(String name, final String address){
        cli = new CLI(this);
        cli.setName(name);
        messages = new ListView();
        
        messages.setOnMouseClicked(new EventHandler<MouseEvent>(){
 
          @Override
          public void handle(MouseEvent click) {
            if (click.getClickCount() == 2) {
            messText = messages.getSelectionModel().getSelectedItem();
            if(messText!=null)
           input.setText(input.getText()+messText);
        }}
        });

        
        //----------------------------
        icom  = new RedisCom(messages); //MocCommunication();
        //----------------------------
        icom.connect(address);
        
        
        input = new TextField();
        input.setOnKeyPressed(new EventHandler<KeyEvent>(){
            
            @Override
            public void handle(KeyEvent ke){
            if(ke.getCode()==KeyCode.ENTER){
                sendText = input.getText();
                input.setText("");
                
                past.add(sendText);
                index=past.size();
                
                sendText = cli.processString(sendText);
                if(sendText.trim().length()!=0){
                    //Check if sent????
                icom.send(sendText);
                
                
                //messages.getItems().add(icom.recieve());
                
                
                
                messages.getItems().add(cli.getUserName()+"> "+sendText);
                messages.scrollTo(messages.getItems().size()-1);
                }
                
            }else if(ke.getCode()==KeyCode.UP || ke.getCode()==KeyCode.DOWN){
                if(ke.getCode()==KeyCode.UP && index > 0){
                    index--;
                }else if(ke.getCode()==KeyCode.DOWN && index < past.size()-1){
                    index++;
                }
                input.setText(past.get(index));
            }
            
            }});
        
        
        //Success
       //....
    }
    
    public Scene createChatWindow(){
        //Create new communication with address....
        //----
       // HBox hb = new HBox();
        //|
        //|
        input.requestFocus();
        
        VBox vb = new VBox();
        vb.getChildren().addAll(messages,input);
        
        
        return new Scene(vb,600,300,Color.WHITE);
        
    }
}
