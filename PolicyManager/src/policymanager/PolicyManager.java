// Policy Manager
// A program that calculates insurance claims for gadgets

package policymanager;
//@author Alicia-Jade Fisher

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.util.Scanner;


public class PolicyManager 
{
//@param args the command line arguments
    
    // declares a class variable for user inputs
    public static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String[] args) 
    {
         // calls the subroutine displayMenu
        displayMenu();
    }
    
    static void displayMenu()
    {
        
       String menuChoice;
        
        // a series of outputs that display a menu for the user
        System.out.println("1.  Enter new Policy");
        System.out.println("2.  Display Summary of Policies");
        System.out.println("3.  Display Summary of Policies for Selected Month");
        System.out.println("4.  Find and display Policy");
        System.out.println("0.  Exit");
        System.out.println("");
        System.out.print("Please enter an option from above : ");
        
        // gets a menu choice from the user
        menuChoice = keyboard.nextLine();
        
        
        // validates the menu choice from the user
        // until a valid entry is inputted, the code will loop
        while (!(menuChoice.matches("[0-4]{1}")))
        {
            System.out.print("Invalid option, please try again : ");
            menuChoice = keyboard.nextLine();
            
        }
        
        // uses the menu choice from the user
        // to determine which subroutine will be called next
        switch(menuChoice)
        {
                case "1":
                    newPolicy();
                    break;
                case "2":
                    fileSummary();
                    break;
                case "3":
                    monthSummary();
                    break;
                case "4":
                    searchFile();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    break;
        }
    }
    static void newPolicy()
    {
        // uses method calls and class setters, to assign values to the attributes
        Policy newPolicy = new Policy();
        newPolicy.setName(getClientName());
        newPolicy.setRef(getReferenceNumber());
        newPolicy.setQuantity(getGadgetQuantity());
        newPolicy.setValue(getGadgetValue());
        newPolicy.setExcess(getExcess());
        newPolicy.setTerm(getPaymentTerm());
        newPolicy.setPremium();
        newPolicy.setDate();
        
        // calls the write to file method
        writeToFile(newPolicy.getDate(), newPolicy.getRef(), newPolicy.getQuantity(), newPolicy.getValue(), newPolicy.getExcess(), newPolicy.getPremium(), newPolicy.getTerms(), newPolicy.getName());
        
        // calls the display policy method
        displayPolicy(newPolicy.getDate(), newPolicy.getRef(), newPolicy.getQuantity() , newPolicy.getValue(), newPolicy.getExcess(), newPolicy.getPremium(), newPolicy.getTerms(), newPolicy.getName());

        // calls the back to menu method
        backToMenu();
        
    }   
    
    static String getClientName()
    {
        // gets the client's name from the user
        String name;
        
        System.out.print("Please enter you name : ");
        name = keyboard.nextLine();
        
        //checks that the name is less than 20 characters
        while (name.length() == 0)
        {
            System.out.println("No name has been entered, please try again, or press R to return to the menu.");
            name = keyboard.nextLine();
            if("R".equals(name) || "r".equals(name))
            {
                displayMenu();
            }
        }
        while (name.length() > 20)
        {
            System.out.print("Entered name is too long, please try a shorter version : ");
            name = keyboard.nextLine();
        }
        return name;
    }
    
    static String getReferenceNumber()
    {
        // gets the reference number from the user
        String reference;
        
        System.out.print("Please enter a reference number [AB123C] : ");
        reference = keyboard.nextLine().toUpperCase();
      
        // checks that the entered reference number matches the specified pattern
        while(!(reference.length() == 6))
        {
            System.out.println("Referece number is not the correct length of 6 characters. Please try again :");
            reference = keyboard.nextLine().toUpperCase();
        }
        
        while (!(reference.matches("[A-Z]{2}[0-9]{3}[A-Z]{1}")))
        {
            for(int i = 0; i<=5; i++)
            {
                if(i == 0 || i == 1 || i == 5 )
                {
                    if(!(String.valueOf(reference.charAt(i)).matches("[A-Z]")))
                    {
                        System.out.println("The entered character at position " + (i + 1) + " is not a letter.");
                    }
                }   
                else
                {
                    if(!(String.valueOf(reference.charAt(i)).matches("[0-9]")))
                    {
                        System.out.println("The entered character at position " + (i + 1) + " is not a number.");
                    
                    }
                }
                
            }
            System.out.print("Please try again : ");
            reference = keyboard.nextLine().toUpperCase();
        }
        return reference;
    }
    
    static int getGadgetQuantity()
    {
        // gets the number of gadets from the user
        String quantity;
        
        System.out.print("Please enter the number of gadgets : ");
        quantity = keyboard.next();
        
        // checks that the entered value is not a letter
        while(!(quantity.matches(".*[0-9].*")))
        {
            System.out.println("You have inputted a letter, which is an invalid entry.");
            System.out.print("Please try again : ");
            
            quantity = keyboard.next();
        }

        // checks that the entered value is not 0
        while (Integer.parseInt(quantity) <= 0)
        {
            System.out.println("At least one gadget is required to make a claim.");
            System.out.print("Please try again : ");
            
            quantity = keyboard.next();
        }
        
       
        return Integer.parseInt(quantity);
       
    }
    
    static double getGadgetValue()
    {
        // gets the gadget value from the user
        String value;
        
        System.out.print("Please enter the value of your most expensive gadget : ");
        value = keyboard.next();
        
        //checks that the entered value is not a letter
        while(!(value.matches(".*[0-9].*")))
        {
            System.out.println("You have inputted a letter, which is an invalid entry.");
            System.out.print("Please try again : ");
            
            value = keyboard.next();
        }
        
        // checks that the value is positive
        while (Double.parseDouble(value) <= 0)
        {
            System.out.println("A negative value has been entered.");
            System.out.print("Please enter a positive value : ");
            value = keyboard.next();
            
        }
        return Double.parseDouble(value);
    }
    
    static double getExcess()
    {
        // gets the payment excess from the user
        String paymentExcess;
        double excess = 0;
        
        System.out.println("Payment excess must be between £30 and £70.");
        System.out.print("How much excess would you like to pay ?");
        paymentExcess = keyboard.next();
        
        //checks that the entered value is not a letter
        while(!(paymentExcess.matches(".*[0-9].*")))
        {
            System.out.println("You have inputted a letter, which is an invalid entry.");
            System.out.print("Please try again : ");
            
            paymentExcess = keyboard.next();
        }
        
        
        // checks that the entered value is within a specified range
        while (!(Double.parseDouble(paymentExcess) >= 30 && Double.parseDouble(paymentExcess) <= 70) ||!(Double.parseDouble(paymentExcess) % 10 == 0) )
        {
            excess = Double.parseDouble(paymentExcess);
            if(!(excess >= 30 && excess <= 70))
            {
              System.out.print("The excess must be between £30 and £70. Please try again : ");
                paymentExcess = keyboard.next();  
            }
            else if(!(excess % 10 == 0))
            {
                  System.out.print("The excess must be a multiple of £10. Please try again : ");
                paymentExcess = keyboard.next();       
            }
        }
        excess = Double.parseDouble(paymentExcess);
        
        return excess;
    }
            
    
    static String getPaymentTerm()
    {
        // gets the payment terms from the user
        char payment;
        String term;
        
        System.out.print("Would you like to pay monthly (M) or annually (A) : ");
        payment = Character.toUpperCase(keyboard.next().charAt(0));
       
        // checks that the entered value is a certain character
        while (payment != 'A' && payment != 'M')
        {
            System.out.print("Invalid entry, please try again (A or M) : ");
             payment = Character.toUpperCase(keyboard.next().charAt(0));
             
        }
        
        // assigns the relevant string to the entered character
        if (payment == 'A')
        {
            term = "Annual";
        }
        else
        {
            term = "Monthly"; 
        }
        return term;
        
    }
    
    static void writeToFile(String d, String r, int q, double v, double exc, double p, String t, String n)
    {
        // this method, adds a new policy to the policies.txt file
        PrintWriter output = null;

        File policy = new File("policies.txt");
        
        //checks that the file exists
        try 
        {
            
            FileWriter fw = new FileWriter(policy, true);
            output = new PrintWriter(fw);

        } catch (FileNotFoundException e) 
        {
            System.out.println("Error problem creating the file! Program closing");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Error problem creating the file! Program closing");
            System.exit(0);
        }
       
        // checks for a rejected policy
        if (q > 5 || v > 1000)
        {
            p = -1;
            t = "R";
        }
        else
        {
            p = p *100;
        }

        // writes the policy to the file
        output.print( d + "\t");
        output.print(r + "\t");
        output.print(+ q + "\t");
        output.print((int)v + "\t");
        output.print((int)exc + "\t");
        output.print((int)(p) + "\t");
        output.print(t.charAt(0) + "\t");
        output.println(n);

        output.close();
    }  
    static void fileSummary()
    {
        //calls the display summary method
        displaySummary(1);
    }
    static void monthSummary()
    {
        //calls the display summary method
        displaySummary(2);
    }
    static void searchFile()
    {
        //calls the display summary method
        displaySummary(3);
    }
    static void displaySummary(int option)
    {
        String choice;
        String fileName;
    
        // outputs a menu for the user to make a choice
        System.out.println("1. Current Policies");
        System.out.println("2. Archive Policies");
        System.out.println("0. Return");
        System.out.println("");
        System.out.println("Please select an option : ");
        
        // checks that the input is a valid menu option
        choice = keyboard.nextLine();
        while (!(choice.matches("[0-2]{1}")))
        {
            System.out.print("Invalid option, please try again : ");
            choice = keyboard.nextLine();
            
        }
        
        // assigns the right file name, and passes it and the option it came from, to the read from file method
        switch(choice)
        {
            case "1":
                fileName = "policies.txt";
                readFromFile(fileName, option);
                break;
            case "2":
                fileName = "archive.txt";
                readFromFile(fileName, option);
                break;
            case "0":
                displayMenu();
                break;
        }
    }
    static void readFromFile(String file, int option)
    {
        // variables used in reading the file
        int total = 0;
        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may = 0;
        int jun = 0;
        int jul = 0;
        int aug = 0;
        int sep = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;
        String month = "";
        String search ="";
        
        //checks which option the user came from
        if (option == 2)
        {
            //gets the user to enter a month and checks that it is a valid month
            System.out.print("Please enter the first three letters of a month e.g.Jan :");
             month = keyboard.next().toLowerCase();
            while (!(month.equals("jan")||month.equals("feb")||month.equals("mar")||month.equals("apr")||month.equals("jun")||month.equals("jul")||month.equals("aug")||month.equals("sep")||month.equals("oct")||month.equals("nov")||month.equals("jan")||month.equals("dec")))
            {
                System.out.print("Invalid entry, please try again : ");
                month = keyboard.next().toLowerCase();
            }
           
        }
        else if (option == 3)
        {
            //gets the use to enter a search
            System.out.println("Enter search : ");
            search = keyboard.next().toLowerCase();
        }
        
    
        // checks that the file exists
        if(option == 1)
        {
            try 
        {
            
            File currentFile = new File (file);
            if (currentFile.exists())
            {
              FileReader fr = new FileReader(currentFile);   
              LineNumberReader lnr = new LineNumberReader(fr);
              
                // checks that there is a next line in the file, and counts the number of lines
                while (lnr.readLine() != null)
                {
                    total++;
                }
                
            }
            else 
            {
                System.out.println("File does nto exist.");
            }
        } catch (IOException e){}
        
        }
        
        Scanner input = null; 
        
        // checks that the file exists
        try {
            
            input = new Scanner(new File(file));
            
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            System.exit(1);
        }
        int accepted = 0;
        int quantity = 0;
        double premium = 0;
         
        // checks that the file has more data
        while (input.hasNext()) 
        {
            // gets each piece of data on one row
            String d= input.next();
            String r = input.next();
            int q = input.nextInt();
            int v = input.nextInt();
            int e = input.nextInt();
            int p = input.nextInt();
            String t = input.next();
            String n = input.nextLine();
            
            // checks which option the user came from
            switch (option)
            {
                case 1:
                    // checks if the policy has been rejected
                    if (!(String.valueOf(p).equals("-1")))
                    {
                        // if it hasn't it then counts up the accpeted policies and sums the quantity of each accepted policy
                        accepted++;
                        quantity = quantity + q;
                        // checks if the policy is an annual policy
                        if (t.equals("A"))
                        {
                            //if it is an anual policy, it works out the monthly and adds it to the running total
                            premium = premium + (p/12);
                            
                        }
                        else
                        {
                            // if it isn't an annual policy, it adds the premium to the running total
                            premium = premium + p;
                        }
                    }   break;
                case 2:
                    // checks if the current date contains the month the user has searched for
                    if (d.toLowerCase().contains(month))
                    {
                        // if it does containt the month, it adds one to the running total of policies and then continues the same as option 1
                        total++;
                        if (!(String.valueOf(p).equals("-1")))
                        {
                            accepted++;
                            quantity = quantity + q;
                            if (t.equals("A"))
                            {
                                premium = premium + (p/12);
                                
                            }
                            else
                            {
                                premium = premium + p;
                            }
                            
                        }
                    }   break;
                case 3:
                    // checks if the reference number or the client name contains the user's search
                    if (r.toLowerCase().contains(search) || n.toLowerCase().contains(search) )
                    {
                        //if it does contain the search, it calls the display policy and passes all the policy information in
                        displayPolicy(d, r, q, v, e, p, t, n);
                    }
                    else
                    {
                        System.out.println("No matching policy was found.");
                        input.close();
                        backToMenu();
                    }   break;
                default:
                    break;
            }
            
            //checks the user came from a certain option
            if (option == 1)
            { // checks if the date contains a certain month and adds to that months running total if it does
                if (d.contains("Jan"))
                {
                    jan++;
                }
                else if (d.contains("Feb"))
                {
                    feb++;
                }
                else if (d.contains("Mar"))
                {
                    mar++;
                }
                else if (d.contains("Apr"))
                {
                    apr++;
                }
                else if (d.contains("May"))
                {
                    may++;
                }
                else if(d.contains("Jun"))
                {
                    jun++;
                }
                else if (d.contains("Jul"))
                {
                    jul++;
                }
                else if (d.contains("Aug"))
                {
                    aug++;
                }
                else if (d.contains("Sep"))
                {
                    sep++;
                }
                else if(d.contains("Oct"))
                {
                    oct++;
                }
                else if (d.contains("Nov"))
                {
                    nov++;
                }
                else if (d.contains("Dec"))
                {
                    dec++;
                }
            }
           
            
        }
        // checks which option the user came from
        if (option == 1 || option == 2)
        {
            //converts the premium to an average and to pounds and pence
            premium = (premium / accepted) / 100;
            //outputs the summary
            System.out.println("");
            System.out.println("Total Number of policies: " + total);
            System.out.println("Average number of items (Accepted policies): " + quantity / accepted);
            System.out.println("Average Monthly Premium: " + Math.round(premium*100.0)/100.0);
           
            //checks which option the user came from
            if(option == 1)
            { 
                //outputs the total for each month
                System.out.println("Number of Policies per Month (inc. non-accepted) : ");
                System.out.println("");
                System.out.printf("%-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s", "Jan", "Feb" ,"Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
                System.out.println("");
                System.out.printf("%-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s", jan, feb ,mar,apr, may, jun, jul, aug, sep, oct , nov, dec);
            }
        
        }
         input.close();
         backToMenu();
    }
    
    
    static void backToMenu()
    {
        //asks the user to input c to contiune
        System.out.println("");
        System.out.println("");
        System.out.print("Press C to continue : ");
        char next = Character.toUpperCase(keyboard.next().charAt(0));
        
        //checks that c was inputed
        while (!(next == 'C'))
        {
            System.out.print("Invalid input, please try again : ");
            next = Character.toUpperCase(keyboard.next().charAt(0));
        }
        keyboard.nextLine();
        
        //returns to the menu
        displayMenu();
    }
    static void displayPolicy(String date, String ref, int items, double value, double excess, double premium, String terms, String name)
    {
        String number;
        int limit;
        
         // assigns the relevent string to each number
        if (items == 1)
        {
            number = "One";
        }
        else if (items == 2)
        {
            number = "Two";
        }
        else if (items == 3)
        {
            number = "Three";
        }
        else if (items == 4)
        {
            number = "Four";
        }
        else if (items == 5)
        {
            number = "Five";
        }
        else
        {
            number = Integer.toString(items);
        }
        
        // assigns the relevent limit for the inputted value
        if (value <= 1000 && value > 800)
        {
            limit = 1000;
        }
        else if (value <= 800 && value > 550)
        {
            limit = 800;
        }
        else if ( value <=550 && value >0)
        {
            limit = 550;
        }
        else
        {
            limit = (int) value;
        }
        
        //removes formatting
        name = name.replaceAll("\t", "");
        
        //outputs the policy information
        System.out.println("");
        System.out.println("+=============================================+");
        System.out.println("|                                             |");
        System.out.printf("|" + "%10s %-20s %14s %n", "Client: " , name , " " +" |");
        System.out.println("|                                             |");
        System.out.printf("|" + "%10s %-20s %5s %-25s %n", "Date: " , date, "Ref:" , ref + " |");
        System.out.printf("|" + "%10s %-19s %6s %-5s %2s %n", "Terms: " , terms, "Items:" , number , "|");
        System.out.printf("|" + "%10s £%-20.2f %13s %n", "Excess: " ,excess , " " +" |");
        System.out.println("|                                             |");
        
        // checks for rejected policys and displays the relevent message
        
        if (items > 5)
        {
            System.out.println("|   This claim has been rejected due to the   |");
            System.out.println("|    number of gadgets exceeding the limit    |");
        }
        else if (value > 1000)
        {
            System.out.println("|   This claim has been rejected due to the   |");
            System.out.println("|    value of gadgets exceeding the limit     |");
        }
        else
        {
            System.out.printf("|" + "%9s %-16s %-10s %8s %n", "Annual ","", "Limit per" , " |");
            System.out.printf("|" + "%10s £%-15.2f %1s %2s %8s %n", "Premium: " , premium ,"", "Gadget:" , limit + " |");
        }
        
        System.out.println("|                                             |");
        System.out.println("+=============================================+");
    }
}