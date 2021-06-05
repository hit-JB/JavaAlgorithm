package com.company;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;
public class Main {
    public static void display(){
        Date data = new Date();
        LocalDate newYeas = LocalDate.of(1999,12,31);
        System.out.printf("Year:%d,Month:%d,Day:%d",newYeas.getYear(),newYeas.getMonthValue(),newYeas.getDayOfMonth());
    }
    public static void main(String[] args) {
       Comparator<String> comp = new LengthComparator();
       String[] friends = {"Peter","Paul","Marry"};
       Arrays.sort(friends,comp);
       for(String f:friends){
           System.out.println("Name:"+f);
       }
        }
    public static class Employee implements Comparable<Employee>,Cloneable
    {
        private String name;
        private double salary;
        private LocalDate hireDay;
        public Employee(){
            this.name = "James Hrden";
            this.salary=1000;
            this.hireDay = LocalDate.of(2000,12,12);
        }
        public Employee(String name,double salary,int year,int month,int day) {
            this.name = name;
            this.salary = salary;
            this.hireDay = LocalDate.of(year, month, day);
        }
        public String getName(){
            return this.name;
        }
        public double getSalary(){
            return this.salary;
        }
        public LocalDate getHireDay(){
            return this.hireDay;
        }
        public void raiseSalary(double byPercent){
            double raise = this.salary * byPercent /100;
            this.salary += raise;
        }
        public final int  compareTo(Employee other){
            return Double.compare(salary,other.salary);
        }
        public Employee clone() throws  CloneNotSupportedException{
            Employee cloned = (Employee) super.clone();
            return cloned;
        }
    }
    public static class  Manager extends Employee{
        private double bonus;

        public Manager(String name,double salary,int year,int month,int day){
            super(name,salary,year,month,day);
            this.bonus = 0;
        }
        public double getSalary(){
            double baseSalary = super.getSalary();
            return baseSalary+this.bonus;
        }
        public String setName(String s){
            String nameCopy = super.getName();
            return s+nameCopy;
        }
        public void setBonus(double b){
            this.bonus = b;
        }
    }
    public static class Boss extends Employee{
        private final double cash;
        public Boss(String name,double salary,int year,int month,int day){
            super(name,salary,year,month,day);
            this.cash = salary * 12;
        }

        public double getCash() {
            return cash;
        }
    }
    public enum Size{
        SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");
        private Size(String abbreviation){
            this.abbreviation = abbreviation;
        }
        public String getAbbreviation(){
            return this.abbreviation;
        }
        private String abbreviation;
    }
    public static class TimePrinter implements ActionListener{
        public void actionPerformed(ActionEvent event){
            System.out.println("At the tone,the time is"+new Date());
            Toolkit.getDefaultToolkit().beep();
        }
    }

}
class LengthComparator implements Comparator<String>{
    public int compare(String first ,String second){
        return first.length() -second.length();
    }
}

