package com.ancyracademy.esportsclash.team.domain.model;

public enum Role {
    TOP,
    JUNGLE,
    MID,
    BOTTOM,
    SUPPORT;


    public static Role fromString(String role) {
        return switch (role) {
            case "Top" -> TOP;
            case "Jungle" -> JUNGLE;
            case "Mid" -> MID;
            case "Bottom" -> BOTTOM;
            case "Support" -> SUPPORT;
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case TOP -> "Top";
            case JUNGLE -> "Jungle";
            case MID -> "Mid";
            case BOTTOM -> "Bottom";
            case SUPPORT -> "Support";
        };
    }
}
