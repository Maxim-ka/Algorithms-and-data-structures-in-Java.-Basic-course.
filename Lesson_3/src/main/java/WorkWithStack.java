public class WorkWithStack {

    private static final int SIZE_STACK = 10;
    private static final char ROUND_OPEN = '(';
    private static final char ROUND_CLOSE = ')';
    private static final char CURLY_OPEN = '{';
    private static final char CURLY_CLOSE = '}';
    private static final char ANGLE_OPEN = '<';
    private static final char ANGLE_CLOSE = '>';
    private static final char SQUARE_OPEN = '[';
    private static final char SQUARE_CLOSE = ']';
    private static final String REMAIN = "Оставшиеся незакрытые скобки";
    private static final String NO_OPEN = "Нет";
    private static final String MISS = "Отсутствует";

    private class Bracket{

        private char bracket;
        private int index;

        char getBracket() {
            return bracket;
        }

        int getIndex() {
            return index;
        }

        Bracket(char bracket, int index) {
            this.bracket = bracket;
            this.index = index + 1;
        }
    }

    private MyStack<Character> myStack;
    private MyStack<Bracket> myStackBracket;

    public WorkWithStack() {
        myStack = new MyStack<>(SIZE_STACK);
        myStackBracket = new MyStack<>(SIZE_STACK);
    }

    public String reverse(String text){
        StringBuilder stringBuilder = new StringBuilder();
        if (!myStack.isEmpty()) myStack.clear();
        for (int i = 0; i < text.length(); i++) {
            myStack.push(text.charAt(i));
        }
        for (int i = 0; i < text.length(); i++) {
            stringBuilder.append(myStack.pop());
        }
        return stringBuilder.toString();
    }

    public String parseSequences(String text){
        Bracket missingBracket;
        if (text.length() < 2) return String.format("%s - нет пары", text);
        if (!myStackBracket.isEmpty()) myStackBracket.clear();
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)){
                case ROUND_OPEN:
                    myStackBracket.push(new Bracket(ROUND_OPEN, i));
                    break;
                case ROUND_CLOSE:
                    if ((missingBracket = checkErrorBrackets(ROUND_CLOSE)) != null)
                        return (missingBracket.getBracket() == ROUND_CLOSE) ? String.format("%s %s позиция %d", NO_OPEN, ROUND_OPEN, i + 1) :
                                String.format("%s %s позиции %d, текущая %s позиция %d", MISS, match(missingBracket.getBracket()),missingBracket.getIndex(), ROUND_CLOSE, i + 1);
                    break;
                case ANGLE_OPEN:
                    myStackBracket.push(new Bracket(ANGLE_OPEN, i));
                    break;
                case ANGLE_CLOSE:
                    if ((missingBracket = checkErrorBrackets(ANGLE_CLOSE)) != null)
                        return (missingBracket.getBracket() == ANGLE_CLOSE) ? String.format("%s %s позиция %d", NO_OPEN, ANGLE_OPEN, i + 1) :
                                String.format("%s %s позиции %d, текущая %s позиция %d", MISS, match(missingBracket.getBracket()),missingBracket.getIndex(), ANGLE_CLOSE, i + 1);
                    break;
                case CURLY_OPEN:
                    myStackBracket.push(new Bracket(CURLY_OPEN, i));
                    break;
                case CURLY_CLOSE:
                    if ((missingBracket = checkErrorBrackets(CURLY_CLOSE)) != null)
                        return (missingBracket.getBracket() == CURLY_CLOSE) ? String.format("%s %s позиция %d", NO_OPEN, CURLY_OPEN, i + 1) :
                                String.format("%s %s позиции %d, текущая %s позиция %d", MISS, match(missingBracket.getBracket()),missingBracket.getIndex(), CURLY_CLOSE, i + 1);
                    break;
                case SQUARE_OPEN:
                    myStackBracket.push(new Bracket(SQUARE_OPEN, i));
                    break;
                case SQUARE_CLOSE:
                    if ((missingBracket = checkErrorBrackets(SQUARE_CLOSE)) != null)
                        return (missingBracket.getBracket() == SQUARE_CLOSE) ? String.format("%s %s позиция %d", NO_OPEN, SQUARE_OPEN, i + 1) :
                                String.format("%s %s позиции %d, текущая %s позиция %d", MISS, match(missingBracket.getBracket()),missingBracket.getIndex(), SQUARE_CLOSE, i + 1);
                    break;
            }
        }
        if (!myStackBracket.isEmpty()) return toRemaining();
        return null;
    }

    private String toRemaining(){
        StringBuilder stringBuilder = new StringBuilder(REMAIN);
        while (!myStackBracket.isEmpty()){
            Bracket bracket = (Bracket) myStackBracket.pop();
            stringBuilder.append(", позиция: ").append(bracket.getIndex()).append(bracket.getBracket());
        }
        return stringBuilder.toString();
    }

    private char match(char bracket){
        if (bracket == ROUND_OPEN) return ROUND_CLOSE;
        if (bracket == CURLY_OPEN) return CURLY_CLOSE;
        if (bracket == ANGLE_OPEN) return ANGLE_CLOSE;
        return SQUARE_CLOSE;
    }

    private Bracket checkErrorBrackets(char character){
        if (myStackBracket.isEmpty()) return new Bracket(character, -1);
        Bracket bracket = (Bracket) myStackBracket.pop();
        if (match(bracket.getBracket()) != character) return bracket;
        return null;
    }
}
