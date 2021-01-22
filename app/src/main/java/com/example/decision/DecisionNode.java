package com.example.decision;

public class DecisionNode {
    int nodeID;
    int yesID;
    int noID;
    String description;
    String question;



    public DecisionNode(int giveNodeID, int giveYesID, int giveNoID, String giveDescription, String giveQuestion) {
        nodeID = giveNodeID;
        yesID = giveYesID;
        noID = giveNoID;
        description = giveDescription;
        question = giveQuestion;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public int getYesID() {
        return yesID;
    }

    public void setYesID(int yesID) {
        this.yesID = yesID;
    }

    public int getNoID() {
        return noID;
    }

    public void setNoID(int noID) {
        this.noID = noID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
