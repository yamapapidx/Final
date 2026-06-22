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
    private String joinDate;          // 入社年月
private String languages;         // 扱える言語
private String career;            // 経歴
private String trainingHistory;   // 研修受講歴

private double technicalSkill;    // 技術力
private double attitude;          // 受講態度
private double communication;     // コミュニケーション能力
private double leadership;        // リーダーシップ

private String note;              // 備考

    /**
     * コンストラクタ
     * @param id    社員ID
     * @param name  名前
     * @param birthDate 生年月日
     */
    public Employee(
    String id,
    String name,
    LocalDate birthDate,
    int engineerExperience,

    String joinDate,
    String languages,
    String career,
    String trainingHistory,

    double technicalSkill,
    double attitude,
    double communication,
    double leadership,

    String note
){
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
    this.engineerExperience = engineerExperience;

    this.joinDate = joinDate;
    this.languages = languages;
    this.career = career;
    this.trainingHistory = trainingHistory;

    this.technicalSkill = technicalSkill;
    this.attitude = attitude;
    this.communication = communication;
    this.leadership = leadership;

    this.note = note;
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

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 生年月日を取得する
     * @return  生年月日
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    /**
     * エンジニア歴を取得する
     * @return  エンジニア歴
     */
    public int getEngineerExperience() {
    return engineerExperience;
}

public void setEngineerExperience(int engineerExperience) {
    this.engineerExperience = engineerExperience;
}

public String getJoinDate() {
    return joinDate;
}

public void setJoinDate(String joinDate) {
    this.joinDate = joinDate;
}

public String getLanguages() {
    return languages;
}

public void setLanguages(String languages) {
    this.languages = languages;
}

public String getCareer() {
    return career;
}

public void setCareer(String career) {
    this.career = career;
}

public String getTrainingHistory() {
    return trainingHistory;
}

public void setTrainingHistory(String trainingHistory) {
    this.trainingHistory = trainingHistory;
}

public double getTechnicalSkill() {
    return technicalSkill;
}

public void setTechnicalSkill(double technicalSkill) {
    this.technicalSkill = technicalSkill;
}

public double getAttitude() {
    return attitude;
}

public void setAttitude(double attitude) {
    this.attitude = attitude;
}

public double getCommunication() {
    return communication;
}

public void setCommunication(double communication) {
    this.communication = communication;
}

public double getLeadership() {
    return leadership;
}

public void setLeadership(double leadership) {
    this.leadership = leadership;
}

public String getNote() {
    return note;
}

public void setNote(String note) {
    this.note = note;
}

public int getAge() {
    return Period.between(
        birthDate,
        LocalDate.now()
    ).getYears();
}

    @Override
    public String toString() {
    return id + " : " + name;
}
public String getDetailInfo() {
    return
        "社員ID: " + id + "\n" +
        "氏名: " + name + "\n" +
        "生年月日: " + birthDate + "\n" +
        "エンジニア歴: " + engineerExperience + "ヶ月\n" +
        "入社年月: " + joinDate + "\n" +
        "扱える言語: " + languages + "\n" +
        "経歴: " + career + "\n" +
        "研修受講歴: " + trainingHistory + "\n" +
        "技術力: " + technicalSkill + "\n" +
        "受講態度: " + attitude + "\n" +
        "コミュニケーション能力: " + communication + "\n" +
        "リーダーシップ: " + leadership + "\n" +
        "備考: " + note;
}
public String toCsv() {
    return id + "," +
        name + "," +
        birthDate + "," +
        engineerExperience + "," +
        joinDate + "," +
        languages + "," +
        career + "," +
        trainingHistory + "," +
        technicalSkill + "," +
        attitude + "," +
        communication + "," +
        leadership + "," +
        note;
}
}
