package com.example.tetris.model;

public class GameModel {

    public static final long LOCK_DELAY_NANOS = 1_000_000_000L;
    public static final long BASE_FALL_INTERVAL_NANOS = 800_000_000L;
    public static final long FALL_INTERVAL_DECREASE_NANOS = 70_000_000L;
    public static final long MIN_FALL_INTERVAL_NANOS = 100_000_000L;
    private static final int LINES_PER_LEVEL = 10;

    private Board board;
    private Piece currentPiece;
    private PieceType nextPieceType;
    private long groundedNanos;
    private long fallElapsedNanos;
    private int score;
    private int totalLinesCleared;
    private int level;
    private boolean gameOver;

    public GameModel() {
        reset();
    }

    GameModel(Board board, Piece currentPiece) {
        this.board = board;
        this.currentPiece = currentPiece;
        nextPieceType = randomPieceType();
        level = 1;
    }

    public void reset() {
        board = new Board();
        currentPiece = new Piece(PieceType.T, 3, 0);
        nextPieceType = randomPieceType();
        groundedNanos = 0;
        fallElapsedNanos = 0;
        score = 0;
        totalLinesCleared = 0;
        level = 1;
        gameOver = false;
    }

    public boolean movePiece(int dx, int dy) {
        if (gameOver) {
            return false;
        }
        int nextX = currentPiece.getX() + dx;
        int nextY = currentPiece.getY() + dy;
        if (!board.canPlace(currentPiece.getShape(), nextX, nextY)) {
            return false;
        }
        currentPiece.move(dx, dy);
        return true;
    }

    public boolean rotatePiece() {
        if (gameOver) {
            return false;
        }
        boolean wasGrounded = isPieceGrounded();
        boolean[][] rotatedShape = currentPiece.getRotatedShape();
        int verticalKick = 0;
        while (verticalKick <= 2
                && !board.canPlace(rotatedShape, currentPiece.getX(), currentPiece.getY() - verticalKick)) {
            verticalKick++;
        }
        if (verticalKick > 2) {
            return false;
        }
        if (verticalKick > 0) {
            currentPiece.move(0, -verticalKick);
        }
        currentPiece.setShape(rotatedShape);
        if (wasGrounded) {
            groundedNanos = 0;
        }
        return true;
    }

    public boolean dropPiece() {
        if (gameOver) {
            return false;
        }
        while (movePiece(0, 1)) {
        }
        lockCurrentPiece();
        return true;
    }

    public boolean update(long elapsedNanos) {
        if (elapsedNanos < 0) {
            throw new IllegalArgumentException("Elapsed time cannot be negative.");
        }
        if (gameOver) {
            return false;
        }

        long remainingNanos = elapsedNanos;
        boolean changed = false;
        while (remainingNanos > 0 && !gameOver) {
            if (isPieceGrounded()) {
                long untilLock = LOCK_DELAY_NANOS - groundedNanos;
                long elapsedTowardLock = Math.min(remainingNanos, untilLock);
                groundedNanos += elapsedTowardLock;
                remainingNanos -= elapsedTowardLock;
                if (groundedNanos == LOCK_DELAY_NANOS) {
                    lockCurrentPiece();
                    changed = true;
                }
                continue;
            }

            long untilFall = getFallIntervalNanos() - fallElapsedNanos;
            long elapsedTowardFall = Math.min(remainingNanos, untilFall);
            fallElapsedNanos += elapsedTowardFall;
            remainingNanos -= elapsedTowardFall;
            if (fallElapsedNanos == getFallIntervalNanos()) {
                fallElapsedNanos = 0;
                if (movePiece(0, 1)) {
                    changed = true;
                }
            }
        }
        return changed;
    }

    public boolean isPieceGrounded() {
        return !gameOver
                && !board.canPlace(currentPiece.getShape(), currentPiece.getX(), currentPiece.getY() + 1);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getTotalLinesCleared() {
        return totalLinesCleared;
    }

    public long getFallIntervalNanos() {
        long levelSpeedup = (long) (level - 1) * FALL_INTERVAL_DECREASE_NANOS;
        return Math.max(MIN_FALL_INTERVAL_NANOS, BASE_FALL_INTERVAL_NANOS - levelSpeedup);
    }

    long getGroundedNanos() {
        return groundedNanos;
    }

    private void lockCurrentPiece() {
        board.lockPiece(currentPiece);
        int clearedLines = board.clearFullLines();
        if (clearedLines > 0) {
            recordClearedLines(clearedLines);
        }

        currentPiece = new Piece(nextPieceType, 3, 0);
        nextPieceType = randomPieceType();
        groundedNanos = 0;
        fallElapsedNanos = 0;
        if (!board.canPlace(currentPiece.getShape(), currentPiece.getX(), currentPiece.getY())) {
            currentPiece = null;
            gameOver = true;
        }
    }

    private int pointsForLines(int lines) {
        return switch (lines) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> throw new IllegalArgumentException("A piece can clear between one and four lines.");
        };
    }

    void recordClearedLines(int lines) {
        score += pointsForLines(lines) * level;
        totalLinesCleared += lines;
        level = 1 + totalLinesCleared / LINES_PER_LEVEL;
    }

    private PieceType randomPieceType() {
        PieceType[] types = PieceType.values();
        return types[java.util.concurrent.ThreadLocalRandom.current().nextInt(types.length)];
    }

    public boolean[][] getNextPieceShape() {
        return nextPieceType.createShape();
    }

    public boolean[][] getLandingCells() {
        boolean[][] landingCells = new boolean[Board.HEIGHT][Board.WIDTH];
        if (currentPiece == null) {
            return landingCells;
        }

        int landingY = currentPiece.getY();
        boolean[][] shape = currentPiece.getShape();
        while (board.canPlace(shape, currentPiece.getX(), landingY + 1)) {
            landingY++;
        }

        for (int row = 0; row < shape.length; row++) {
            for (int column = 0; column < shape[row].length; column++) {
                if (shape[row][column]) {
                    landingCells[landingY + row][currentPiece.getX() + column] = true;
                }
            }
        }
        return landingCells;
    }

    public boolean[][] getVisibleCells() {
        return board.getVisibleCells(currentPiece);
    }
}
