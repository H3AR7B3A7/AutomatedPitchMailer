package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    static String addressedName;
    static String company;
    static String bestQuality;
    static String myName;

    public static void main(String[] args) throws IOException {
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

        BufferedWriter writer = new BufferedWriter(new FileWriter("pitch.txt"));
        if(addressedName.equals("")){
            writer.append("Hi,\n");
            writer.close();
        }else{
            writer.append("Dear ");
            writer.append(addressedName);
            writer.append(",\n");
            writer.close();
        }

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
}
