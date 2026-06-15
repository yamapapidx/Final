import java.time.LocalDate;
import java.time.Period;

/**
 * 社員情報クラス
 */
public class Employee {
    private String id;            // 社員ID
    private String name;          // 名前
    private LocalDate birthDate;  // 生年月日
    private int engineerExperience; //エンジニア歴

    /**
     * コンストラクタ
     * @param id    社員ID
     * @param name  名前
     * @param birthDate 生年月日
     */
    public Employee(String id, String name, LocalDate birthDate, int engineerExperience) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.engineerExperience = engineerExperience;
    }

    /**
     * 社員IDを取得する
     * @return  社員IDを取得する
     */
    public String getId() {
        return id;
    }

    /**
     * 名前を取得する
     * @return  名前
     */
    public String getName() {
        return name;
    }

    /**
     * 生年月日を取得する
     * @return  生年月日
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * エンジニア歴を取得する
     * @return  エンジニア歴
     */
    public int getEngineerExperience() {
    return engineerExperience;
}

public int getAge() {
    return Period.between(
        birthDate,
        LocalDate.now()
    ).getYears();
}

    @Override
    public String toString() {
        return id + "," + name + "," + birthDate + "," + engineerExperience;  // CSV形式での出力用フォーマット
    }
}
