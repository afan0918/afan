import fr.ign.cogit.roc4j.graphics.RocSpace;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import static javax.swing.UIManager.setLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Afan Chen
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        try {
            setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        java.awt.Dimension scr_size =java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scr_size.width - 720) / 2,(scr_size.height - 600) / 2);

        setTitle("Afan");
        initComponents();
        jPanel5.setTitle("");
        jPanel5.setYLabel("Sensitivity");
        jPanel5.setXLabel("1 - specificity");
        jPanel4.setTitle("");
        jPanel4.setYLabel("Sensitivity");
        jPanel4.setXLabel("1 - specificity");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        TabPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        RunAllPermutations = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        LogisticRegression = new javax.swing.JCheckBox();
        SVM = new javax.swing.JCheckBox();
        HomeRun = new javax.swing.JButton();
        jPanel5 = new RocSpace();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        MaxIter = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Lambda = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Tolerance = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        LogisticRegressionModel = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        LogisticRegressionRun = new javax.swing.JButton();
        RunAllPermutations1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        SVMModel = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        SVMRUN = new javax.swing.JButton();
        RunAllPermutations2 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel4 = new RocSpace();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ImportCSVFile = new javax.swing.JMenuItem();
        View = new javax.swing.JMenu();
        ViewFScoresLineChart = new javax.swing.JMenuItem();
        Export = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        TabPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabPane.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        TabPane.setPreferredSize(new java.awt.Dimension(800, 700));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        RunAllPermutations.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        RunAllPermutations.setText("Run all permutations");

        jLabel1.setFont(new java.awt.Font("微軟正黑體", 1, 16)); // NOI18N
        jLabel1.setText("Please import a file.");

        LogisticRegression.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        LogisticRegression.setText("Logistic Regression");
        LogisticRegression.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        SVM.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        SVM.setText("SVM");
        SVM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        HomeRun.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        HomeRun.setText("Run");
        HomeRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeRunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 482, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(HomeRun))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(SVM)
                                                        .addComponent(LogisticRegression)
                                                        .addComponent(RunAllPermutations))
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(RunAllPermutations)
                                                .addGap(18, 18, 18)
                                                .addComponent(LogisticRegression)
                                                .addGap(18, 18, 18)
                                                .addComponent(SVM))
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(HomeRun)
                                .addGap(41, 41, 41))
        );

        TabPane.addTab("Home", jPanel1);

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("微軟正黑體", 1, 18)); // NOI18N
        jLabel2.setText("Training");

        jLabel3.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jLabel3.setText("MaxIter :");

        MaxIter.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        MaxIter.setText("100");
        MaxIter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel13.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jLabel13.setText("λ :");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Lambda.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        Lambda.setText("0.1");
        Lambda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel16.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jLabel16.setText("Tolerance :");

        Tolerance.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        Tolerance.setText("1e-5");
        Tolerance.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel31.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel31.setText("when tolerance is achieved.");

        jLabel30.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel30.setText("To stop searching for a minimax");

        jLabel28.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel28.setText("Impact of the regularization");
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel29.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel29.setText("term by multiplying its value.");

        jLabel5.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jLabel5.setText("10000");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jLabel4.setText("100");

        jSlider1.setMinimum(1);
        jSlider1.setValue(1);
        jSlider1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("微軟正黑體", 1, 18)); // NOI18N
        jLabel10.setText("Check");

        jLabel9.setFont(new java.awt.Font("微軟正黑體", 1, 18)); // NOI18N
        jLabel9.setText("Show the Model");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        LogisticRegressionModel.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        LogisticRegressionModel.setText("Model");

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        LogisticRegressionRun.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        LogisticRegressionRun.setText("Run");
        LogisticRegressionRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogisticRegressionRunActionPerformed(evt);
            }
        });

        RunAllPermutations1.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        RunAllPermutations1.setText("Run all permutations");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(LogisticRegressionRun)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(96, 96, 96)
                                                        .addComponent(MaxIter, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                                        .addComponent(jLabel3)
                                                                        .addGap(15, 15, 15))
                                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                        .addComponent(jLabel4)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel5))
                                                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(43, 43, 43)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel13)
                                                                .addComponent(jLabel28)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(Lambda, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel29))
                                                                .addComponent(jLabel9))
                                                        .addGap(47, 47, 47)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel31)
                                                                .addComponent(jLabel30)
                                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                        .addComponent(jLabel16)
                                                                        .addGap(36, 36, 36)
                                                                        .addComponent(Tolerance, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(RunAllPermutations1)
                                                                .addComponent(LogisticRegressionModel)
                                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(62, 62, 62)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(MaxIter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel13)
                                                        .addComponent(Lambda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel28)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel29))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel16)
                                                        .addComponent(Tolerance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel30)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel31)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(RunAllPermutations1)
                                                .addGap(18, 18, 18)
                                                .addComponent(LogisticRegressionModel))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LogisticRegressionRun)
                                .addContainerGap(47, Short.MAX_VALUE))
        );

        TabPane.addTab("Logistic Regression", jPanel2);

        jLabel11.setFont(new java.awt.Font("微軟正黑體", 1, 18)); // NOI18N
        jLabel11.setText("Training");

        jLabel14.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jLabel14.setText("Cost");

        jLabel22.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel22.setText("Cost defines the weight of how much samples");

        jLabel23.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel23.setText("inside the margin contribute to the overall error.");

        jTextField4.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jTextField4.setText("1");

        jLabel15.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jLabel15.setText("Margin of tolerance");

        jTextField5.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        jTextField5.setText("1e-8");

        jLabel24.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel24.setText("This is the tolerance for the stopping criteria.");

        jLabel25.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel25.setText("To stop searching for a minimum (or maximum)");

        jLabel26.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel26.setText("once some tolerance is achieved, i.e. once you're close enough.");

        jLabel19.setFont(new java.awt.Font("微軟正黑體", 1, 18)); // NOI18N
        jLabel19.setText("Check");

        SVMModel.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        SVMModel.setText("Model");

        jLabel18.setFont(new java.awt.Font("微軟正黑體", 1, 18)); // NOI18N
        jLabel18.setText("Show the model");

        SVMRUN.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        SVMRUN.setText("Run");
        SVMRUN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SVMRUNActionPerformed(evt);
            }
        });

        RunAllPermutations2.setFont(new java.awt.Font("微軟正黑體", 0, 16)); // NOI18N
        RunAllPermutations2.setText("Run all permutations");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(RunAllPermutations2)
                                                        .addComponent(SVMModel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(jLabel14)
                                                                                .addGap(142, 142, 142)
                                                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel11)
                                                                                .addComponent(jLabel22)
                                                                                .addComponent(jLabel23)))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel24)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(jLabel15)
                                                                                .addGap(65, 65, 65)
                                                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jLabel25)
                                                                        .addComponent(jLabel26)))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jLabel19)
                                                                .addGap(191, 191, 191)
                                                                .addComponent(jLabel18)))
                                                .addGap(0, 48, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(SVMRUN)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel14)
                                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel15)
                                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addGap(18, 18, 18)
                                                .addComponent(RunAllPermutations2)
                                                .addGap(18, 18, 18)
                                                .addComponent(SVMModel))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(SVMRUN)
                                .addContainerGap(29, Short.MAX_VALUE))
        );

        TabPane.addTab("SVM", jPanel3);

        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 701, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 505, Short.MAX_VALUE)
        );

        TabPane.addTab("ROC curve", jPanel4);

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setText("File");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenu1.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N

        ImportCSVFile.setText("Import File (.csv)");
        ImportCSVFile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ImportCSVFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportCSVFileActionPerformed(evt);
            }
        });
        jMenu1.add(ImportCSVFile);

        jMenuBar1.add(jMenu1);

        View.setText("View");
        View.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N

        ViewFScoresLineChart.setText("FScores Line Chart");
        ViewFScoresLineChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewFScoresLineChartActionPerformed(evt);
            }
        });
        View.add(ViewFScoresLineChart);

        jMenuBar1.add(View);

        Export.setText("Export");
        Export.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Export.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        Export.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExportMouseClicked(evt);
            }
        });
        jMenuBar1.add(Export);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>

    /**
     * 查看哪些選像被勾選，進行相應的分析
     *
     * @param evt 主頁的RUN
     */
    private void HomeRunActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (RunAllPermutations.isSelected() && LogisticRegression.isSelected()) {
            logisticRegressionMethod = new LogisticRegressionMethod();
            logisticRegressionMethod.RunAllPermutationsLogisticRegression(DataBase.ROC.data, DataBase.csvFile.IntLabelMat, 100, 0.1, 1e-5);
        } else if (LogisticRegression.isSelected()) {
            logisticRegressionMethod = new LogisticRegressionMethod();
            logisticRegressionMethod.lr(DataBase.ROC.data, DataBase.csvFile.IntLabelMat, 100, 0.1, 1e-5);
        }
        if (RunAllPermutations.isSelected() && SVM.isSelected()) {
            svmMethod = new SVMMethod();
            svmMethod.RunAllPermutationsSVM(DataBase.ROC.data, DataBase.csvFile.IntLabelMat, 1,1e-8);
        } else if (SVM.isSelected()) {
            svmMethod = new SVMMethod();
            svmMethod.svm(DataBase.ROC.data, DataBase.csvFile.IntLabelMat, 1,1e-8);
        }
    }

    /**
     * 當滑動條條被滑動，就改一下數字
     *
     * @param evt
     */
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
        jSlider1.addChangeListener(e -> MaxIter.setText(String.valueOf(jSlider1.getValue() * 100)));
    }

    public static LogisticRegressionMethod logisticRegressionMethod = null;

    private void LogisticRegressionRunActionPerformed(java.awt.event.ActionEvent evt) {

        if (RunAllPermutations1.isSelected()) {
            logisticRegressionMethod = new LogisticRegressionMethod();
            logisticRegressionMethod.RunAllPermutationsLogisticRegression(DataBase.ROC.data, DataBase.csvFile.IntLabelMat, Integer.parseInt(MaxIter.getText()), Double.parseDouble(Lambda.getText()), Double.parseDouble(Tolerance.getText()));
        } else {
            logisticRegressionMethod = new LogisticRegressionMethod();
            logisticRegressionMethod.lr(DataBase.ROC.data, DataBase.csvFile.IntLabelMat, Integer.parseInt(MaxIter.getText()), Double.parseDouble(Lambda.getText()), Double.parseDouble(Tolerance.getText()));
        }

        ArrayList<double[]> weight = logisticRegressionMethod.getWeight();
        ArrayList<Double> FScores = logisticRegressionMethod.getFScore();
        ArrayList<Double> Sensitivity = logisticRegressionMethod.getSensitivity();
        ArrayList<Double> Specificity = logisticRegressionMethod.getSpecificity();
        ArrayList<int[]> Combinations = logisticRegressionMethod.getCombinations();

        if (LogisticRegressionModel.isSelected()) {
            if (RunAllPermutations1.isSelected()) {
                double fscore = 0;
                int pos = 0;
                for (int i = 0; i < FScores.size(); i++) {
                    if (fscore < FScores.get(i)) {
                        fscore = FScores.get(i);
                        pos = i;
                    }
                }

                jTextArea1.setText("The best model is 1/(");
                jTextArea1.append("(" + weight.get(pos)[0] + " * " + DataBase.csvFile.DataTitle[Combinations.get(pos)[0] + 1] + ")");
                for (int i = 1; i < Combinations.get(pos).length; i++) {
                    jTextArea1.append("+ (" + weight.get(pos)[i] + " * " + DataBase.csvFile.DataTitle[Combinations.get(pos)[i] + 1] + ")");
                }
                jTextArea1.append(" + 1) > 0.5\nThe FScore is " + fscore + "\nSensitivity is " + Sensitivity.get(pos) + "Specificity is " + Specificity.get(pos));
            } else {
                jTextArea1.setText("The model is 1/(");
                jTextArea1.append("(" + weight.get(0)[0] + " * " + DataBase.csvFile.DataTitle[1] + ")");
                for (int i = 1; i < weight.get(0).length - 1; i++) {//lr會丟回多一個參數，最後一個是無效項
                    jTextArea1.append("+ (" + weight.get(0)[i] + " * " + DataBase.csvFile.DataTitle[i] + ")");
                }
                jTextArea1.append(" + 1) > 0.5\nThe FScore is " + FScores.get(0) + "\nSensitivity is " + Sensitivity.get(0) + "Specificity is " + Specificity.get(0));
            }
        }
    }

    public static SVMMethod svmMethod = null;

    private void SVMRUNActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        if (RunAllPermutations2.isSelected()) {
            svmMethod = new SVMMethod();
            svmMethod.RunAllPermutationsSVM(DataBase.ROC.data, DataBase.csvFile.IntLabelMat, Double.parseDouble(jTextField4.getText()), Double.parseDouble(jTextField5.getText()));
        } else {
            svmMethod = new SVMMethod();
            svmMethod.svm(DataBase.ROC.data, DataBase.csvFile.IntLabelMat, Double.parseDouble(jTextField4.getText()), Double.parseDouble(jTextField5.getText()));
        }

        ArrayList<double[]> weight = svmMethod.getWeight();
        ArrayList<Double> Bias = svmMethod.getBias();
        ArrayList<Double> FScores = svmMethod.getFScore();
        ArrayList<Double> Sensitivity = svmMethod.getSensitivity();
        ArrayList<Double> Specificity = svmMethod.getSpecificity();
        ArrayList<int[]> Combinations = svmMethod.getCombinations();

        if (SVMModel.isSelected()) {
            if (RunAllPermutations2.isSelected()) {
                double fscore = 0;
                int pos = 0;
                for (int i = 0; i < FScores.size(); i++) {
                    if (fscore < FScores.get(i)) {
                        fscore = FScores.get(i);
                        pos = i;
                    }
                }

                jTextArea3.setText("The best model is ");
                jTextArea3.append("(" + weight.get(pos)[0] + " * " + DataBase.csvFile.DataTitle[Combinations.get(pos)[0] + 1] + ")");
                for (int i = 1; i < Combinations.get(pos).length; i++) {
                    jTextArea3.append("+ (" + weight.get(pos)[i] + " * " + DataBase.csvFile.DataTitle[Combinations.get(pos)[i] + 1] + ")");
                }
                jTextArea3.append(" + (" + Bias.get(pos) + ")) > 0\nThe FScore is " + fscore + "\nSensitivity is " + Sensitivity.get(pos) + ", Specificity is " + Specificity.get(pos));
            } else {
                jTextArea3.setText("The model is 1/(");
                jTextArea3.append("(" + weight.get(0)[0] + " * " + DataBase.csvFile.DataTitle[1] + ")");
                for (int i = 1; i < weight.get(0).length - 1; i++) {//lr會丟回多一個參數，最後一個是無效項
                    jTextArea3.append("+ (" + weight.get(0)[i] + " * " + DataBase.csvFile.DataTitle[i] + ")");
                }
                jTextArea3.append(" + (" + Bias.get(0) + ")) > 0\nThe FScore is " + FScores.get(0) + "\nSensitivity is " + Sensitivity.get(0) + ", Specificity is " + Specificity.get(0));
            }
        }
    }

    /**
     * 把檔案匯入介面叫出來
     * @param evt 按下File中Import File的按鈕
     */
    private void ImportCSVFileActionPerformed(java.awt.event.ActionEvent evt) {
        ImportFile frame = new ImportFile();
        frame.setVisible(true);
    }

    public void AlreadyImportFile(String Path){
        jLabel1.setText(Path);//改成檔案路徑示意使用者
        DataBase.ROC.drawROC();
        jPanel4.updateUI();
        jPanel5.updateUI();
    }

    private void ExportMouseClicked(java.awt.event.MouseEvent evt) {
        ExportFile frame = new ExportFile();
        frame.setVisible(true);
    }

    private void ViewFScoresLineChartActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<double[]> List=new ArrayList<>();
        ArrayList<String> Title=new ArrayList<>();
        if(logisticRegressionMethod!=null){
            ArrayList<Double> FScores = logisticRegressionMethod.getFScore();
            ArrayList<int[]> Combinations = logisticRegressionMethod.getCombinations();

            Title.add("LR");
            AddFScoreChartData(List, FScores, Combinations);
            List.get(List.size()-1)[0]=MathMethod.getMaxValue(DataBase.ROC.fScores);
        }
        if(svmMethod!=null){
            ArrayList<Double> FScores = svmMethod.getFScore();
            ArrayList<int[]> Combinations = svmMethod.getCombinations();

            Title.add("SVM");

            AddFScoreChartData(List, FScores, Combinations);
            List.get(List.size()-1)[0]=MathMethod.getMaxValue(DataBase.ROC.fScores);
        }
        try {
            XYChart xyChart=null;
            xyChart=ChartMethod.DrawFScoreChart(Title, List);
            JButton button=new JButton("Save the plot.");
            XYChart finalXyChart = xyChart;
            button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //得到存檔路徑
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int result = fileChooser.showSaveDialog(null);
                    String path = "";
                    if (result == JFileChooser.APPROVE_OPTION) {
                        path = fileChooser.getSelectedFile().getAbsolutePath();
                    }
                    try {
                        BitmapEncoder.saveBitmapWithDPI(finalXyChart, path+"/FScoresLineChart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 300);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            JFrame frame =new JFrame("FScore");
            frame.add(button, BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new XChartPanel<XYChart>(xyChart));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddFScoreChartData(ArrayList<double[]> List, ArrayList<Double> FScores, ArrayList<int[]> combinations) {
        if (combinations.size()!=0) {
            List.add(MathMethod.GetBestFScore(FScores, combinations, DataBase.ROC.data[0].length));
        }else{
            double[] list=new double[FScores.size()+1];
            for(int i=0;i<FScores.size();i++){
                list[i+1]=FScores.get(i);
            }
            List.add(list);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JMenu Export;
    private javax.swing.JButton HomeRun;
    private javax.swing.JMenuItem ImportCSVFile;
    private javax.swing.JTextField Lambda;
    private javax.swing.JCheckBox LogisticRegression;
    private javax.swing.JCheckBox LogisticRegressionModel;
    private javax.swing.JButton LogisticRegressionRun;
    private javax.swing.JTextField MaxIter;
    private javax.swing.JCheckBox RunAllPermutations;
    private javax.swing.JCheckBox RunAllPermutations1;
    private javax.swing.JCheckBox RunAllPermutations2;
    private javax.swing.JCheckBox SVM;
    private javax.swing.JCheckBox SVMModel;
    private javax.swing.JButton SVMRUN;
    private javax.swing.JTabbedPane TabPane;
    private javax.swing.JTextField Tolerance;
    private javax.swing.JMenu View;
    private javax.swing.JMenuItem ViewFScoresLineChart;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public RocSpace jPanel4;
    public RocSpace jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration
}