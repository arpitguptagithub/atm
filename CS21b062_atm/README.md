                                                                        ATM 

Functionality provided:

1. Deposit -- deposits a given amount in the Balance (amount should be greater than 0)
2. Withdraw -- withdraws a given amount from the Balance (the amount should be less than the current Balance)
3. Check Balance -- display the current savings of the individual.

Concepts used :

The Objected Oriented Programming Systems in JAVA is the primary tool used in the making of this project because the concepts of OOPS can be 
efficiently used in the making of different projects which requires data hiding and security functions. This code uses OOPS concepts 
such as Abstraction, Encapsulation, Inheritance, and Polymorphism , the implementation of each technique is defined later while giving the class description.

Some challenges were also faced while doing the project, like giving the limited user chances to input the correct combination of Account number
and password. For this, the program uses iteration along with a calling feature, which is time complex but can be reduced using stack and queue
features. Moreover, File handling is added to the project to store data in text format. So that in case there is a system failure, the program
details for the transactions is still saved. The pin and account number are not reserved for security reasons. Another problem that occured while 
creating the code is selection of the right access specifiers while saving the pins and account numbers.   

The four main classes used in here is:

ATM: It is the main class of the entire program, consisting of main function objects from other classes inside the primary function, and performs
significant operations by calling objects.

Customer: This class consists of getters, setters, and validation checking for the account number and Pin regarding it. It can be extended to
keep more details of customers. This class also consists of different access specifier functions resulting in data encapsulation.

Transaction: This class consists of all the functions that provide the basic functionality to the ATM, such as deposit, withdraw and check Balance. 
This class also implements different interfaces to carry out the functionality. The other interfaces include withdraw, deposit and check Balance
which provides the functionality of abstraction to the code.

Display: This class consists of display details for the ATM, i.e., all the commands to be used in the GUI representation of the ATM in the terminal.
Moreover, this class consists of constructor overloading showcasing polymorphism
