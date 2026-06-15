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
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);
        refreshEmployeeList();  // 社員リストの初期化

        // 「追加」ボタン
        JButton addButton = new JButton("社員を追加");
        addButton.addActionListener(e -> addEmployee());

        // 「削除」ボタン
        JButton removeButton = new JButton("選択した社員を削除");
        removeButton.addActionListener(e -> removeEmployee());

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

        // ボタンを配置するパネル
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(sortIdButton);
        buttonPanel.add(sortNameButton);
        buttonPanel.add(sortBirthButton);
        buttonPanel.add(sortExpButton);
        buttonPanel.add(sortIdDescButton);
        buttonPanel.add(sortNameDescButton);
        buttonPanel.add(sortBirthDescButton);
        buttonPanel.add(sortExpDescButton);
        buttonPanel.add(searchButton);

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

        add(new JScrollPane(employeeList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    sortExpDescButton.addActionListener(e -> {
        manager.sortByEngineerExperienceDesc();
        refreshEmployeeList();
    });

    searchButton.addActionListener(e -> {

    String keyword =
        JOptionPane.showInputDialog("検索する氏名を入力");

    employeeListModel.clear();

    for (Employee emp : manager.searchByName(keyword)) {

        employeeListModel.addElement(
            emp.toString()
        );

    }

});
    /**
     * 社員情報を追加する.
     * ボタン押下された時の処理
     */
    private void addEmployee() {
        String id = JOptionPane.showInputDialog("社員IDを入力してください:");
        String name = JOptionPane.showInputDialog("名前を入力してください:");
        LocalDate birthDate = LocalDate.parse(JOptionPane.showInputDialog("生年月日を入力してください (YYYY-MM-DD):"));
        String expStr = JOptionPane.showInputDialog("エンジニア歴(月)を入力してください:");
int engineerExperience = Integer.parseInt(expStr);
        Employee emp = new Employee(id, name, birthDate, engineerExperience);
        manager.addEmployee(emp);  // 社員情報を管理クラスに追加
        refreshEmployeeList();     // 表示リストを更新
    }

    /**
     * 社員情報を削除する.
     * ボタン押下された時の処理
     */
    private void removeEmployee() {
        int selectedIndex = employeeList.getSelectedIndex();
        if (selectedIndex != -1) {
            String empData = employeeListModel.get(selectedIndex);
            Employee emp = manager.getEmployees().stream()
                                  .filter(e -> e.toString().equals(empData))
                                  .findFirst()
                                  .orElse(null);
            if (emp != null) {
                manager.removeEmployee(emp);  // 社員情報を管理クラスから削除
                refreshEmployeeList();       // 表示リストを更新
            }
        }
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
