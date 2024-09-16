package com.github.seanfinnessy.ValTracker.model;

public enum Rank {
    UNRANKED(1, "Unranked"),
    UNUSED_1(0, "Unranked"),
    UNUSED_2(2, "Unranked"),
    IRON_1(3, "Iron 1"),
    IRON_2(4, "Iron 2"),
    IRON_3(5, "Iron 3"),
    BRONZE_1(6, "Bronze 1"),
    BRONZE_2(7, "Bronze 2"),
    BRONZE_3(8, "Bronze 3"),
    SILVER_1(9, "Silver 1"),
    SILVER_2(10, "Silver 2"),
    SILVER_3(11, "Silver 3"),
    GOLD_1(12, "Gold 1"),
    GOLD_2(13, "Gold 2"),
    GOLD_3(14, "Gold 3"),
    PLATINUM_1(15, "Platinum 1"),
    PLATINUM_2(16, "Platinum 2"),
    PLATINUM_3(17, "Platinum 3"),
    DIAMOND_1(18, "Diamond 1"),
    DIAMOND_2(19, "Diamond 2"),
    DIAMOND_3(20, "Diamond 3"),
    ASCENDANT_1(21, "Ascendant 1"),
    ASCENDANT_2(22, "Ascendant 2"),
    ASCENDANT_3(23, "Ascendant 3"),
    IMMORTAL_1(24, "Immortal 1"),
    IMMORTAL_2(25, "Immortal 2"),
    IMMORTAL_3(26, "Immortal 3"),
    RADIANT(27, "Radiant");

    private final int value;
    private final String displayName;

    Rank(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Rank getRankByValue(int value) {
        for (Rank rank : Rank.values()) {
            if (rank.getValue() == value) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Invalid rank value: " + value);
    }
}
