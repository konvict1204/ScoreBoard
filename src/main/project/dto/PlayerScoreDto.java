package project.dto;

public record PlayerScoreDto (String name, String points, int games,
                             int sets, Integer tieBreakPoints) {}
