package p11_factory_abstract_method_pattern;

class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Windows Button");
    }

}
