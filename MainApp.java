import javax.swing.SwingUtilities;

/*
 * アプリのエントリーポイントとなるクラス
 */
public class MainApp {

    /**
     * メイン関数
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeManager manager = new EmployeeManager();  // 社員管理クラスの初期化
            EmployeeView view = new EmployeeView(manager);    // GUIの初期化
            view.setVisible(true);                          // GUIの表示
        });
    }
}
