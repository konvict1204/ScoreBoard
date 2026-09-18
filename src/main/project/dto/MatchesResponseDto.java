package project.dto;

import java.util.List;

public record MatchesResponseDto (List<MatchResponseDto> matches, int currentPage, int totalPages) {
}
