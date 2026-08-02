package P08_decorator_design_pattern;

class MilkDecorator extends CoffeeDecorator {

    MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {
        return coffee.cost() + 20;
    }

    @Override
    public String description() {
        return coffee.description() + " + milk";
    }

}