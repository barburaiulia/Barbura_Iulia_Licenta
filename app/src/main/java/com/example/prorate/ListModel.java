package com.example.prorate;

public class ListModel {

    private String cardTitle;
    private String cardDescription;
    private Float cardRating;
    private Classes materie;

    public ListModel(String cardTitle, String cardDescription, Float cardRating, Classes materie) {
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
        this.cardRating = cardRating;
        this.materie = materie;
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

    public Classes getMaterie() {
        return materie;
    }

    public void setMaterie(Classes materie) {
        this.materie = materie;
    }



}