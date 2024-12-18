
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.DriverManager;

public class User extends javax.swing.JFrame {

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    private int userId;

    public User(int userId) {
        initComponents();
        this.userId = userId;
        database DB = new database();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        tampildata();
        loadUserData();
    }

    @SuppressWarnings("unchecked")

    public void setUserName(String userName) {
        Nama.setText(userName);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private void openHistory() {
        History historyFrame = new History();
        historyFrame.setHistoryId(userId);
        historyFrame.setVisible(true);
        this.dispose();
    }

    private void clearFields() {
        txtId.setText("");
        txtjudulBuku.setText("");
        txtAuthor.setText("");
        txtHarga.setText("");
        txtQuantity.setText("");
    }

    private void loadUserData() {
        String sql = "SELECT Nama FROM register WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database_buku", "root", "Anangnaz"); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String userName = rs.getString("Nama");
                    setUserName(userName);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saat mengambil data pengguna: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void tampildata() {
        DefaultTableModel tbl = new DefaultTableModel();

        tbl.addColumn("ID");
        tbl.addColumn("Judul Buku");
        tbl.addColumn("Author");
        tbl.addColumn("Harga");
        tbl.addColumn("Quantity");

        try {
            String sql = "SELECT * FROM buku";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);

            tbl.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("id");
                String judulBuku = rs.getString("judulBuku");
                String author = rs.getString("Author");
                int harga = rs.getInt("Harga");
                int quantity = rs.getInt("Quantity");

                tbl.addRow(new Object[]{id, judulBuku, author, harga, quantity});
            }

            tblData.setModel(tbl);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    void caridata() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Judul Buku");
        tbl.addColumn("Author");
        tbl.addColumn("Harga");
        tbl.addColumn("Quantity");

        String cari = txtCari.getText();

        String sql = "SELECT * FROM buku WHERE judulBuku LIKE ? OR Author LIKE ? OR Harga LIKE ? OR Quantity LIKE ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            String searchPattern = "%" + cari + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern); 
            pstmt.setString(3, searchPattern); 
            pstmt.setString(4, searchPattern); 

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tbl.addRow(new Object[]{
                        rs.getString("ID"),
                        rs.getString("judulBuku"), 
                        rs.getString("Author"),
                        rs.getString("Harga"),
                        rs.getString("Quantity")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        tblData.setModel(tbl);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayTotal = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        txtjudulBuku = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        Bcover = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        billData = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Bayar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        total = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Nama = new javax.swing.JLabel();
        displayTotal2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        displayTotal.setBackground(new java.awt.Color(80, 12, 1));
        displayTotal.setForeground(new java.awt.Color(255, 255, 255));
        displayTotal.setkEndColor(new java.awt.Color(122, 183, 255));
        displayTotal.setkStartColor(new java.awt.Color(21, 83, 161));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Harga");

        txtjudulBuku.setEditable(false);
        txtjudulBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjudulBukuActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Viner Hand ITC", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Viner Hand ITC", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Iswa Book Store");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Author");

        txtHarga.setEditable(false);
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Judul Buku");

        txtAuthor.setEditable(false);
        txtAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAuthorActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity");

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "Judul Buku", "Author", "Harga", "Quantity"
            }
        ));
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        Bcover.setForeground(new java.awt.Color(255, 255, 255));
        Bcover.setText("Book Cover");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("id");

        txtId.setEditable(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        billData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "Judul Buku", "Author", "Harga", "Quantity"
            }
        ));
        billData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(billData);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bill Anda");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Tagihan Anda");

        Bayar.setText("Bayar");
        Bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BayarActionPerformed(evt);
            }
        });

        jButton1.setText("Tambahkan Ke Bill");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Masukkan Pembayaran");

        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });

        jButton2.setText("History");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Struk");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Halo selamat datang, ");

        Nama.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        Nama.setForeground(new java.awt.Color(255, 255, 255));
        Nama.setText("jLabel12");

        displayTotal2.setEditable(false);

        jButton4.setText("Log Out");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        jButton5.setText("Cari");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displayTotalLayout = new javax.swing.GroupLayout(displayTotal);
        displayTotal.setLayout(displayTotalLayout);
        displayTotalLayout.setHorizontalGroup(
            displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayTotalLayout.createSequentialGroup()
                .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayTotalLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(165, 165, 165))
                    .addGroup(displayTotalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(156, 156, 156)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nama)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayTotalLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayTotalLayout.createSequentialGroup()
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(displayTotalLayout.createSequentialGroup()
                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(displayTotalLayout.createSequentialGroup()
                                .addComponent(txtjudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2)))
                        .addGap(28, 28, 28)
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(Bcover, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayTotalLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(displayTotalLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayTotalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(displayTotalLayout.createSequentialGroup()
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(displayTotal2)
                            .addComponent(txtBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayTotalLayout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(total)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayTotalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
            .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayTotalLayout.createSequentialGroup()
                    .addContainerGap(856, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(308, 308, 308)))
        );
        displayTotalLayout.setVerticalGroup(
            displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayTotalLayout.createSequentialGroup()
                .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayTotalLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(displayTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(total))
                        .addGap(28, 28, 28)
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(Bayar)))
                    .addGroup(displayTotalLayout.createSequentialGroup()
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayTotalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11)
                                    .addComponent(Nama))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtjudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(28, 28, 28)
                                .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(displayTotalLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(Bcover, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addContainerGap(273, Short.MAX_VALUE))
            .addGroup(displayTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayTotalLayout.createSequentialGroup()
                    .addContainerGap(603, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(256, 256, 256)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtjudulBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjudulBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjudulBukuActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAuthorActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void GetBookCover() {
        String sql = "SELECT Gambar FROM buku WHERE id = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Key);

            rs = pst.executeQuery();
            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("Gambar");
                Bcover.setIcon(ResizeCover(null, imageBytes));
            } else {
                System.out.println("Data tidak ditemukan.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ImageIcon ResizeCover(String ImagePath, byte[] pic) {
        ImageIcon MyImage = null;
        if (ImagePath != null) {
            MyImage = new ImageIcon(ImagePath);
        } else {
            MyImage = new ImageIcon(pic);
        }
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(Bcover.getWidth(), Bcover.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    int Key = 0;
    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        int baris = tblData.getSelectedRow();
        Key = Integer.parseInt(tblData.getValueAt(baris, 0).toString());
        if (baris != -1) {
            txtId.setText(tblData.getValueAt(baris, 0).toString());
            txtjudulBuku.setText(tblData.getValueAt(baris, 1).toString());
            txtAuthor.setText(tblData.getValueAt(baris, 2).toString());
            txtHarga.setText(tblData.getValueAt(baris, 3).toString());
            GetBookCover();
        }
    }//GEN-LAST:event_tblDataMouseClicked

    private int totalHargaKeseluruhan = 0;

    void billData() {
        if (txtjudulBuku.getText().isEmpty() || txtAuthor.getText().isEmpty()
                || txtHarga.getText().isEmpty() || txtQuantity.getText().isEmpty()
                || txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi.");
            return;
        }

        String judulBuku = txtjudulBuku.getText();
        String author = txtAuthor.getText();
        int harga;
        int quantity;

        String quantityText = txtQuantity.getText().trim();
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantity harus diisi.");
            return;
        }

        if (!quantityText.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Quantity harus berupa angka.");
            return;
        }

        try {
            String hargaText = txtHarga.getText().trim();
            harga = Integer.parseInt(hargaText);
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Harga dan Quantity harus berupa angka.");
            return;
        }

        String inputId = txtId.getText();
        int availableQuantity = getAvailableQuantityFromDatabase(inputId);

        if (quantity > availableQuantity) {
            JOptionPane.showMessageDialog(null,
                    "Jumlah quantity yang dimasukkan (" + quantity
                    + ") melebihi jumlah yang tersedia (" + availableQuantity + ").");
            return;
        }

        DefaultTableModel BillTableModel = (DefaultTableModel) billData.getModel();
        BillTableModel.addRow(new Object[]{inputId, judulBuku, author, harga, quantity});

        clearFields();

        int totalPrice = quantity * harga;
        totalHargaKeseluruhan += totalPrice;
        displayTotal2.setText(String.valueOf(totalHargaKeseluruhan));
    }

    void updateBookStock(String bookId, int newQuantity) {
        String sql = "UPDATE buku SET Quantity = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database_buku", "root", "Anangnaz"); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saat memperbarui stok: " + e.getMessage());
        }
    }

    void saveTransactionToDatabase(int userId, String judulBuku, String author, int harga, int quantity, int total) {
        String sql = "INSERT INTO transaksi (judulBuku, Author, Harga, Quantity, jumlahBayar, userId) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database_buku", "root", "Anangnaz"); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, judulBuku);
            pstmt.setString(2, author);
            pstmt.setInt(3, harga);
            pstmt.setInt(4, quantity);
            pstmt.setInt(5, total);
            pstmt.setInt(6, userId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan.");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan transaksi.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saat menyimpan transaksi: " + e.getMessage());
        }
    }

    private int getAvailableQuantityFromDatabase(String bookId) {
        int quantity = 0;

        String sql = "SELECT Quantity FROM buku WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, bookId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    quantity = rs.getInt("Quantity");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saat mengambil data dari database: " + e.getMessage());
        }

        return quantity;
    }

    public String getDisplayTotal2Value() {
        return displayTotal2.getText();
    }


    private void billDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billDataMouseClicked

    }//GEN-LAST:event_billDataMouseClicked

    void prosesPembayaran() {
        if (displayTotal2 == null || displayTotal2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Total belum terisi. Silakan hitung total terlebih dahulu.");
            return;
        }

        int totalHarga = Integer.parseInt(displayTotal2.getText());

        String bayarText = txtBayar.getText().trim();
        int bayar;

        if (bayarText.isEmpty() || !bayarText.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Masukkan jumlah uang yang valid.");
            return;
        }

        bayar = Integer.parseInt(bayarText);

        if (bayar < totalHarga) {
            JOptionPane.showMessageDialog(null, "Maaf, uang Anda kurang.");
            return;
        }

        int kembalian = bayar - totalHarga;
        JOptionPane.showMessageDialog(null, "Pembayaran Berhasil!");
        JOptionPane.showMessageDialog(null, "Kembalian Anda: " + kembalian);

        DefaultTableModel BillTableModel = (DefaultTableModel) billData.getModel();
        for (int i = 0; i < BillTableModel.getRowCount(); i++) {
            if (BillTableModel.getValueAt(i, 0) == null || BillTableModel.getValueAt(i, 1) == null
                    || BillTableModel.getValueAt(i, 2) == null || BillTableModel.getValueAt(i, 3) == null
                    || BillTableModel.getValueAt(i, 4) == null) {
                continue;
            }

            String judulBuku = (String) BillTableModel.getValueAt(i, 1);
            String author = (String) BillTableModel.getValueAt(i, 2);
            Integer hargaObj = (Integer) BillTableModel.getValueAt(i, 3);
            Integer quantityObj = (Integer) BillTableModel.getValueAt(i, 4);

            if (hargaObj == null || quantityObj == null) {
                JOptionPane.showMessageDialog(null, "Data pada tabel tidak lengkap. Pastikan semua field terisi.");
                return;
            }

            int harga = hargaObj.intValue();
            int quantity = quantityObj.intValue();

            saveTransactionToDatabase(userId, judulBuku, author, harga, quantity, totalHarga);

            String bookId = (String) BillTableModel.getValueAt(i, 0);
            int availableQuantity = getAvailableQuantityFromDatabase(bookId);

            if (availableQuantity >= quantity) {
                int newQuantity = availableQuantity - quantity;
                updateBookStock(bookId, newQuantity);
            } else {
                JOptionPane.showMessageDialog(null, "Stok tidak cukup untuk buku: " + judulBuku);
                return;
            }
        }

        txtBayar.setText("");
        BillTableModel.setRowCount(0);
        totalHargaKeseluruhan = 0;
        displayTotal2.setText("0");
    }

    void exportExcel() {
        FileWriter fileWriter;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("[B] export_output/excel[/B]"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                TableModel tModel = tblData.getModel();
                fileWriter = new FileWriter(new File(chooser.getSelectedFile()
                        + ".xls"));
                for (int i = 0; i < tModel.getColumnCount(); i++) {
                    fileWriter.write(tModel.getColumnName(i).toUpperCase() + "\t");
                }
                fileWriter.write("\n");
                for (int i = 0; i < tModel.getRowCount(); i++) {
                    for (int j = 0; j < tModel.getColumnCount(); j++) {
                        fileWriter.write(tModel.getValueAt(i, j).toString() + "\t");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Simpan Berhasil!!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void cetakStruk() {
        String query = "SELECT * FROM transaksi WHERE userId = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database_buku", "root", "Anangnaz"); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Simpan Struk sebagai PDF");
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
            fileChooser.setSelectedFile(new File("struk.pdf")); // Nama file default

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
                }

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
                document.open();

                document.add(new Paragraph("================================="));
                document.add(new Paragraph("          Iswa Book Store        "));
                document.add(new Paragraph("         www.IswaBookStore.com      "));
                document.add(new Paragraph("================================="));
                document.add(new Paragraph("Tanggal: " + java.time.LocalDate.now()));
                document.add(new Paragraph("================================="));

                while (rs.next()) {
                    String judulBuku = rs.getString("judulBuku");
                    String author = rs.getString("Author");
                    double harga = rs.getDouble("Harga");
                    int quantity = rs.getInt("Quantity");
                    int totalBayar = rs.getInt("jumlahBayar");

                    document.add(new Paragraph("Judul Buku: " + judulBuku));
                    document.add(new Paragraph("Author: " + author));
                    document.add(new Paragraph("Harga: Rp " + harga));
                    document.add(new Paragraph("Quantity: " + quantity));
                    document.add(new Paragraph("Total Bayar: Rp " + totalBayar));
                    document.add(new Paragraph("================================="));
                }

                document.add(new Paragraph("Terima kasih telah berbelanja!"));
                document.add(new Paragraph("================================="));

                document.close();

                System.out.println("Struk telah dicetak dalam bentuk file PDF di: " + fileToSave.getAbsolutePath());
            } else {
                System.out.println("Penyimpanan dibatalkan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data transaksi");
            e.printStackTrace();
        } catch (DocumentException e) {
            System.out.println("Gagal mencetak file PDF");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Gagal menulis file PDF");
            e.printStackTrace();
        }
    }

    private void BayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BayarActionPerformed

        prosesPembayaran();

    }//GEN-LAST:event_BayarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        billData();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBayarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        openHistory();
        System.out.println("User  ID sebelum membuka history: " + userId);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cetakStruk();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed

    }//GEN-LAST:event_txtIdActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        caridata();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bayar;
    private javax.swing.JLabel Bcover;
    private javax.swing.JLabel Nama;
    private javax.swing.JTable billData;
    private keeptoo.KGradientPanel displayTotal;
    private javax.swing.JTextField displayTotal2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblData;
    private javax.swing.JLabel total;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtjudulBuku;
    // End of variables declaration//GEN-END:variables
}
