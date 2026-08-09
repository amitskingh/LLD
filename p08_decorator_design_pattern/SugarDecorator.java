package p08_decorator_design_pattern;

class SugarDecorator extends CoffeeDecorator {

    SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {
        return coffee.cost() + 10;
    }

    @Override
    public String description() {
        return coffee.description() + " + sugar";
    }

}
