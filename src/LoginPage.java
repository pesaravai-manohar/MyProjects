import java.util.*;
import java.sql.*;

class randompass 
{
    public String generate() 
    {

        String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String num = "1234567890";
        String symbols = "!@#$%^&*(){}[]<>?/";
        String combination = UPPER + lower + num + symbols;

        char[] password = new char[8];
        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            password[i] = combination.charAt(r.nextInt(combination.length()));
        }
        return new String(password);
    }
}

class createUser
{
    // scanner
    Scanner input = new Scanner(System.in);
    // defining variables
    String Fname, Lname, phone, password,selectQuery1, getuser,getpassword,password1, password2, username, name,url, UN, PS, selectQuery, DeleteQuery,UpdateQuery, DescQuery, insertQuery;
    Long AccNum, balance;
    int ifscCode=12909;
    boolean check=false;


    public void GetPassword() 
    {
        //generating a random password
        randompass pass = new randompass();
        String G = pass.generate();
        System.out.println("\n\nyour randomly generated password is : " + "" + G);


        //checking whether to use or get new password
        System.out.println("Do you want to use the random password\n If YES please enter Y/y \n If NO please enter N/n");
        char z = input.next().charAt(0);
        if (z == 'y' || z == 'Y') 
        {
            password = G;
            System.out.println("your password has been set successfully");
        } 
            //getting a new password
            else 
            {
            Boolean C = false;
            while (!C) {
                System.out.print("\nplease set your password :");
                password1 = input.next();

                if (password1.length() < 8) {
                    System.out.println("\nNOTE : your password must contain lower case,upper case letter and a symbol");
                    System.out.println("\nyour password must contain atleast 8 letters");
                }

                else {
                    System.out.print("\nplease confirm your password :");
                    password2 = input.next();

                    if (Objects.equals(password1, password2)) {
                        C = true;
                        System.out.println("\nyour password set successfully");
                    } else {
                        System.out.println("\n\nplease check your password and try agin\n");
                    }
                }
            }
            password = password1;
        }
    }

    public void GetNames() 
    {
        System.out.print("\nplease enter your first name :");
        Fname = input.nextLine();
        System.out.print("\nplease enter your last name :");
        Lname = input.nextLine();

        Boolean C = false;
        while (!C) {
            System.out.print("\n");

            if (Fname.length() < 3) {
                if (Lname.length() < 3 || Lname.length() > 16) {
                    System.out.println("\nyour first name and last name must contain atleast 3 letters\n");
                    System.out.print("\nplease enter your first name :");

                    Fname = input.next();

                }

                else {
                    System.out.println("\nyour first name must contain atleast 3 letters\n");
                    System.out.print("\nplease enter your first name :");
                    Fname = input.next();
                }
            }

            else if (Lname.length() < 3) {
                System.out.println("\nyour last name must contain atleast 3 letters\n");
                System.out.print("\nplease enter your last name :");
                Lname = input.nextLine();
            }

            else if (Objects.equals(Fname, Lname)) {
                System.out.println("sorry! first name and last name cannot be same\n\n");
                System.out.print("\nplease enter your first name :");
                Fname = input.nextLine();
                System.out.print("\nplease enter your last name :");
                Lname = input.nextLine();
            }

            else {
                C = true;
                name = Fname + Lname;
            }
        }
    }

    public void GetPhoneNum() 
    {
        System.out.print("please enter your mobile number :");
        phone = input.next();
        Boolean C = false;
        while (!C) {
            if (phone.length() > 10 || phone.length() < 10) {
                System.out.println("Please enter a valid mobile number");
                phone = input.next();
            } else {
                C = true;
            }
        }
    }

    public void GetUserName() {
        System.out.println("please enter your username");
        System.out.println("\n NOTE: spaces are not allowed in username \n username must contain atleast 5 letters");
        username = input.next();
        Boolean C = false;
        while (!C) {
            if (username.length() > 15 || username.length() < 5) {
                System.out.println("Please enter a valid username");
                username = input.next();
            } else {
                C = true;
            }

        }
    }

    public void GenAccNum() {
        Random random = new Random();

        AccNum = random.nextLong();
        AccNum = Math.abs(AccNum);
        AccNum = AccNum % 10000000000L;
    }
    
