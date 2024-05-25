package algoritm;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// Class NFA (Autómata Finito No Determinista)
    public class NFA {
        Set<Integer> states;
        Set<Character> alphabet;
        Stack<Transition> transitions;
        int initialState;
        int[] acceptState;
        static char lastestSymbolTransition;

        public NFA() {
            states = new HashSet<>();
            alphabet = new HashSet<>();
            transitions = new Stack<>();
            initialState = 0;
            acceptState[0] = 1;
            states.add(initialState);
            states.add(1);
        }

        public void addTransition(char symbol) { //Crea una transición
            alphabet.add(symbol);
            lastestSymbolTransition=symbol;
            transitions.push(new Transition(initialState, acceptState, symbol));
        }

        public void concatenate(NFA otherNFA) {
            int currentStateCount = states.size()-1;
            for (int state : otherNFA.states) {
                states.add(state + currentStateCount);
            }
            for (Transition transition : otherNFA.transitions) {
                transitions.push(new Transition(transition.sourceState + currentStateCount,
                                                transition.targetState + currentStateCount,
                                                transition.symbol));
            }
            //transitions.add(new Transition(acceptState, otherNFA.initialState + currentStateCount, null));
            acceptState = otherNFA.acceptState + currentStateCount;
            alphabet.addAll(otherNFA.alphabet);

        }

        public void alternate(NFA nfa) {
            System.out.println(nfa);

            for (Transition t : nfa.transitions) {
                if(t.targetState==nfa.transitions.size()){
                    transitions.push(t);
                }
            }

        }

        public void kleeneClosure() {
            transitions.pop();
            states.remove(states.size()-1);
            int newInitialState = states.size()-1;
            int newAcceptState = newInitialState;
            states.add(newAcceptState);
            transitions.add(new Transition(newInitialState, newInitialState, lastestSymbolTransition));
            acceptState = newAcceptState;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("States: ").append(states).append("\n");
            sb.append("Alphabet: ").append(alphabet).append("\n");
            sb.append("Transitions:\n");
            for (Transition t : transitions) {
                sb.append(t).append("\n");
            }
            sb.append("Initial State: ").append(initialState).append("\n");
            sb.append("Accept State: ").append(acceptState);
            return sb.toString();
        }
    }