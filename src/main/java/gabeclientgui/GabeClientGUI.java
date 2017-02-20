/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gabeclientgui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Gabe
 */
public class GabeClientGUI extends Application {
    Label welcome,addMess,namePrompt;
    TextField addText,name;
    Button next;
    @Override
    public void start(final Stage primaryStage) {
        welcome = new Label("Welcome to Gabe Client!");
        namePrompt = new Label("Please enter your username:");
        addMess = new Label("Please input the address you are connecting to:");
        addText = new TextField("localhost");
        name = new TextField("Guest");
        next = new Button("Connect");
        next.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae){
            ChatWindow cw = new ChatWindow(name.getText(),next.getText());
           Scene chatScene = cw.createChatWindow();
            primaryStage.setScene(chatScene);
            
            }});
        
        VBox root = new VBox();
        root.getChildren().addAll(welcome,namePrompt,name,addMess,addText,next);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("GabeClient!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
