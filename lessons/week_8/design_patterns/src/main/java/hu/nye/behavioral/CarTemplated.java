package hu.nye.behavioral;

public abstract class CarTemplated {
    public final void drive() {
        System.out.print("Get in... ");
        if (start()) {
            use();
            stop();
        }
        System.out.println("get out");
    }

    public boolean start() {
        System.out.print("start... ");
        return true;
    }

    public abstract void use();

    public void stop() {
        System.out.print("stop... ");
    }
}
