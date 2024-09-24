package operator;

public class AddOperator implements Operator{
    @Override
    public int calculate(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }
}
