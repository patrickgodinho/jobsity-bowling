package com.jobsity.patrick.model;

public class Play {

    private int firstScore, secondScore, finalScore, totalScore, round;

    private boolean isOpen;

    public int getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(int firstScore) {
        this.firstScore = firstScore;
    }

    public int getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(int secondScore) {
        this.secondScore = secondScore;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public boolean isStrike() {
        return (this.firstScore == 10 || this.secondScore == 10) && this.round < 10;
    }

    public boolean isSpare() {
        return !this.isStrike() && this.firstScore + this.secondScore == 10 && this.round < 10;
    }
}
