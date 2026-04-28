package com.rs.leetcode;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LeetCode Progress Tracker with Spaced Repetition System
 * 
 * This class tracks your LeetCode problem-solving progress using an Excel file
 * with a spaced repetition schedule: Day 0 (today) → Day 1 → Day 3 → Day 7 → Day 15 → Day 30 → Day 45
 * 
 * Features:
 * - Automatically scans easy/med/hard packages for new problems
 * - Adds new problems to Excel with initial solve date
 * - Calculates next practice dates based on spaced repetition
 * - Marks overdue problems as backlog
 * - Updates practice completion flags
 * 
 * Usage: Run this after solving a new problem or to update practice status
 */
public class ProgressTracker {

    private static final String SRC_BASE = "src/com/rs/leetcode";
    private static final String EXCEL_PATH = "_DSA_Tracker.xlsx";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // Spaced repetition intervals in days
    private static final int[] REPETITION_INTERVALS = {1, 3, 7, 15, 30, 45};
    
    // Column indices
    private static final int COL_PROBLEM_NUMBER = 0;
    private static final int COL_TITLE = 1;
    private static final int COL_DIFFICULTY = 2;
    private static final int COL_ALGORITHM = 3;
    private static final int COL_FIRST_SOLVE_DATE = 4;
    private static final int COL_DAY_1 = 5;
    private static final int COL_DAY_3 = 6;
    private static final int COL_DAY_7 = 7;
    private static final int COL_DAY_15 = 8;
    private static final int COL_DAY_30 = 9;
    private static final int COL_DAY_45 = 10;
    private static final int COL_STATUS = 11;
    private static final int COL_BACKLOG_COUNT = 12;
    private static final int COL_FILE_PATH = 13;

    static class ProblemInfo {
        int number;
        String title;
        String difficulty;
        String algorithm;
        String fileName;
        String relativePath;
        LocalDate solveDate;

        @Override
        public String toString() {
            return String.format("Leetcode_%d: %s (%s)", number, title, difficulty);
        }
    }

    public static void main(String[] args) {
        try {
            Path projectRoot = Paths.get("").toAbsolutePath();
            System.out.println("🚀 LeetCode Progress Tracker");
            System.out.println("Project root: " + projectRoot);
            System.out.println("Excel file: " + EXCEL_PATH);
            System.out.println();

            // Scan all problems from source folders
            List<ProblemInfo> allProblems = scanAllProblems(projectRoot);
            System.out.println("📊 Found " + allProblems.size() + " problems in source code");

            // Update Excel file
            Path excelPath = projectRoot.resolve(EXCEL_PATH);
            updateExcelTracker(excelPath, allProblems);

            System.out.println("\n✅ Progress tracker updated successfully!");

        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Scans easy, med, and hard packages for all LeetCode problems
     */
    private static List<ProblemInfo> scanAllProblems(Path projectRoot) throws IOException {
        List<ProblemInfo> problems = new ArrayList<>();
        Path srcBasePath = projectRoot.resolve(SRC_BASE);

        String[] folders = {"easy", "med", "hard"};
        Map<String, String> difficultyMap = Map.of(
            "easy", "Easy",
            "med", "Medium",
            "hard", "Hard"
        );

        for (String folder : folders) {
            Path folderPath = srcBasePath.resolve(folder);
            if (!Files.exists(folderPath)) continue;

            String difficulty = difficultyMap.get(folder);

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath, "Leetcode_*.java")) {
                for (Path file : stream) {
                    ProblemInfo info = parseFile(file, difficulty);
                    if (info != null) {
                        problems.add(info);
                        System.out.println("  ✓ " + info);
                    }
                }
            }
        }

        problems.sort(Comparator.comparingInt(p -> p.number));
        return problems;
    }

