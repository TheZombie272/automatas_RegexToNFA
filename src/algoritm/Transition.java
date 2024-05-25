package algoritm;

// Class Transition (Transición)
class Transition {
    int sourceState; 
    int targetState; //There are only three symbols
    Character symbol;

    public Transition(int sourceState, int targetState, Character symbol) {
        this.sourceState = sourceState;
        this.targetState = targetState;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "(" + sourceState + ", " + targetState + ", " + symbol + ")";
    }
}