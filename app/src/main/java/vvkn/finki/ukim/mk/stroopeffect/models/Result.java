package vvkn.finki.ukim.mk.stroopeffect.models;

public class Result {
    // Primary key
    private Long id;

    private String gender;
    private double errorPercentageCongruent;
    private long elapsedTimeCongruent;
    private double errorPercentageIncongruent;
    private long elapsedTimeIncongruent;
    private double errorPercentageIncongruentTimer;
    private long elapsedTimeIncongruentTimer;

    public Result() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setErrorPercentageCongruent(double errorPercentageCongruent) {
        this.errorPercentageCongruent = errorPercentageCongruent;
    }

    public void setErrorPercentageIncongruent(double errorPercentageIncongruent) {
        this.errorPercentageIncongruent = errorPercentageIncongruent;
    }

    public void setErrorPercentageIncongruentTimer(double errorPercentageIncongruentTimer) {
        this.errorPercentageIncongruentTimer = errorPercentageIncongruentTimer;
    }

    public void setElapsedTimeCongruent(long elapsedTimeCongruent) {
        this.elapsedTimeCongruent = elapsedTimeCongruent;
    }

    public void setElapsedTimeIncongruent(long elapsedTimeIncongruent) {
        this.elapsedTimeIncongruent = elapsedTimeIncongruent;
    }

    public void setElapsedTimeIncongruentTimer(long elapsedTimeIncongruentTimer) {
        this.elapsedTimeIncongruentTimer = elapsedTimeIncongruentTimer;
    }

    public Long getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public double getErrorPercentageCongruent() {
        return errorPercentageCongruent;
    }

    public double getErrorPercentageIncongruent() {
        return errorPercentageIncongruent;
    }

    public double getErrorPercentageIncongruentTimer() {
        return errorPercentageIncongruentTimer;
    }

    public long getElapsedTimeCongruent() {
        return elapsedTimeCongruent;
    }

    public long getElapsedTimeIncongruent() {
        return elapsedTimeIncongruent;
    }

    public long getElapsedTimeIncongruentTimer() {
        return elapsedTimeIncongruentTimer;
    }
}