    /**
     * Parses a LeetCode solution file for metadata
     */
    private static ProblemInfo parseFile(Path file, String difficulty) throws IOException {
        String fileName = file.getFileName().toString();
        
        Pattern numberPattern = Pattern.compile("Leetcode_(\\d+)\\.java");
        Matcher numberMatcher = numberPattern.matcher(fileName);
        
        if (!numberMatcher.matches()) return null;

        ProblemInfo info = new ProblemInfo();
        info.number = Integer.parseInt(numberMatcher.group(1));
        info.fileName = fileName;
        info.difficulty = difficulty;
        info.relativePath = SRC_BASE + "/" + difficulty.toLowerCase().replace("medium", "med") + "/" + fileName;
        info.solveDate = LocalDate.now(); // Default to today

        String content = Files.readString(file);

        // Parse @LeetcodeMeta block
        Pattern metaBlockPattern = Pattern.compile("/\\*\\s*@LeetcodeMeta(.*?)\\*/", Pattern.DOTALL);
        Matcher metaMatcher = metaBlockPattern.matcher(content);

        if (metaMatcher.find()) {
            String metaBlock = metaMatcher.group(1);
            info.title = extractMetaField(metaBlock, "Title");
            info.algorithm = extractMetaField(metaBlock, "Algorithm");
        } else {
            info.title = "Leetcode " + info.number;
            info.algorithm = "-";
        }

        return info;
    }

    private static String extractMetaField(String metaBlock, String fieldName) {
        Pattern fieldPattern = Pattern.compile("@" + fieldName + ":\\s*(.+)");
        Matcher matcher = fieldPattern.matcher(metaBlock);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "-";
    }

