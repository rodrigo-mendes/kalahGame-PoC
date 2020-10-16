package com.rms.kalah.domain;

public final class Pit {

    private transient int id;
    private int stoneNumbers;

    public Pit() {
    }

    public Pit(int id, int stoneNumbers) {
        this.id = id;
        this.stoneNumbers = stoneNumbers;
    }

    public int getId() {
        return id;
    }

    public int getStoneNumbers() {
        return stoneNumbers;
    }

    public void setStoneNumbers(int stoneNumbers) {
        this.stoneNumbers = stoneNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pit pit = (Pit) o;

        if (getId() != pit.getId()) return false;
        return getStoneNumbers() == pit.getStoneNumbers();
    }

    public boolean isEmpty(){
        return stoneNumbers == 0;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStoneNumbers();
        return result;
    }

    @Override
    public String toString() {
        return "Pit{" +
                "id=" + id +
                ", stoneNumbers=" + stoneNumbers +
                '}';
    }
}
