package p08_decorator_design_pattern;

class PlainCoffee implements Coffee {

    @Override
    public int cost() {
        return 100;
    }

    @Override
    public String description() {
        return "Plain Coffee";
    }
    
}
