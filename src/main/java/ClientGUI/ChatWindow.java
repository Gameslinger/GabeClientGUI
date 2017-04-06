/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientGUI;

import CLI.CLI;
import CLI.MessageList;
import CLI.Nameable;
import Communication.Chat;
import Communication.ICommunication;
import Communication.JsCom;
import Communication.MockCom;
import Communication.RedisCom;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;

/**
 *
 * @author Gabe
 */
public class ChatWindow extends Chat implements Nameable, MessageList{
    ICommunication icom;
    String sendText;
    TextField input;
    
    ComEnum comOp;
    
    TextArea sendCode,recCode;
    HBox codeAreas;
    
    String name;
    
    CLI cli;
    
    List<String> past = new ArrayList();
    int index = 0;
    String messText;
    public ListView<String> messages;
    ChatWindow(String name,final String address, final String channel,ComEnum comOp){
        this.setAddress(address);
        cli = new CLI(this);
        cli.setName(name);
        messages = new ListView();
        
        messages.setOnMouseClicked(click-> {
            if (click.getClickCount() == 2) {
                messText = messages.getSelectionModel().getSelectedItem();
                if(messText!=null)
                    input.setText(input.getText()+messText);
            }
        });

        this.comOp = comOp;
        //----------------------------
        switch(comOp){
            case RedisCom:
                icom = new RedisCom(this,messages);
                break;
            case MockCom:
                icom = new MockCom();
                break;
            case JsCom:
                sendCode = new TextArea("//Put onSend Javascript code here\nclient.send(name+'>'+msg);");
                recCode = new TextArea("//Put onRecieve Javascript code in here\nmessages.add(msg);");
                codeAreas = new HBox();
                codeAreas.getChildren().addAll(sendCode,recCode);
                icom = new JsCom(this,messages,sendCode,recCode);
                
                break;
            default:
                System.out.println("Illegal Toggle!");
        }
        //----------------------------
        icom.connect(address,channel);
        
        
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
                
                
                
                messages.getItems().add(cli.getName()+"> "+sendText);
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
        input.requestFocus();
        
        VBox vb = new VBox();
        if(comOp==ComEnum.JsCom){
            vb.getChildren().addAll(messages,codeAreas,input);
        }else{
        vb.getChildren().addAll(messages,input);
        }
        
        return new Scene(vb,600,300,Color.WHITE);
        
    }

    @Override
    public void setName(String name) {
    
    }
    @Override
    public String getName(){
       return cli.getName();
    }

    public void setIcom(ICommunication icom) {
        this.icom = icom;
    }

    public ICommunication getIcom() {
        return icom;
    }

    @Override
    public ObservableList<String> getMessages() {
        return messages.getItems();
    }

    @Override
    public ICommunication getCom() {
        return icom;
    }

    
}
