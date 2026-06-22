import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * 社員情報管理アプリのGUI表示するクラス
 */
public class EmployeeView extends JFrame {
    private EmployeeManager manager;            // 社員情報の管理クラス
    private DefaultListModel<String> employeeListModel; // 社員情報のリストモデル
    private JList<String> employeeList;         // 社員情報の表示リスト

    /**
     * コンストラクタ
     * @param manager   社員情報管理クラスのインスタンス
     */
    public EmployeeView(EmployeeManager manager) {
        this.manager = manager;
        setupUI();  // UIの設定
    }

    /**
     * UIを設定する.
     * 内部でUIに表示する文字やボタンを設定している
     */
    private void setupUI() {
        setTitle("社員名簿管理");  // ウィンドウタイトル
        setSize(1600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);
        employeeList.setSelectionMode(
    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        );
        refreshEmployeeList();  // 社員リストの初期化

        // 「追加」ボタン
        JButton addButton = new JButton("社員を追加");
        addButton.addActionListener(e -> addEmployee());

        // 「削除」ボタン
        JButton removeButton = new JButton("選択した社員を削除");
        removeButton.addActionListener(e -> removeEmployee());

        JButton editButton = new JButton("編集");
        //　ソート
        JButton sortIdButton = new JButton("社員ID順");
        JButton sortNameButton = new JButton("氏名順");
        JButton sortBirthButton = new JButton("生年月日順");
        JButton sortExpButton = new JButton("エンジニア歴順");
        JButton sortIdDescButton = new JButton("社員ID逆順");
        JButton sortNameDescButton = new JButton("氏名逆順");
        JButton sortBirthDescButton = new JButton("生年月日逆順");
        JButton sortExpDescButton = new JButton("エンジニア歴逆順");
        JButton searchButton = new JButton("氏名検索");
        JButton detailButton = new JButton("詳細表示");

        // ボタンを配置するパネル
        JPanel buttonPanel = new JPanel(new GridLayout(2, 7));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(sortIdButton);
        buttonPanel.add(sortNameButton);
        buttonPanel.add(sortBirthButton);
        buttonPanel.add(sortExpButton);
        buttonPanel.add(sortIdDescButton);
        buttonPanel.add(sortNameDescButton);
        buttonPanel.add(sortBirthDescButton);
        buttonPanel.add(sortExpDescButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(detailButton);
        System.out.println("詳細ボタン追加");

        sortIdButton.addActionListener(e -> {
            manager.sortById();
            refreshEmployeeList();
        });

        sortNameButton.addActionListener(e -> {
            manager.sortByName();
            refreshEmployeeList();
        });

        sortBirthButton.addActionListener(e -> {
            manager.sortByBirthDate();
            refreshEmployeeList();
        });

        sortExpButton.addActionListener(e -> {
            manager.sortByEngineerExperience();
            refreshEmployeeList();
        });

        sortIdDescButton.addActionListener(e -> {
            manager.sortByIdDesc();
            refreshEmployeeList();
        });

        sortNameDescButton.addActionListener(e -> {
            manager.sortByNameDesc();
            refreshEmployeeList();
        });

        sortBirthDescButton.addActionListener(e -> {
            manager.sortByBirthDateDesc();
            refreshEmployeeList();
        });

        sortExpDescButton.addActionListener(e -> {
            manager.sortByEngineerExperienceDesc();
            refreshEmployeeList();
        });

        searchButton.addActionListener(e -> {

    String keyword =
        JOptionPane.showInputDialog("検索する氏名を入力");

    employeeListModel.clear();

    for (Employee emp : manager.searchByName(keyword)) {
        employeeListModel.addElement(emp.toString());
    }
});

    editButton.addActionListener(e -> editEmployee());
        add(new JScrollPane(employeeList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        detailButton.addActionListener(
e -> showDetail()
);
    }

    /**
     * 社員情報を追加する.
     * ボタン押下された時の処理
     */
    private void addEmployee() {
        String id = JOptionPane.showInputDialog("社員IDを入力してください:");
        String name = JOptionPane.showInputDialog("名前を入力してください:");
        LocalDate birthDate = LocalDate.parse(JOptionPane.showInputDialog("生年月日を入力してください (YYYY-MM-DD):"));
        String expStr = JOptionPane.showInputDialog("エンジニア歴(月)を入力してください:");
        String joinDate =
JOptionPane.showInputDialog(
    "入社年月"
);

String languages =
JOptionPane.showInputDialog(
    "扱える言語"
);

String career =
JOptionPane.showInputDialog(
    "経歴"
);

String trainingHistory =
JOptionPane.showInputDialog(
    "研修受講歴"
);

double technicalSkill =
Double.parseDouble(
    JOptionPane.showInputDialog("技術力")
);

double attitude =
Double.parseDouble(
    JOptionPane.showInputDialog("受講態度")
);

double communication =
Double.parseDouble(
    JOptionPane.showInputDialog("コミュニケーション能力")
);

double leadership =
Double.parseDouble(
    JOptionPane.showInputDialog("リーダーシップ")
);

String note =
JOptionPane.showInputDialog("備考");
int engineerExperience = Integer.parseInt(expStr);
        Employee emp = new Employee(
    id,
    name,
    birthDate,
    engineerExperience,

    joinDate,
    languages,
    career,
    trainingHistory,

    technicalSkill,
    attitude,
    communication,
    leadership,

    note
);
manager.addEmployee(emp);  // 社員情報を管理クラスに追加
        refreshEmployeeList();     // 表示リストを更新
    }

    /**
     * 社員情報を削除する.
     * ボタン押下された時の処理
     */
    private void removeEmployee() {
   int[] selectedIndices =
        employeeList.getSelectedIndices();

    for (int i = selectedIndices.length - 1; i >= 0; i--) {

        String empData =
            (String) employeeListModel.get(
                selectedIndices[i]
            );

        Employee emp =
            manager.getEmployees().stream()
                .filter(e -> e.toString().equals(empData))
                .findFirst()
                .orElse(null);

        if (emp != null) {
            manager.removeEmployee(emp);
        }
    }

    refreshEmployeeList();
    }
private void editEmployee() {

    int selectedIndex = employeeList.getSelectedIndex();

    if (selectedIndex == -1) {
        JOptionPane.showMessageDialog(
            this,
            "編集する社員を選択してください"
        );
        return;
    }

        Employee emp =
        manager.getEmployees().get(selectedIndex);

String newName =
    JOptionPane.showInputDialog(
        "新しい名前",
        emp.getName()
    );

String newBirth =
    JOptionPane.showInputDialog(
        "新しい生年月日(YYYY-MM-DD)",
        emp.getBirthDate()
    );

String newExp =
    JOptionPane.showInputDialog(
        "新しいエンジニア歴(月)",
        emp.getEngineerExperience()
    );

    String newJoinDate =
JOptionPane.showInputDialog(
    "入社年月",
    emp.getJoinDate()
);

String newLanguages =
JOptionPane.showInputDialog(
    "扱える言語",
    emp.getLanguages()
);

String newCareer =
JOptionPane.showInputDialog(
    "経歴",
    emp.getCareer()
);

String newTrainingHistory =
JOptionPane.showInputDialog(
    "研修受講歴",
    emp.getTrainingHistory()
);

String newTechnicalSkill =
JOptionPane.showInputDialog(
    "技術力",
    emp.getTechnicalSkill()
);

String newAttitude =
JOptionPane.showInputDialog(
    "受講態度",
    emp.getAttitude()
);

String newCommunication =
JOptionPane.showInputDialog(
    "コミュニケーション能力",
    emp.getCommunication()
);

String newLeadership =
JOptionPane.showInputDialog(
    "リーダーシップ",
    emp.getLeadership()
);

String newNote =
JOptionPane.showInputDialog(
    "備考",
    emp.getNote()
);

if(newName != null){

    emp.setName(newName);

    emp.setBirthDate(
        LocalDate.parse(newBirth)
    );

    emp.setEngineerExperience(
        Integer.parseInt(newExp)
    );

    emp.setJoinDate(newJoinDate);

emp.setLanguages(newLanguages);

emp.setCareer(newCareer);

emp.setTrainingHistory(
    newTrainingHistory
);

emp.setTechnicalSkill(
    Double.parseDouble(newTechnicalSkill)
);

emp.setAttitude(
    Double.parseDouble(newAttitude)
);

emp.setCommunication(
    Double.parseDouble(newCommunication)
);

emp.setLeadership(
    Double.parseDouble(newLeadership)
);

emp.setNote(newNote);

    manager.updateEmployee();

    refreshEmployeeList();
}
}
    private void showDetail() {

            System.out.println("詳細ボタン押下");

    int selectedIndex =
        employeeList.getSelectedIndex();

    if(selectedIndex == -1){
        JOptionPane.showMessageDialog(
            this,
            "社員を選択してください"
        );
        return;
    }

    Employee emp =
        manager.getEmployees().get(
            selectedIndex
        );

    JOptionPane.showMessageDialog(
        this,
    emp.getDetailInfo()
    );
}
    /**
     * 社員情報リストをリフレッシュする
     */
    private void refreshEmployeeList() {
        employeeListModel.clear();  // リストをクリアしてから再設定
        for (Employee emp : manager.getEmployees()) {
            employeeListModel.addElement(emp.toString());
        }
    }
}
