import java.util.*;
import java.io.*;

// ////////////////   Packing Activity  ////////////////////////////////
class Packing
{
    public void Packer() 
    {
        try
        {
            Scanner sobj = new Scanner(System.in);

            System.out.println("----------------- Packing Activity ------------------");
            System.out.println();

            System.out.println("Enter the name of Directory that you want to open for packing : ");
            String FolderName = sobj.nextLine();

            File fobj = new File(FolderName);

            System.out.println("Enter the name of packed file that you want to create : ");
            String PackedFile = sobj.nextLine();

            File Packobj = new File(PackedFile);
            
            boolean bret = Packobj.createNewFile();
            if(bret == false)
            {
                System.out.println("Unable to create packed file");
                return;
            }

            FileOutputStream foobj = new FileOutputStream(Packobj);

            if(fobj.exists())
            {
                int i = 0, j = 0;
                int iCount = 0;

                File Arr[] = fobj.listFiles();

                
                String Header = null;
                int iRet = 0;
                byte Buffer[] = new byte[1024];
                FileInputStream fiobj = null;
                
                for(i = 0; i < Arr.length; i++)
                {
                    Header = Arr[i].getName();
                    
                    if(Header.endsWith(".txt"))
                    {
                        System.out.println("File packed with name : "+Header);
                        
                        Header = Header + " " + Arr[i].length();

                        for(j = Header.length(); j < 100; j++)
                        {
                            Header = Header + " ";
                        }

                        foobj.write(Header.getBytes(),0,100);

                        fiobj = new FileInputStream(Arr[i]);

                        while((iRet = fiobj.read(Buffer))!= -1)
                        {
                            foobj.write(Buffer,0,iRet);
                        }

                        System.out.println("Thank you ")
                        fiobj.close();
                        iCount++;

                    }
                }

                System.out.println();
                System.out.println("-----------------------------------------------------");
                System.out.println("Packing activity completed..");
                System.out.println("Number of files scan : "+Arr.length);
                System.out.println("Number of files packed : "+iCount);
                System.out.println("-----------------------------------------------------");

              
                System.out.println("Thank you for using Marvellous Packer Unpacker tool");

                foobj.close();
            }
            else
            {
                System.out.println("There is no such directory");
            }
        }
        catch(Exception e)
        {

        }

    }
}

// ////////////////   Unpacking Activity  ////////////////////////////////

class Unpacking
{
    public void UnPacker() 
    {
        try
        {
            Scanner sobj = new Scanner(System.in);
            
            byte Header[] = new byte[100];
            int iRet = 0;
            String HeaderX = null;
            File obj = null;
            int FileSize = 0;
            FileOutputStream foobj = null;
            int iCount = 0;
            
            System.out.println("---------------- Unpacking Activity -----------------");
            System.out.println();

            System.out.println("Enter the name of Packed that you want to open : ");
            String PackedFile = sobj.nextLine();

            File fobj = new File(PackedFile);

            if(!fobj.exists())
            {
                System.out.println("Unable to proceed as Packed file is missing...");
                return;
            }  

            FileInputStream fiobj = new FileInputStream(fobj);

            while((iRet = fiobj.read(Header,0,100)) > 0)
            {
                HeaderX = new String(Header);
                HeaderX = HeaderX.trim();

                String Tokens[] = HeaderX.split(" ");

                obj = new File(Tokens[0]);
                System.out.println("File drop with name : "+Tokens[0]);

                obj.createNewFile();

                FileSize = Integer.parseInt(Tokens[1]);
                byte Buffer[] = new byte[FileSize];

                fiobj.read(Buffer,0,FileSize);

                foobj = new FileOutputStream(obj);
                foobj.write(Buffer,0,FileSize);

                foobj.close();
                iCount++;
            }

            System.out.println();
            System.out.println("-----------------------------------------------------");
            System.out.println("Unpacking activity completed..");
            System.out.println("Number of files unpacked : "+iCount);
            System.out.println("-----------------------------------------------------");

            System.out.println("Thank you for using Marvellous Packer Unpacker tool");
            System.out.println();

            fiobj.close();
        }
        catch(Exception e)
        {

        }
    }
}

public class FilePackerUnpacker 
{
     public static void main(String args[]) throws Exception 
     {
         int iChoice = 0;
         int No = 0;

        System.out.println("-----------------------------------------------------");
        System.out.println("------- Marvellous Packer Unpacker CUI Module -------");
        System.out.println("-----------------------------------------------------");

          while(iChoice != 4)
          {
             System.out.println();
             System.out.println("Please select your choice : ");
             System.out.println("1 : To Display help");
             System.out.println("2 : Packing Activity");
             System.out.println("3 : UnPacking Activity");
             System.out.println("4 : Exit");
             
            Scanner sobj = new Scanner(System.in);
            iChoice = sobj.nextInt();

            switch(iChoice)
            {
                case 1:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("This application is used to pack and unpack files.");
                    System.out.println();
                    System.out.println("Packing  :");
                    System.out.println(" - Enter directory name");
                    System.out.println(" - Enter name of packed file");
                    System.out.println(" - All .txt files will be packed");
                    System.out.println();
                    System.out.println("Unpacking :");
                    System.out.println(" - Enter packed file name");
                    System.out.println(" - All files will be restored");
                    System.out.println("-----------------------------------------------------");
                    break;


                case 2:
                    Packing pobj = new Packing();
                    pobj.Packer();
                    break;

                case 3:
                    Unpacking unobj = new Unpacking();
                    unobj.UnPacker();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Thank you for using Marvellous Packer Unpacker tool");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");

            }
        }
    }
}


    

