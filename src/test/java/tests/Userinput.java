package tests;

 import java.util.Scanner; 
 
  public class Userinput{
    public static void main(String[]args){

      Scanner sc = new Scanner(System.in);


        
        System.out.print("ankit:");
        String name = sc.nextLine();

        System.out.print("age:");
        int age = sc.nextInt();

        System.out.println(" user details:");
        System.out.println("   name:" + name);
        System.out.println(" age:" + age);
            
            sc.close();

        }
      }