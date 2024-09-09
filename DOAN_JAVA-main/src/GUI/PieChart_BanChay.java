package GUI;

import DTO.ThongKeDTO;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieChart_BanChay extends JPanel {

    static ArrayList<ThongKeDTO> thk;
    Font font = new Font("Tahoma", Font.BOLD, 13);

    public PieChart_BanChay(String title, ArrayList<ThongKeDTO> thk) {
        this.thk = thk;
        
        JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        this.setLayout(new BorderLayout());
        this.add(chartPanel,BorderLayout.CENTER);
    }

    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (ThongKeDTO thongKe : thk) {
            dataset.setValue(thongKe.getTenSP(), Double.valueOf(thongKe.getSoLuong()));
        }
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Sản phẩm bán chạy", // chart title
                dataset, // data    
                true, // include legend   
                true,
                false);

        // Đặt phông chữ cho tiêu đề biểu đồ
        chart.setTitle(new TextTitle("Sản phẩm bán chạy",
                new Font("Tahoma", Font.BOLD, 20))
        );

        // Đặt phông chữ cho chú thích
        if (chart.getLegend() != null) {
            chart.getLegend().setItemFont(new Font("Tahoma", Font.BOLD, 13));
        }

        // Đặt phông chữ cho nhãn mục
        StandardChartTheme chartTheme = (StandardChartTheme) StandardChartTheme.createJFreeTheme();
        chartTheme.setExtraLargeFont(new Font("Tahoma", Font.BOLD, 20)); // tiêu đề
        chartTheme.setRegularFont(new Font("Tahoma", Font.BOLD, 13));   // nhãn mục
        chartTheme.setLargeFont(new Font("Tahoma", Font.BOLD, 13));     // chú thích
        chartTheme.apply(chart);

        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        ThongKeGUI tkGUI = new ThongKeGUI(800, 600);
        ArrayList<String> currentday = new ArrayList<>();
        currentday.add("2023/01/01");
        currentday.add("2024/01/01");
        currentday.add("10");
        tkGUI.ShowbanChay(currentday);
        System.out.println(tkGUI.ds.size());
        
        PieChart_BanChay demo = new PieChart_BanChay("Sản phẩm bán chạy", tkGUI.ds);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.add(demo);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
