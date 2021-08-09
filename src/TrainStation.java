import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;



import javafx.application.Application;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Font;
        import javafx.scene.text.FontPosture;
        import javafx.scene.text.FontWeight;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.Scanner;
        import java.util.concurrent.ThreadLocalRandom;


public class TrainStation extends Application {
    public static final int SEATING_CAPACITY = 42;
    private static ArrayList<Passenger> waitingRoom = new ArrayList<>();
    private static ArrayList<Passenger> temporaryQueue = new ArrayList<>();

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        String[] mainArray = new String[SEATING_CAPACITY];
        BufferedReader objReader = null;

        try {
            int i = 0;
            try {
                String CurrentLine;

                objReader = new BufferedReader(new FileReader("E:\\seats.txt"));
                //reading cw 1 text file
                while ((CurrentLine = objReader.readLine()) != null) {


                    String passengerName = CurrentLine.substring(11, CurrentLine.length());
                    if (!"null".equals(passengerName)) {
                        mainArray[i] = passengerName;  //storing the passengers name in to main array
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (objReader != null)
                        objReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ii) {
            System.out.println("Invalid Input!!");
        }
        for (int i = 0; i < SEATING_CAPACITY; i++) {
            if (mainArray[i] != null) {
                Passenger passenger = new Passenger();
                passenger.setName(mainArray[i]);
                passenger.setSeat(i);
                waitingRoom.add(passenger);
            }
        }
        menu();
    }           //menu
    public static void menu(){
        System.out.println();
        System.out.println("            **********************************************************************");
        System.out.println("            ++++++   WELCOME TO DENUWARA MENIKE TRAIN SEAT BOOKING SYSTEM   ++++++");
        System.out.println("            **********************************************************************");
        System.out.println();
        menu:
        while (true) {
            Scanner sc = new Scanner(System.in);

            System.out.println("    <>  Enter V to view all passengers ");
            System.out.println("    <>  Enter S to store programme data ");                    //getting a input to userinput
            System.out.println("    <>  Enter D to delete customer from queue  ");
            System.out.println("    <>  Enter R to run the simulation ");
            System.out.println("    <>  Enter A to add a passenger to the queue ");
            System.out.println("    <>  Enter L to Load programme data from file ");
            System.out.println("    <>  Enter Q to quit the programme  ");
            System.out.print("       *  Please select an option : ");
            String userinput = sc.next();
            System.out.println();
            switch (userinput) {
                case "V":
                case "v":
                    viewAllPassengers();
                    break;
                case "S":
                case "s":
                    storeProgrammeData();              //selecting the user input to matching method
                    break;

                case "A":
                case "a":
                    addPassenger();
                    break;


                case "R":
                case "r":
                    runSimulation();
                    break;


                case "L":
                case "l":
                    loadProgramme();
                    break;


                case "D":
                case "d":
                    deletePassenger();
                    break;
                case "Q":
                case "q":


                    System.exit(0);

                default:
                    System.out.println("Invalid");
                    System.out.println();
            }
        }
    }
    private static void addPassenger(){

        if (waitingRoom.isEmpty() && temporaryQueue.isEmpty()) {
            System.out.println("No passengers in the waiting room ");
            System.out.println();
            return;
        }

        Stage stage = new Stage();
        if (temporaryQueue.isEmpty()) {
            int random = ThreadLocalRandom.current().nextInt(1,7);
            if (random > waitingRoom.size()) random=waitingRoom.size();

            for (int i=0; i<random; i++) {
                temporaryQueue.add(waitingRoom.remove(0));
            }
        }
        ArrayList<Button> buttons = new ArrayList<>();
        GridPane gridPane=new GridPane();
        Text text1 = new Text("Train Queue");
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        for (int i=0; i<temporaryQueue.size(); i++) {
            Button temporaryBtn= new Button(temporaryQueue.get(i).getName()+" - "+(temporaryQueue.get(i).getSeat()+1));
            temporaryBtn.setMouseTransparent(true);
            buttons.add(temporaryBtn);
            gridPane.add(temporaryBtn,5,i+10);
            temporaryBtn.setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");

        }
        buttons.get(0).setStyle("-fx-background-color: yellow");

        Button addBtn = new Button("  Add  ");
        Button removeBtn = new Button(" Remove ");
        gridPane.add(addBtn,4,25);
        gridPane.add(removeBtn,5,25);
        removeBtn.setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        addBtn.setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");

        addBtn.setOnAction(event -> {
            if (PassengerQueue.isFull()) {
                System.out.println("Queue is full");
                stage.close();
            }else {
                buttons.remove(0).setStyle("-fx-background-color: green");
                Passenger passenger = temporaryQueue.remove(0);
                PassengerQueue.addToQueue(passenger, passenger.getSeat());

                if (buttons.isEmpty()) {
                    stage.close();
                } else {
                    buttons.get(0).setStyle("-fx-background-color: yellow");
                }
            }
        });
        removeBtn.setOnAction(event -> {
            buttons.remove(0).setStyle("-fx-background-color: red");
            waitingRoom.add(temporaryQueue.remove(0));
            if (buttons.isEmpty()) {
                stage.close();
            }else {
                buttons.get(0).setStyle("-fx-background-color: yellow");
            }
        });

        //exit button
        Button confirm = new Button("CLOSE");
        confirm.setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        confirm.setOnAction((Event) -> {
            stage.close();
        });

        gridPane.setHgap(30);
        gridPane.setVgap(10);
        gridPane.add(confirm,6,25);
        gridPane.add(text1, 5, 5);
        Scene scene = new Scene(gridPane,500,500);
        gridPane.setStyle("-fx-background-color:#ffe4b5;-fx-opacity:1;");
        stage.setScene(scene);
        stage.showAndWait();


    }
    private static void deletePassenger() {
        while (true){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the seat number you should want to delete or enter -1 to exit : ");
                int seat = scanner.nextInt()-1;
                if (seat==-2) {
                    System.out.println();
                    break;}
                else if (seat<0 || seat>41){
                    System.out.println("Invalid Input ");
                    continue;
                }else {
                    Passenger passenger = PassengerQueue.getPassengerQueue()[seat];
                    if (passenger == null) {
                        System.out.println("No passenger in the queue to delete ! ");
                        System.out.println();
                        continue;
                    }else {
                        System.out.println(passenger.getName()+" - "+passenger.getSeat());
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Are you sure want to delete this passenger ? ");
                        System.out.println(" Enter Y or y to delete and any other key to Cancel : ");
                        String string = sc.next();
                        if (string.equalsIgnoreCase("y")) {
                            PassengerQueue.delete(seat);
                            System.out.println("Passenger Deleted from the Queue");
                        }else {
                            System.out.println("Delete cancelled");
                        }
                    }
                }
            }catch (Exception e){
                System.out.println("Invalid Input Please re enter ! ");
                System.out.println();
            }

        }
    }
    private static void loadProgramme(){
        try {
            FileInputStream file = new FileInputStream(new File("E:\\passenger.txt"));
            ObjectInputStream objRead = new ObjectInputStream(file);
            while (true) {
                try {
                    Passenger passenger = (Passenger) objRead.readObject();
                    PassengerQueue.addToQueue(passenger,passenger.getSeat());  //reading the text file and store objects in to passenger
                }catch (EOFException e) {
                    break;}
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded");
        System.out.println();

    }
    private static void runSimulation() {
        if (PassengerQueue.isEmpty()) {
            System.out.println("Train Queue is empty");
            return;
        }

        int maxPassenger=0;
        int totalTime=0;
        int totPassengers=0;
        int maxStayTime = 0;
        int minStayTime = 100;
        ArrayList<Passenger> train = new ArrayList<>();
        ArrayList<Passenger> queue = new ArrayList<>();
        ArrayList<Passenger> tempLine = new ArrayList<>();
        for (Passenger passenger : PassengerQueue.getPassengerQueue()) {
            if (passenger!=null) queue.add(passenger);
        }
        int queueSize = queue.size();
        while (queueSize != train.size()) {
            if (tempLine.isEmpty()) {
                int random = ThreadLocalRandom.current().nextInt(1,7);
                if (random>queue.size()) random=queue.size();
                if (random>maxPassenger) maxPassenger = random;
                for (int i=0; i<random; i++) {
                    Passenger passenger = queue.remove(0);
                    PassengerQueue.delete(passenger.getSeat());
                    tempLine.add(passenger);
                }
            }
            //generating random number 3 to 18
            int time = ThreadLocalRandom.current().nextInt(3,19);
            for (Passenger passenger : tempLine){
                passenger.setSecondsInQueue(time);
            }
            int passengerTime = tempLine.get(0).getMinutesInTheQueue();
            System.out.println(passengerTime);
            totalTime= totalTime+(passengerTime);
            if (passengerTime>maxStayTime) maxStayTime = passengerTime;
            if (passengerTime<minStayTime) minStayTime = passengerTime;
            train.add(tempLine.remove(0));
            totPassengers++;
        }

//calculating results
        String totalPassengers = ("Number of passengers - "+(totPassengers));
        String maxLength = ("Maximum length of the queue attained - "+maxPassenger);
        String averageTime = "Average waiting time - " + (totalTime / totPassengers+" minutes");
        String maxTime = ("Maximum waiting time - "+maxStayTime+" minutes");
        String minTime = ("Minimum waiting time - "+minStayTime+" minutes");

        try {
            Writer fileWriter = new FileWriter("E:\\simulation.txt", true);
            fileWriter.write("Simulation summary");
            fileWriter.write(totalPassengers);
            fileWriter.write(maxLength);
            fileWriter.write(averageTime);
            fileWriter.write(maxTime);
            fileWriter.write(minTime);


            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error");
        }
        //adding results to the gui
        Stage stage = new Stage();
        GridPane gridPane=new GridPane();
        Text text1=new Text("Simulation");
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text1.setUnderline(true);
        Text text2=new Text(totalPassengers);
        Text text3=new Text(maxLength);
        Text text4=new Text(averageTime);
        Text text5=new Text(maxTime);
        Text text6=new Text(minTime);

        gridPane.setHgap(8);
        gridPane.setVgap(8);
        gridPane.add(text1,5,3);
        gridPane.add(text2,5,5);
        gridPane.add(text3,5,6);
        gridPane.add(text4,5,7);
        gridPane.add(text5,5,8);
        gridPane.add(text6,5,9);
        Scene scene = new Scene(gridPane,300,350);
        stage.setScene(scene);
        stage.showAndWait();


    }
    private static void storeProgrammeData() {
        try {
            FileOutputStream output = new FileOutputStream("E:\\passenger.txt");
            ObjectOutputStream objOut = new ObjectOutputStream(output);
            for (Passenger passenger : PassengerQueue.getPassengerQueue()) {
                if (passenger != null) {
                    objOut.writeObject(passenger);   //write objects to a file named data.txt
                }
            }



        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("All data have been saved");
        System.out.println();

    }
    private static void viewAllPassengers() {
        Stage stage = new Stage();
        Text text1 = new Text("Other Queue attends");
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text1.setUnderline(true);

        Text text2 = new Text("Waiting Room");
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text2.setUnderline(true);

        GridPane gridPane = new GridPane();
        //adding passengers names into the queue
        int j=0;
        for (Passenger passenger : temporaryQueue){
            Text queueText = new Text(passenger.getName()+" - "+(passenger.getSeat()+1));
            queueText.setFont(Font.font("verdana", FontPosture.REGULAR, 13));
            gridPane.add(queueText,9,j+12);
            j++;
        }
        //adding passengers names in to the waiting room
        int i=0;
        for (Passenger passenger : waitingRoom) {

            Text passengertxt = new Text(passenger.getName()+" - "+(passenger.getSeat()+1));
            passengertxt.setFont(Font.font("verdana", FontPosture.REGULAR, 13));
            gridPane.add(passengertxt, 3,   i+ 12);
            i++;
        }


        int z=0;

        for (Passenger passenger : PassengerQueue.getPassengerQueue()){
            z++;
            Button btn;
            if (passenger==null){
                btn = new Button("Empty - "+z);
            }else {
                btn = new Button(passenger.getName() + " - " + (passenger.getSeat() + 1));
                btn.setStyle("-fx-background-color: green");
            }
            //btn.setPrefSize(60, 30);
            if(z<=14){
                gridPane.add(btn,14,z+4);
                continue;
            }else if(z<=28){
                gridPane.add(btn,15,z-10);
                continue;
            }else {
                gridPane.add(btn,16,z-24);
            }

        }



        //exit button
        Button confirm = new Button("CLOSE");
        confirm.setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        confirm.setOnAction((Event) -> {
            stage.close();
        });
        gridPane.add(text2,3,5);
        gridPane.add(confirm, 19, 19);
        gridPane.add(text1, 9, 5);
        gridPane.setHgap(30);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color:#ffe4b5;-fx-opacity:1;");
        Scene scene = new Scene(gridPane, 1300, 600);
        stage.setScene(scene);
        stage.showAndWait();

    }


}



