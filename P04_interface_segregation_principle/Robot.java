package P04_interface_segregation_principle;

public class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }
}
