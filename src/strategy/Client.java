package strategy;

public class Client {
    private final ValidationStrategy strategy;

    public Client(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s ) {
        return strategy.execute(s);
    }
}
