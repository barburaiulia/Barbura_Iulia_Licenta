package com.example.prorate;

public class ListModelComments {

    private String cardTitle;
    private String cardDescription;
    private Float cardRating;
    private Comms comm;

    public ListModelComments(String cardTitle, String cardDescription, Float cardRating, Comms comm) {
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
        this.cardRating = cardRating;
        this.comm = comm;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public Float getCardRating() {
        return cardRating;
    }

    public void setCardRating(Float cardRating) {
        this.cardRating = cardRating;
    }

    public Comms getComm() {
        return comm;
    }

    public void setMaterie(Comms comm) {
        this.comm = comm;
    }



}