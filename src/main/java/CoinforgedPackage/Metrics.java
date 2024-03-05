package CoinforgedPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Metrics {
    private static String DB_URL=System.getenv("COINFORGED_DB_URL");
    private static String USER=System.getenv("COINFORGED_DB_USER");
    private static String PASS=System.getenv("COINFORGED_DB_PASS");
    private static final Set<String> VALID_COLUMNS = Set.of("wins", "games", "picks", "offers");
    private static final Map<String, Map<String, Integer>> localMetrics = new HashMap<>();

    public static void incrementCardWins(String cardId) {
        incrementLocalMetric("wins", cardId);
    }

    public static void incrementCardGames(String cardId) {
        incrementLocalMetric("games", cardId);
    }

    public static void incrementCardPicks(String cardId) {
        incrementLocalMetric("picks", cardId);
    }

    public static void incrementCardOffers(String cardId) {
        incrementLocalMetric("offers", cardId);
    }

    private static void incrementLocalMetric(String column, String cardId) {
        if (!VALID_COLUMNS.contains(column)) {
            System.err.println("Invalid column: " + column);
            return;
        }
        localMetrics.computeIfAbsent(cardId, k -> new HashMap<>()).merge(column, 1, Integer::sum);
    }

    private static void updateMetric(String column, String cardId) {
        if (!VALID_COLUMNS.contains(column)) {
            System.err.println("Invalid column: " + column);
            return;
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try {
                // Start transaction
                conn.setAutoCommit(false);

                String sql = "INSERT INTO metrics (cardId, " + column + ") VALUES (?, 1) " +
                        "ON DUPLICATE KEY UPDATE " + column + " = " + column + " + 1";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, cardId);
                stmt.executeUpdate();

                // Commit transaction
                conn.commit();
            } catch (SQLException e) {
                // If there was an error, roll back the transaction
                conn.rollback();
                System.err.println("SQL error: " + e.getMessage());
                System.err.println("SQL state: " + e.getSQLState());
                System.err.println("Error code: " + e.getErrorCode());
            } finally {
                // Always set auto commit back to true
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
        }
    }

    public static void endRun() {
        for (Map.Entry<String, Map<String, Integer>> cardEntry : localMetrics.entrySet()) {
            for (Map.Entry<String, Integer> metricEntry : cardEntry.getValue().entrySet()) {
                for (int i = 0; i < metricEntry.getValue(); i++) {
                    updateMetric(metricEntry.getKey(), cardEntry.getKey());
                }
            }
        }
        localMetrics.clear();
    }
}