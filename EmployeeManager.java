import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.*;

/**
 * 社員情報を管理するクラス
 */
public class EmployeeManager {
    private static final String DATA_FOLDER = "NameInfoApp/Data";    // データ保存先のフォルダ
    private static final String LOG_FOLDER = "NameInfoApp/Log";      // ログ保存先のフォルダ
    private static final String CSV_FILE = DATA_FOLDER + "/NameInfo.csv"; // CSVファイルのパス
    private List<Employee> employees;  // 社員情報のリスト
    private Logger logger;             // ログ機能

    /**
     * コンストラクタ
     */
    public EmployeeManager() {
        employees = new ArrayList<>();
        logger = Logger.getLogger("EmployeeLogger");
        setupLogger();   // ロガーの設定
        loadEmployees(); // CSVから社員データを読み込み
    }

    /**
     * ログを設定する
     */
    private void setupLogger() {
        try {
            // ログフォルダを作成
            Files.createDirectories(Paths.get(LOG_FOLDER));
            String logFile = LOG_FOLDER + "/NameInfoApp-" + LocalDate.now() + ".log";
            FileHandler fh = new FileHandler(logFile, true);

            // ログのフォーマットを設定
            // ログの利用方法をよく調べてチームの仕様に合わせて変更・対応してください
            System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
            fh.setFormatter(new SimpleFormatter());

            logger.addHandler(fh);
        } catch (IOException e) {
            // スタックトレースを文字列で取得してログに出力する
            printLogStackTrace(e, "ログ設定で例外が発生しました");
        }
    }

    /**
     * 社員情報をCSVファイルから読み込む.
     */
    private void loadEmployees() {
        try {
            // データフォルダを作成
            Files.createDirectories(Paths.get(DATA_FOLDER));
            File file = new File(CSV_FILE);
            if (!file.exists()) {
                file.createNewFile(); // ファイルがない場合は新規作成
            } else {
                // CSVファイルから社員情報を読み込む
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                            if(line.trim().isEmpty()){
        continue;
    }
                        String[] data = line.split(",");
                            if(data.length < 13){
        System.out.println(
            "CSVデータ不足: " + line
        );
        continue;
    }
                        employees.add(
    new Employee(
        data[0],
        data[1],
        LocalDate.parse(data[2]),
        Integer.parseInt(data[3]),

        data[4],
        data[5],
        data[6],
        data[7],

        Double.parseDouble(data[8]),
        Double.parseDouble(data[9]),
        Double.parseDouble(data[10]),
        Double.parseDouble(data[11]),

        data[12]
    )
);
                    }
                }
            }
        } catch (IOException e) {
            printLogStackTrace(e, "CSVから社員情報の読み込む際に例外が発生しました");
        }
    }

    /**
     * ログにスタックトレースを出力する
     * @param e スタックトレースを持っている例外クラス
     * @param errorString   ログに出力するエラー文言
     */
    private void printLogStackTrace(Exception e, String errorString) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        logger.severe(String.format("%s¥n%s", errorString, sw.toString()));
    }

    /**
     * 社員情報を追加する
     * @param employee  社員情報
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);  // 社員情報の追加
        saveEmployees();     // CSVに保存
        logger.info("社員情報を追加: " + employee);  // ログ出力
    }

    /**
     * 社員情報を削除する
     * @param employee  対象の社員情報
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);  // 社員情報の削除
        saveEmployees();        // CSVに保存
        logger.info("社員情報を削除: " + employee);  // ログ出力
    }

    /**
     * CSVに社員情報を保存する
     */
    private void saveEmployees() {
        // CSVファイルに社員情報を保存
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Employee employee : employees) {
                writer.write(employee.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            printLogStackTrace(e, "CSVに社員情報を保存する際に例外が発生しました");
        }
    }

    /**
 * 社員IDで昇順ソート
 */
public void sortById() {
    employees.sort(
        Comparator.comparing(Employee::getId)
    );
}

public void sortByName() {
    employees.sort(
        Comparator.comparing(Employee::getName)
    );
}

public void sortByBirthDate() {
    employees.sort(
        Comparator.comparing(Employee::getBirthDate)
    );
}

public void sortByEngineerExperience() {
    employees.sort(
        Comparator.comparingInt(Employee::getEngineerExperience)
    );
}

public void sortByIdDesc() {
    employees.sort(
        Comparator.comparing(Employee::getId)
        .reversed()
    );
}

/**
 * 氏名で昇順ソート
 */
public void sortByNameDesc() {
    employees.sort(
        Comparator.comparing(Employee::getName)
        .reversed()
    );
}

/**
 * 生年月日で昇順ソート
 */
public void sortByBirthDateDesc() {
    employees.sort(
        Comparator.comparing(Employee::getBirthDate)
        .reversed()
    );
}

/**
 * エンジニア歴で昇順ソート
 */
public void sortByEngineerExperienceDesc() {
    employees.sort(
        Comparator.comparingInt(Employee::getEngineerExperience)
        .reversed()
    );
}

    /**
     * 社員情報のリストを取得する
     * @return  社員情報リスト
     */
    /**
 * 氏名検索
 */
public List<Employee> searchByName(String keyword) {

    List<Employee> result = new ArrayList<>();

    for (Employee emp : employees) {

        if (emp.getName().contains(keyword)) {
            result.add(emp);
        }

    }

    return result;
}

public void updateEmployee() {
    saveEmployees();
}
    public List<Employee> getEmployees() {
        return employees;  // 社員情報リストを取得
    }
}
