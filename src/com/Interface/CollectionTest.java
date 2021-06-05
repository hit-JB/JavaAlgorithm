package com.Interface;
import com.company.Interfacetest;

import java.awt.desktop.SystemSleepEvent;
import java.util.*;
import java.time.*;
public class CollectionTest {
    public static void main(String[] args) {
        List<Employee> workers = new ArrayList<>();
        Double[] salary1 = {1.,2.,3.};
        Integer[] salary2 = {3,4,5};
        workers.add(new Employee<>(salary1[0]));
        workers.add(new Employee<>(salary2[0]));
        workers.add(new Employee("Salary"));
        Collection<Employee> workerTest = Collections.unmodifiableCollection(workers);
        for(Employee e :workers){
            System.out.println("Salary "+e.getSalary());
        }
        for(Employee e : workerTest){
            System.out.println("Salary "+e.getSalary());
        }
        Object s = new Employee<String>("Salary");

//        Comparator<Manager<Integer,Double>> com = new Comparator<Manager<Integer, Double>>() {
//            @Override
//            public int compare(Manager<Integer, Double> o1, Manager<Integer, Double> o2) {
//                return o1.getExtraSalary()-o2.getExtraSalary() ;
//            }
//        };
//        ArrayList<Manager<Integer,Double>> listTest= new ArrayList<>();
//        String[] alpha = {"A", "B", "C"};
//        Double[] salary = {5.1, 4.1, 3.1};
//        Integer[] extraSalary = {5, 2, 3};
//        SortedMap<Integer,Employee<Double> >treeTest= new TreeMap<>((o1,o2)->o1-o2>0?1:0);
//        Map<Integer, Employee<Double>> employees = new HashMap<>();
//        Map<Integer, Manager<Integer,Double>> managers = new HashMap<>();
//        for (int i = 0; i < salary.length; i++) {
//            employees.put(i, new Employee<>(salary[i]));
//            managers.put(i+3,new Manager<>(salary[i],extraSalary[i]));
//            listTest.add( new Manager<>(salary[i],extraSalary[i]));
//        }
//        listTest.sort(com);
//        for(Manager e:listTest){
//            System.out.println("Salary"+e.getSalary());
//        }
//        System.out.println("Array list"+listTest);
    }
}
class Employee<T> implements Comparable<Employee<T>> {
    private T salary;
    Employee(T initialSalary){
        salary = initialSalary;
    }
    Employee(){
        salary = null;
    }
    public T getSalary(){
        return salary;
    }

    @Override
    public int compareTo(Employee<T> o) {
        return this.hashCode()-o.hashCode();
    }

}
class Manager<E,T> extends Employee<T>{
    private E extraSalary;
    Manager(){
        super();
        extraSalary = null;
    }
    Manager(T initialSalary,E extraSalary){
        super(initialSalary);
        this.extraSalary = extraSalary;
    }
    public static <K,V> V getValue(K keys,V value){
        System.out.println("The keys is "+ keys+"Value is "+ value);
        return value;
    }
    public E getExtraSalary() {
        return extraSalary;
    }
}
class Student{
    private int score;
    Student(int
                    score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }
}
