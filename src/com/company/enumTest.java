package com.company;

public class enumTest {
    public static void main(String args[]){
        Gender man = Gender.MALE;
        man.dip();

        System.out.println("The name of the person is :"+man.getName());

    }
}
interface GenderDisp{
    void dip();
}
enum Gender implements  GenderDisp{
    MALE("male"){
        @Override
        public void dip() {
            System.out.println("This is a male");
        }
    },
    FEMALE("female"){
        @Override
        public void dip() {
            System.out.println("This is a female");
        }
    };
    private final String name;
    private Gender(String name){
        this.name = name;

    }
//    public void setName(String name){
//        if(this == MALE){
//            this.name = "male"+name;
//        }
//        else if(this == FEMALE){
//            this.name = "female" + name;
//        }
//    }

    public String getName() {
        return name;
    }
}