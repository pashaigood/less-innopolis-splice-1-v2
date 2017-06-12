package less.android;


public class Main {

    public static void main(String[] args) {
        Generator generator = new Generator();
        Listener listener = new Listener(generator);

        generator.start();
        listener.start();
    }
}
