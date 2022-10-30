

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class atm {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //scanner class to input data
        
        
    
        customer cus = new customer();  // define customer class and its object, to be used later in the class 
        cus.setaccntno_details();
        cus.setacctpin();

        display ds; //display and transt object
        transt t;

        int total_cash =100; //class variable representing total cash available in the atm

        while ( true) {   // loop to continuous run the atm machine 

            System.out.println(" Welcome to HDFC atm" + '\n');
            System.out.println("Please select one 1. admin or 2. customer");
            int opt = sc.nextInt();   // giving two option either customer or bank employee
            if (opt == 1) {


                System.out.println(" please enter the key ");
                int key = sc.nextInt( );
                if(key == 201010) // specific key to enter the amount in atm 
                { 
                    System.out.println("enter the amount to deposit");
                    total_cash+=sc.nextInt();


                }
                else 
                System.out.println("wrong input");


            } 
            
            
            
            
            else if (opt == 2) {   //customer options 
                System.out.println(" Please enter the account number ");
                int acctNo = sc.nextInt();

                


                int CountErr = 0;

                 int ack =cus.accnt_details(acctNo); // checking if the account number is right. Used sample getter which can be upgraded

                if (ack !=-1 ) {
                    while (CountErr < 5) {
                        System.out.println("Please enter the pin");
                        int pin = sc.nextInt();
                        double n;
                        boolean check = false;
                        if (cus.pin_check(pin ,ack)) // pin checking 
                        {
                            
                            check = true;
                            ds = new display(check); //showing available transcation options user can opt 
                            int ot = sc.nextInt();
                            ds = new display(ot);

                            double bs = cus.getbalance(acctNo); // need to cchange 

                            switch (ot) { //transaction as per the customer requirements require new class

                                case 1:
                                    System.out.print(bs); //display the balance of the customer
                                    // t = new View_balnce();
                                    //t.view_blnc( bs);
                                    break;

                                case 2:                      // display and perform withdraw operations
                                    System.out.print("enter amount   ");
                                    int aw = sc.nextInt();
                                    t = new transt();
                                    n = t.withdraw(bs, aw);
                                    cus.setbalance(acctNo, n);

                                    break;


                                case 3:                   // display and performs deposit operations 
                                    System.out.print("enter amount   ");
                                    int ad = sc.nextInt();

                                    t = new transt();
                                    n = t.deposit(bs, ad);
                                    cus.setbalance(acctNo, n);
                                    break;



                            }


                            break;
                        } else {    // in case of wrong pin the following options is displayed 
                            ds = new display();
                            System.out.println("errors try remaining " + (5 - CountErr));
                            CountErr++;

                        } 

                        if (CountErr == 5) {
                            System.out.println(" please contact bank...maximum tries reached");
                        } //error counting  IF reached maximum 



                    }
                }
                else
                System.out.println("please enter valid account number");



                

            }
            
            if(total_cash<100 )
            {
               System.out.println("OOPs out of cash ");
               continue;
            }
        }
    }

 // Closing the stream  

}




class customer {

    private int acctNo[] = new int[100]; //accnt number
    private int pinNo[] = new int[100];  // pin 
    private double balance[] = new double[100]; //balance

   
   
   
    void setaccntno_details() {
        for (int i = 1; i < 100; i++) {
            this.acctNo[i - 1] = i * 10000;  // setting default account number 

        }

    }



    protected int accnt_details(int acctno) {
        for (int i = 0; i < 100; i++)   
            if (acctno == acctNo[i])  
                return i;     //checking if the acct number exists in the database 


        return -1;


    }




    public void setacctpin() {  // setter for  deafault pin which is just twice of account number

         for (int i = 1; i < 100; i++) {
            this.pinNo[i - 1] = i * 20000;

        }
    }


    protected boolean pin_check(int pin, int acct ) { // checking if the pin defined is right in corresp to acct no.
        
        if (pin == pinNo[acct])
            return true;


        return false;

    }



    protected double getbalance(int acctno) {  // getter functions 
        int i = 0;
        for (i = 0; i < 100; i++) {
            if (this.acctNo[i] == acctno)
                break;
        }

        return balance[i];
    }





   


    protected void setbalance(int acctno, double amt) { //setter for balance 

        int i;
        for (i = 0; i < 100; i++)
            if (acctno == acctNo[i])
                break;
        balance[i] = amt;
    }

 
}



// display class to display various options 
class display {
    display() {
        System.out.println(" the pin entered is wrong, Please re-enter " + '\n');

    }
    display(boolean check) {
        System.out.println("Please choose the following  ");
        System.out.println(" 1  View balance ");
        System.out.println(" 2  Withdrawl Money");
        System.out.println(" 3  Deposits funds");


    }

    display(int cs) {



        switch (cs) {

            //get balance by calling 

            case 1:
                System.out.println(" Your current balance ");


                break;
            case 2:
                System.out.println("  Please enter the amount to --- withdraw  ");

                break;
            case 3:
                System.out.println(" please enter the amount to deposit  ");

                break;
        }
    }
}






 // various interfaces for different form of transactions

interface Withdraw {
    double withdraw(double bs, double amt);

}

interface View_balnce {
    void view_blnc(double al);
}

interface deposit {
    double deposit(double bs, double amt);
}





 // to perform transactions 

class transt implements Withdraw, View_balnce, deposit {



    

//function to withdraw
    public double withdraw(double bs, double aw) {



        try {  
            FileWriter fwrite = new FileWriter("C:withdraw.txt");  
            // writing the content into the FileOperationExample.txt file  
               
            
            
          
        if (bs >= aw) {
            fwrite.append("present blnc " + bs + "   withdraw amt   "+ aw );  
            
             fwrite.close();
            
            System.out.println("present balance  " + bs);

            System.out.println(" Amt to withdraw  " + aw);

            bs = bs - aw;
            System.out.println("Changed balance  " + bs);

            return bs;



        } else

       { fwrite.append("insuff funds"); 
       fwrite.close();
          
        
          
        
        System.out.println("Insufficient funds  ");
        return bs;}
        
    }

        catch (IOException e) {  
            System.out.println("Unexpected error occurred");  
            e.printStackTrace();  
            } 
            return 0;

    }




    public void view_blnc(double bln) {
        System.out.println(bln);

    }



//function to deposit 
   public double deposit(double bs, double aw) {
    try {  
        FileWriter fwrite1 = new FileWriter("C:deposit.txt");  
          fwrite1.append("present blnc " + bs + "  deposit amt   "+ aw );  
            
             fwrite1.close();

        System.out.println("present balance  " + bs);

        System.out.println(" Amt to deposit  " + aw);

        bs = bs + aw;

        // need to shift this print statement 
        System.out.println("Changed balance  " + bs);

        return bs;
    }

    
    catch (IOException e) {  
        System.out.println("Unexpected error occurred");  
        e.printStackTrace();  
        } 
        return 0;





}}
// class Withdraw extends transt