package p08_decorator_design_pattern;

class ChocolateDecorator extends CoffeeDecorator {

    ChocolateDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {
        return coffee.cost() + 40;
    }

    @Override
    public String description() {
        return coffee.description() + " + chocolate";
    }
    
}
