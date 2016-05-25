package csd;
 
import java.util.Scanner;
 
public class Keyboard {
    private Scanner m_kb;
     
    public Keyboard()
    {
        m_kb = new Scanner(System.in);
    }
     
    public String getLine(String msg)
    {
        System.out.print(msg);
        return  m_kb.nextLine();    
    }       
     
    public String getLine()
    {
        return getLine("");
    }
     
    public int getInt(String msg, String errorMessage)
    {
        int result = 0;
        boolean isValid = false;
         
        do {
            try {
                System.out.print(msg);
                result = Integer.parseInt(m_kb.nextLine());
                 
                isValid = true;
            }
            catch (Throwable ex) {
                System.out.print(errorMessage);
            }           
        } while (!isValid);     
         
        return result;          
    }
     
    public int getInt(String msg)
    {
        return getInt(msg, "");     
    }   
     
    public int getInt()
    {
        return getInt("", "");
    }
     
    public long getLong(String msg, String errorMessage)
    {
        long result = 0;
        boolean isValid = false;
         
        do {
            try {
                System.out.print(msg);
                result = Long.parseLong(m_kb.nextLine());
                 
                isValid = true;
            }
            catch (Throwable ex) {
                System.out.print(errorMessage);
            }           
        } while (!isValid);     
         
        return result;          
    }
     
    public long getLong(String msg)
    {
        return getLong(msg, "");        
    }   
     
    public long getLong()
    {
        return getLong("", "");
    }
     
    public double getDouble(String msg, String errorMessage)
    {
        double result = 0;
        boolean isValid = false;
         
        do {
            try {
                System.out.print(msg);
                result = Double.parseDouble(m_kb.nextLine());
                 
                isValid = true;
            }
            catch (Throwable ex) {
                System.out.print(errorMessage);
            }           
        } while (!isValid);     
         
        return result;          
    }
     
    public double getDouble(String msg)
    {
        return getDouble(msg, "");      
    }   
     
    public double getDouble()
    {
        return getDouble("", "");
    }
     
    //...
}