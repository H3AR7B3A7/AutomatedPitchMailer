package com.company;

import java.io.*;
import java.util.Scanner;

public class CreatePitch {

    static String addressedName;
    static String company;
    static String bestQuality;
    static String myName;

    public static void main(String[] args) throws IOException {
        argumentCheck(args);
        getInfo(args);
        capitalize();
        writePitch();
    }

    /**
     * Write the pitch
     */
    private static void writePitch() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("pitch.txt"));
        if (addressedName.equals("")) {
            writer.append("Hi,\n");
        } else {
            writer.append("Dear ");
            writer.append(addressedName);
            writer.append(",\n");
        }
        writer.close();

        PrintWriter printWriter = new PrintWriter(new FileWriter("pitch.txt", true));
        printWriter.printf("I found this job offer at %s and it got me interested.\n", company);
        printWriter.close();

        writer = new BufferedWriter(new FileWriter("pitch.txt", true));
        writer.append("I think I could contribute at ");
        writer.append(company);
        writer.append(" because I am very ");
        writer.append(bestQuality);
        writer.append(".\n\n");
        writer.close();

        printWriter = new PrintWriter(new FileWriter("pitch.txt", true));
        printWriter.printf("Kind regards,\n\n\n%s", myName);
        printWriter.close();
    }

    /**
     * Make sure names are capitalized where needed
     */
    private static void capitalize() {
        addressedName = addressedName.substring(0,1).toUpperCase() + addressedName.substring(1).toLowerCase();
        company = company.substring(0,1).toUpperCase() + company.substring(1);
        myName = myName.substring(0,1).toUpperCase() + myName.substring(1).toLowerCase();
    }

    /**
     * Ask for values if arguments are not all present
     */
    private static void getInfo(String[] args) throws IOException {
        if (args.length < 4) {
            BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
            Scanner scanner = new Scanner(System.in);

            System.out.println("The name of the addressed:");
            System.out.println("Press enter if the addressed is unknown.");
            addressedName = cin.readLine();
            System.out.println("The company name:");
            company = cin.readLine();

            System.out.println("Your best quality:");
            bestQuality = scanner.nextLine();
            System.out.println("Your name:");
            myName = scanner.nextLine();
        }
    }

    /**
     * Check for arguments
     */
    private static void argumentCheck(String[] args) {
        if (args.length > 0) {
            addressedName = args[0];
        }
        if (args.length > 1) {
            company = args[1];
        }
        if (args.length > 2) {
            bestQuality = args[2];
        }
        if (args.length > 3) {
            myName = args[3];
        }
    }
}