    /**
     * Updates or creates the Excel tracker file
     */
    private static void updateExcelTracker(Path excelPath, List<ProblemInfo> allProblems) throws IOException {
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        Map<Integer, Integer> existingProblems = new HashMap<>();

        // Load existing workbook or create new one
        if (Files.exists(excelPath)) {
            System.out.println("📖 Loading existing Excel file...");
            try (FileInputStream fis = new FileInputStream(excelPath.toFile())) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                
                // Map existing problems (problem number -> row index)
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell cell = row.getCell(COL_PROBLEM_NUMBER);
                        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                            existingProblems.put((int) cell.getNumericCellValue(), i);
                        }
                    }
                }
                System.out.println("  Found " + existingProblems.size() + " existing problems");
            }
        } else {
            System.out.println("📝 Creating new Excel file...");
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("LeetCode Progress");
            createHeader(workbook, sheet);
        }

        // Create cell styles
        CellStyle dateStyle = createDateStyle(workbook);
        CellStyle completedStyle = createCompletedStyle(workbook);
        CellStyle pendingStyle = createPendingStyle(workbook);
        CellStyle overdueStyle = createOverdueStyle(workbook);

        int newProblemsAdded = 0;
        int problemsUpdated = 0;

        // Process each problem
        for (ProblemInfo problem : allProblems) {
            Integer existingRow = existingProblems.get(problem.number);
            
            if (existingRow == null) {
                // New problem - add it
                int newRowNum = sheet.getLastRowNum() + 1;
                addNewProblem(sheet, newRowNum, problem, dateStyle, pendingStyle);
                newProblemsAdded++;
                System.out.println("  ➕ Added: Leetcode_" + problem.number + " - " + problem.title);
            } else {
                // Existing problem - update status
                updateProblemStatus(sheet, existingRow, dateStyle, completedStyle, pendingStyle, overdueStyle);
                problemsUpdated++;
            }
        }

        // Auto-size columns
        for (int i = 0; i <= COL_FILE_PATH; i++) {
            sheet.autoSizeColumn(i);
        }

        // Save workbook
        try (FileOutputStream fos = new FileOutputStream(excelPath.toFile())) {
            workbook.write(fos);
        }
        workbook.close();

        System.out.println("\n📈 Summary:");
        System.out.println("  New problems added: " + newProblemsAdded);
        System.out.println("  Problems updated: " + problemsUpdated);
        System.out.println("  Total problems tracked: " + allProblems.size());
    }

    /**
     * Creates the header row with column titles
     */
    private static void createHeader(XSSFWorkbook workbook, XSSFSheet sheet) {
        Row headerRow = sheet.createRow(0);
        
        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        String[] headers = {
            "Problem #", "Title", "Difficulty", "Algorithm", "First Solve Date",
            "Day 1", "Day 3", "Day 7", "Day 15", "Day 30", "Day 45",
            "Status", "Backlog", "File Path"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, headers.length - 1));
        sheet.createFreezePane(0, 1);
    }

    /**
     * Adds a new problem to the tracker
     */
    private static void addNewProblem(XSSFSheet sheet, int rowNum, ProblemInfo problem, 
                                      CellStyle dateStyle, CellStyle pendingStyle) {
        Row row = sheet.createRow(rowNum);

        // Basic info
        row.createCell(COL_PROBLEM_NUMBER).setCellValue(problem.number);
        row.createCell(COL_TITLE).setCellValue(problem.title);
        row.createCell(COL_DIFFICULTY).setCellValue(problem.difficulty);
        row.createCell(COL_ALGORITHM).setCellValue(problem.algorithm);
        
        // First solve date
        Cell dateCell = row.createCell(COL_FIRST_SOLVE_DATE);
        dateCell.setCellValue(problem.solveDate);
        dateCell.setCellStyle(dateStyle);

        // Calculate and set next practice dates
        LocalDate baseDate = problem.solveDate;
        int[] dateCols = {COL_DAY_1, COL_DAY_3, COL_DAY_7, COL_DAY_15, COL_DAY_30, COL_DAY_45};
        
        for (int i = 0; i < REPETITION_INTERVALS.length; i++) {
            LocalDate nextDate = baseDate.plusDays(REPETITION_INTERVALS[i]);
            Cell cell = row.createCell(dateCols[i]);
            cell.setCellValue(nextDate);
            cell.setCellStyle(dateStyle);
        }

        // Status
        Cell statusCell = row.createCell(COL_STATUS);
        statusCell.setCellValue("Pending");
        statusCell.setCellStyle(pendingStyle);

        // Backlog count
        row.createCell(COL_BACKLOG_COUNT).setCellValue(0);

        // File path
        row.createCell(COL_FILE_PATH).setCellValue(problem.relativePath);
    }

    /**
     * Updates the status of an existing problem based on current date
     */
    private static void updateProblemStatus(XSSFSheet sheet, int rowNum, CellStyle dateStyle,
                                           CellStyle completedStyle, CellStyle pendingStyle, 
                                           CellStyle overdueStyle) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return;

        LocalDate today = LocalDate.now();
        int backlogCount = 0;
        boolean allCompleted = true;
        LocalDate nextDueDate = null;

        // Check each practice date
        int[] dateCols = {COL_DAY_1, COL_DAY_3, COL_DAY_7, COL_DAY_15, COL_DAY_30, COL_DAY_45};
        
        for (int col : dateCols) {
            Cell dateCell = row.getCell(col);
            if (dateCell != null && dateCell.getCellType() == CellType.NUMERIC) {
                LocalDate dueDate = dateCell.getLocalDateTimeCellValue().toLocalDate();
                
                // Check if this date is overdue
                if (dueDate.isBefore(today)) {
                    // Check if there's a completion marker (you can manually mark as done)
                    Cell checkCell = row.getCell(col);
                    String cellValue = checkCell.toString();
                    
                    if (!cellValue.contains("✓") && !cellValue.contains("DONE")) {
                        backlogCount++;
                        checkCell.setCellStyle(overdueStyle);
                        allCompleted = false;
                    }
                } else if (nextDueDate == null || dueDate.isBefore(nextDueDate)) {
                    nextDueDate = dueDate;
                    allCompleted = false;
                }
            }
        }

        // Update status
        Cell statusCell = row.getCell(COL_STATUS);
        if (statusCell == null) {
            statusCell = row.createCell(COL_STATUS);
        }

        if (allCompleted) {
            statusCell.setCellValue("Completed ✅");
            statusCell.setCellStyle(completedStyle);
        } else if (backlogCount > 0) {
            statusCell.setCellValue("Overdue ⚠️");
            statusCell.setCellStyle(overdueStyle);
        } else if (nextDueDate != null) {
            long daysUntil = ChronoUnit.DAYS.between(today, nextDueDate);
            statusCell.setCellValue("Next in " + daysUntil + " days");
            statusCell.setCellStyle(pendingStyle);
        }

        // Update backlog count
        Cell backlogCell = row.getCell(COL_BACKLOG_COUNT);
        if (backlogCell == null) {
            backlogCell = row.createCell(COL_BACKLOG_COUNT);
        }
        backlogCell.setCellValue(backlogCount);
        
        if (backlogCount > 0) {
            backlogCell.setCellStyle(overdueStyle);
        }
    }

    // Cell style creators
    private static CellStyle createDateStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        style.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private static CellStyle createCompletedStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.DARK_GREEN.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private static CellStyle createPendingStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private static CellStyle createOverdueStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.DARK_RED.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
