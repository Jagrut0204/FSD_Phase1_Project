import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;

public class Phase_Proj {
	static String Folder;
    File fold_nm;

    public Phase_Proj() {
        Folder = System.getProperty("user.dir");
        fold_nm = new File(Folder+"/files");
        if (!fold_nm.exists())
            fold_nm.mkdirs();
        System.out.println("Folder:"+ fold_nm.getAbsolutePath());
    }

    private static final String Proj_Info  =
            "\n		SimpliLearn Phase1 Assessment Project by Jagrut Goenka";
                   

    private static final String Main_Menu =
            "\nMAIN MENU - Select any of the following: \n"+
                    "1 : Sort files in ascending order\n"+
                    "2 : Adding file, Deleting file or Searching file in folder\n"+
                    "3 : Quit Program";

    private static final String Sub_Menu =
            "   \nSelect any of the following: \n"+
                    "   1 : Add a file in folder\n"+
                    "   2 : Delete a file from the folder \n"+
                    "   3 : Search a file in the folder\n"+
                    "   4 : Return back to the previous menu ";

    void Init_Menu () {
        System.out.println(Main_Menu);
        try{
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option){
                case 1 : {
                    Asc_Operat();
                    Init_Menu ();
                }
                case 2 : {
                    Inner_Menu();
                }
                case 3 : {
                    System.out.println("\n END ");
                    System.exit(0);
                }
                default: Init_Menu ();
            }
           scanner.close();
        }
        catch (Exception e){
            System.out.println("Enter a valid choice from 1.2.3");
            Init_Menu ();
        }
    }

    void Inner_Menu() {
        System.out.println(Sub_Menu);
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case '1' : {
                    System.out.print("1 : Add a file in folder : \t ");
                    String filename = scanner.next().trim().toLowerCase();
                    Add_Operat(filename);
                    break;
                }
                case '2' : {
                    System.out.print("2 : Delete a file from the folder : \t");
                    String filename = scanner.next().trim();
                    Delete_Operat(filename);
                    break;
                }
                case '3' : {
                    System.out.print("3 : Search a file in the folder  \t");
                    String filename = scanner.next().trim();
                    Search_Operat(filename);
                    break;
                }
                case '4' : {
                    System.out.println("4 : Return back to the previous menu ");
                    Init_Menu ();
                    break;
                }
                default : System.out.println("Please enter a, b, c or d");
            }
            Inner_Menu();
            scanner.close();
            
        }
        catch (Exception e){
            System.out.println("Enter a valid choice from 1.2.3.4");
            Inner_Menu();
        }
    }

    void Asc_Operat() {
        if (fold_nm.list().length==0)
            System.out.println("The folder is empty");
        else {
            String[] list = fold_nm.list();
            System.out.println("The files in "+ fold_nm +" are :");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }
    void Delete_Operat(String filename) {
        File filepath = new File(fold_nm +"/"+filename);
        String[] list = fold_nm.list();
        for (String file: list) {
            if (filename.equals(file) && filepath.delete()) {
                System.out.println("File " + filename + " deleted from " + fold_nm);
                return;
            }
        }
        System.out.println("Delete Operation failed. FILE NOT FOUND");
    }

    void Search_Operat(String filename) {
        String[] list = fold_nm.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("File " + filename + " is located at  " + fold_nm);
                return;
            }
        }
        System.out.println("File not found ");
    }
    void Add_Operat(String filename) throws IOException {
        File filepath = new File(fold_nm +"/"+filename);
        String[] list = fold_nm.list();
        for (String file: list) {
            if (filename.equalsIgnoreCase(file)) {
                System.out.println("File " + filename + " already exists at " + fold_nm);
                return;
            }
        }
        filepath.createNewFile();
        System.out.println("File "+filename+" added to "+ fold_nm);
    }


    public static void main(String[] args) {
        System.out.println(Proj_Info );
        Phase_Proj menu = new Phase_Proj();
        menu.Init_Menu ();
    }

}
