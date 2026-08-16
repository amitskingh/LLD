package p11_factory_abstract_method_pattern;

class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Windows Checkbox");
    }
}
