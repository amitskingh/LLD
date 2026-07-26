package P04_interface_segregation_principle;

public class Human implements Workable, Eateable {
    @Override
    public void work() {
        System.out.println("Human is working");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating");
    }

}