    public void SaveDetails() 
    {   
//         Fname="ramesh";
//         Lname="pesaravai";
//         username="rameshpesarai";
//         password="ramesh@2003";
//         phone="9494726363";

        url = "jdbc:mysql://127.0.0.1:3306/projects";
        UN = "root";
        PS = "Manohar@2003";
        try {
            int noOfRows = 0;
            Connection connection = DriverManager.getConnection(url, UN, PS);
            Statement statement = connection.createStatement();
            DescQuery = "desc manohar";
            selectQuery="select username,password from logindetails where sno=3";
            DeleteQuery = "delete from logindetails where username ='manohart'";
            insertQuery = String.format("insert into logindetails  values(null,'%s','%s','%s','%s','%s',%s,%d)", Fname, Lname, username, password, phone, AccNum,ifscCode);

            //inserting Statement
            noOfRows = statement.executeUpdate(insertQuery);
            System.out.println("no of rows affected " + noOfRows);
            System.out.println("YOU HAVE SUCCESSFULLY REGISTERED");

        /*    deleting Statements
            noOfRows = statement.executeUpdate(DeleteQuery);
            System.out.println("no of rows affected " + noOfRows);
            noOfRows = statement.executeUpdate(DeleteQuery);
            System.out.println("no of rows deleted " + noOfRows);
            
           ResultSet resultSet=statement.executeQuery(selectQuery);
            while(resultSet.next())
            {
                System.out.println(resultSet.getInt(1)+""+resultSet.getString(2)+""+resultSet.getString(3));

            }*/
            connection.close();
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void SignUp() 
    {
        GetNames();
        GetUserName();
        GetPassword();
        GetPhoneNum();
        GenAccNum();
        SaveDetails();

    }
    public void printUserDetails(String T)
    {
        url = "jdbc:mysql://127.0.0.1:3306/projects";
        UN = "root";
        PS = "Manohar@2003";
        selectQuery="select * from logindetails where username="+"'"+T+"'";
        try
        {
            Connection connection = DriverManager.getConnection(url, UN, PS);
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(selectQuery);
            while(resultSet.next())
            {
                //getting details from database
                Fname    = resultSet.getString("firstname");
                Lname    = resultSet.getString("lastname");
                name     = Fname+" "+Lname;
                password = resultSet.getString("password");
                phone    = resultSet.getString("phonenumber");
                AccNum   = resultSet.getLong("accnum");
                ifscCode = resultSet.getInt("ifscCode");
            }
                //printing details
                System.out.println("\n name             :" + " "+name);
                System.out.println("\n password         :" +" "+password.substring(0,3)+""+"*****");
                System.out.println("\n mobile number    :"+" +91-"+phone);
                System.out.println("\n account number   :" + " "+AccNum);
                System.out.println("\n branch IFSC code :" + " "+ifscCode);
                System.out.println("\n account balance  :" + " "+balance);
            connection.close();
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

    }
  
    public void Login(){
        url = "jdbc:mysql://127.0.0.1:3306/projects";
        UN = "root";
        PS = "Manohar@2003";
        selectQuery1="select * from login";
        System.out.println("please continue to login ");
        System.out.println("please enter your username");
        String X =input.next();
        System.out.println("please enter your password");
        String H=input.next();
        selectQuery="select username,password from logindetails where username="+"'"+X+"'";
        try
        {
            int noOfRows = 0;
            Connection connection = DriverManager.getConnection(url, UN, PS);
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(selectQuery);
                while(resultSet.next())
                {
                    getuser= resultSet.getString(1);
                    getpassword =resultSet.getString(2);
                }
                connection.close();
                if(Objects.equals(getuser, X)&& Objects.equals(getpassword, H))
                {
                    System.out.println("\n\n LOGIN SUCCESSFUL\n\n");
                    printUserDetails(getuser);
                }
        }

        catch (Exception e) {
            System.out.println(e);
        }

    }
    
}
    



public class LoginPage {
    public static void startPage(){
        //creating a new user
        createUser newuser = new createUser();
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n\t\t\t\t\t\t\t WELCOME TO SBI\n ");
        //login or signup
        System.out.println("\n\npress L to login & press S to signup");
        char y = input.next().charAt(0);
        if (y == 'L' || y == 'l')
        {
            newuser.Login();
        }

        else if (y == 's' || y == 'S')
        {
            System.out.println("\n\nplease enter the following details to sign");
            newuser.SignUp();
            newuser.Login();
        }
        input.close();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        startPage();

    }
}
