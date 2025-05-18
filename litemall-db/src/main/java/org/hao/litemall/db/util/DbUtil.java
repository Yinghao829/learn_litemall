package org.hao.litemall.db.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DbUtil {
    /**
     * 备份数据库
     */
    public static  void backup(File file, String user, String password, String db) throws Exception, InterruptedException {
        validateInput(file, user, db);
        // 使用ProcessBuilder 避免命令注入
        ProcessBuilder pd = new ProcessBuilder(
                "mysqldump",
                "--user=" + escapeShellArg(user),
                "--password=" + escapeShellArg(password),
                "--default-character-set=utf8mb4",
                "--skip-lock-tables",
                db
        );
        pd.redirectErrorStream(true); // 合并错误流到标准输出
        Process p = pd.start();

        // 使用try-with-resource 自动关闭流
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
        // 检查命令执行结果
        if (p.waitFor() != 0) {
            throw new RuntimeException("Database backup failed with exit code : " + p.exitValue());
        }
    }

    /**
     * 恢复数据库
     */
    public static void load(File file, String user, String password, String db) throws IOException, InterruptedException {
        validateInput(file, user, db);
        ProcessBuilder pd = new ProcessBuilder(
                "mysql",
                "--user=" + escapeShellArg(user),
                "--password=" + escapeShellArg(password),
                "--default-character-set=utf8mb4",
                db
        );
        pd.redirectErrorStream(true);
        Process p = pd.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
        }
        if (p.waitFor() != 0) {
            throw new RuntimeException("Database restore failed with exit code : " + p.exitValue());
        }
    }

    // 辅助方法：校验输入参数
    private static void validateInput(File file, String user, String db) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("Invalid backup file path");
        }
        if (user == null || user.trim().isEmpty()) {
            throw new IllegalArgumentException("Database user cannot be empty");
        }
        if (db == null || db.trim().isEmpty()) {
            throw new IllegalArgumentException("Database db cannot be empty");
        }
    }

    // 辅助方法：转义shell参数（防御命令注入）
    private static String escapeShellArg(String arg) {
        return "'" + arg.replace("'", "\\''") + "'";
    }
}
