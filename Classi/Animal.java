package Classi;

abstract class Animal{
  public abstract void makeSound();

  public void sleep(){
    System.out.println("ZZZ");
  }
}

class Pig extends Animal{
  public void makeSound(){
    System.out.println("Oink");
  }
}