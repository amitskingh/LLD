package p08_decorator_design_pattern;

class Main {

    public static void main(String[] args) {
        Coffee coffee = new PlainCoffee();
        System.out.println(coffee.description() + " $" + coffee.cost());

        Coffee milkCoffee = new MilkDecorator(new PlainCoffee());
        System.out.println(milkCoffee.description() + " $" + milkCoffee.cost());

        Coffee sugarMilkCoffee = new SugarDecorator(new MilkDecorator(new PlainCoffee()));
        System.out.println(sugarMilkCoffee.description() + " $" + sugarMilkCoffee.cost());

        Coffee chocolateMilkCoffee = new ChocolateDecorator(new MilkDecorator(new PlainCoffee()));
        System.out.println(chocolateMilkCoffee.description() + " $" + chocolateMilkCoffee.cost());
    }

}
