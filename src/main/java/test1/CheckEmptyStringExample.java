package test1;

import org.apache.commons.lang3.StringUtils;

public class CheckEmptyStringExample 
{  
  public static void main(String[] args)
  {
     String string1 = "";
     String string2 = "\t\r\n";
     String string3 = " ";
     String string4 = null;
     String string5 = "Hi"; 
     System.out.println("\nString one is empty? " + StringUtils.isBlank(string1));
     System.out.println("String one is not empty? " + StringUtils.isNotBlank(string1));
     System.out.println("\nString two is empty? " +  StringUtils.isBlank(string2));
     System.out.println("String two is not empty?" + StringUtils.isNotBlank(string2));
     System.out.println("\nString three is empty?" + StringUtils.isBlank(string3));
     System.out.println("String three is not empty?" + StringUtils.isNotBlank(string3));
     System.out.println("\nString four is empty?" +  StringUtils.isBlank(string4));
     System.out.println("String four is not empty?" + StringUtils.isNotBlank(string4));
     System.out.println("\nString five is empty?" + StringUtils.isBlank(string5));
     System.out.println("String five is not empty?" + StringUtils.isNotBlank(string5)); 
  }
}