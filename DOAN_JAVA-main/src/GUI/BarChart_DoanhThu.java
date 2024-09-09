/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.ThongKeDTO;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart_DoanhThu extends JPanel {

    private ArrayList<ThongKeDTO> thk;

    public BarChart_DoanhThu(String applicationTitle, ArrayList<ThongKeDTO> thk, ArrayList<String> currentday) {
        this.thk = thk;
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê doanh thu",
                currentday.get(0) + " - " + currentday.get(1),
                "Đồng",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        this.setLayout(new BorderLayout());
        this.add(chartPanel,BorderLayout.CENTER);
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO tk : thk) {
            dataset.addValue(tk.getThanhTien(), tk.getTenSP(), "Số tiền bán được");
        }
        return dataset;
    }

    public static void main(String[] args) {
        ThongKeGUI tkGUI = new ThongKeGUI(800, 600);
        ArrayList<String> currentday = new ArrayList<>();
        currentday.add("2023/01/01");
        currentday.add("2024/05/16");
        currentday.add("Tất cả");
        tkGUI.ShowdoanhThu(currentday);
        BarChart_DoanhThu chart = new BarChart_DoanhThu("Sơ đồ thống kê doanh thu", tkGUI.ds, currentday);
        
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.add(chart);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
