public class MoveOfHorse {

    private int sizeX = 8;
    private int sizeY = 8;
    private final int[][] field = new int[sizeX][sizeY];

    MoveOfHorse(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = 0;
            }
        }
    }

    public void setHorse(){
        int step = 1;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == 0) field[i][j] = step;
                if (gallop(step + 1, i, j )) return;
                field[i][j] = 0;
            }
        }
    }

    private boolean gallop(int step, int x, int y){
        if (step > sizeX * sizeY) return true;
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                if (Math.abs(i) == Math.abs(j) || i == 0 || j == 0) continue;
                if (x + i >= 0 && y + j < sizeY && y + j >= 0 && x + i < sizeX) {
                    if (field[x + i][y + j] == 0) {
                        field[x + i][y + j] = step;
                        if (gallop(step + 1, x + i, y + j)) return true;
                        field[x + i][y + j] = 0;
                    }
                }
            }
        }
        return false;
    }

    public void showField(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.printf("%3d",field[i][j]);
            }
            System.out.println();
        }
    }
}
