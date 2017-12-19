package View;

import Exceptions.InvalidInputException;
import Exceptions.ReturnException;

public interface View {
    public void printMenu() throws InvalidInputException, ReturnException;
    public void run(Object o);
}
