package gr.aueb.cf.mobilecontacts;

import gr.aueb.cf.mobilecontacts.controller.MobileContactController;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;


import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final MobileContactController controller = new MobileContactController();

    public static void main(String[] args) {
        String choice;

        while (true) {
            printMenu();
            choice = getToken();

            if (choice.equals("q") || (choice.equals("Q"))) {
                break;
            }

            handleChoice(choice);
        }

        System.out.println("Thank you for using mobile contacts");
    }


    public static void handleChoice(String choice) {
        String firstname;
        String lastname;
        String phoneNumber;
        String response;

        switch (choice) {
            case "1":
                System.out.println("parakalw eisagete onoma, epwnumo, ar.thlefwnou");
                firstname = getToken();
                lastname = getToken();
                phoneNumber = getToken();
                MobileContactInsertDTO insertDTO = new MobileContactInsertDTO(firstname, lastname, phoneNumber);
                response = controller.insertContact(insertDTO);

                if (response.startsWith("OK")) {
                    System.out.println("epituxhs eisagwgh!");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("anepituxhs eisagwgh");
                    System.out.println(response.substring(7));
                }
                break;
            case "2":
                //
                break;
            case "3":
                //
                break;
            case "4":
                //
                break;
            case "5":
                //
                break;
            default:
                //
                break;

        }
    }


    public static void printMenu() {
        System.out.println("Epileksete ena apo ta parakatw");
        System.out.println("1.Eisagwgh epafhs.");
        System.out.println("2.Enhmerwsh epafhs.");
        System.out.println("3.Diagrafh epafhs.");
        System.out.println("4.Anazhthsh epafhs.");
        System.out.println("5.Provoli epafhs.");
        System.out.println("Q/q Eksodos.");
    }

    public static String getToken() {
        return in.nextLine().trim();
    }
}
