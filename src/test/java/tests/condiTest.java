package tests;

/*public class condiTest {

    public static void main(String[]argu){

     int age = 18;
     if (age >=18){
        System.out.println("pass:");
     }else{
        System.out.println("not eligable:");
     }
    }
} */

/*public class condiTest {

   public static void main(String[]argu){

       boolean loginsuccess =true;

       if (loginsuccess) {
           System.out.println("login successful:");

           }
             else{
               System.out.println("login failed: ");
             
       }
   }
       


} */

public class condiTest {
    public static void main (String[]args){

        String username ="admin";
        int password = 12345;

        if (username.equals("admin")) {
            if (password == 1234) {
                System.out.println("loginsuccessful");
            } else {
                System.out.println("wrong password");
            }
        } else {
            System.out.println("wrong username");
        }
    }
 }
