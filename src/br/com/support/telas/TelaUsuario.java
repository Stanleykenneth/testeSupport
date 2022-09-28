package br.com.support.telas;

import java.sql.*;
import br.com.support.dal.ModuloConexao;
import javax.swing.JOptionPane;

/*
 * @author Kenneth Stanley Neves Dos Santos
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void consultar() {
        String sql = "select * from support.usuarios where id_usuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuFone.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");               
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar() {
        String sql = "insert into support.usuarios (id_usuario, usuario, fone, login, senha, perfil) values (?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuFone.getText());
            pst.setString(4, txtUsuLogin.getText());
            pst.setString(5, txtUsuSenha.getText());
            pst.setString(6, cboUsuPerfil.getSelectedItem().toString());

            if ((txtUsuId.getText().isEmpty())
                    || (txtUsuNome.getText().isEmpty())
                    || (txtUsuLogin.getText().isEmpty())
                    || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja alterar os dados deste usuário?",
                "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "update support.usuarios set usuario=?, fone=?, login=?, senha=?, perfil=? where id_usuario=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuNome.getText());
                pst.setString(2, txtUsuFone.getText());
                pst.setString(3, txtUsuLogin.getText());
                pst.setString(4, txtUsuSenha.getText());
                pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
                pst.setString(6, txtUsuId.getText());

                if ((txtUsuId.getText().isEmpty())
                        || (txtUsuNome.getText().isEmpty())
                        || (txtUsuLogin.getText().isEmpty())
                        || (txtUsuSenha.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                } else {
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Dados do usuário alterado com sucesso!");
                        limpar();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário",
                "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from support.usuarios where id_usuario=?";
            try {

                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                }
                limpar();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void limpar() {

        txtUsuId.setText(null);
        txtUsuNome.setText(null);
        txtUsuFone.setText(null);
        txtUsuLogin.setText(null);
        txtUsuSenha.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuId = new javax.swing.JLabel();
        lblUsuNome = new javax.swing.JLabel();
        lblUsuLogin = new javax.swing.JLabel();
        lblUsuSenha = new javax.swing.JLabel();
        lblUsuPerfil = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JPasswordField();
        lblUsuFone = new javax.swing.JLabel();
        txtUsuFone = new javax.swing.JTextField();
        btnUseCreate = new javax.swing.JButton();
        btnUsuPesquisar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(512, 407));

        lblUsuId.setText("*Id");

        lblUsuNome.setText("*Nome");

        lblUsuLogin.setText("*Login");

        lblUsuSenha.setText("*Senha");

        lblUsuPerfil.setText("*Perfil");

        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });

        lblUsuFone.setText("Fone");

        btnUseCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/support/icones/create2.png"))); // NOI18N
        btnUseCreate.setToolTipText("Adicionar");
        btnUseCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseCreateActionPerformed(evt);
            }
        });

        btnUsuPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/support/icones/read1 (1).png"))); // NOI18N
        btnUsuPesquisar.setToolTipText("Pesquisar");
        btnUsuPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuPesquisarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/support/icones/update2.png"))); // NOI18N
        btnAlterar.setToolTipText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/support/icones/delete2.png"))); // NOI18N
        btnDeletar.setToolTipText("Deletar");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "usuário", " ", " " }));

        jLabel1.setText("* Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(btnUseCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUsuPesquisar))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblUsuLogin)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUsuId)
                                    .addComponent(lblUsuNome))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 113, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUsuPerfil)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUsuSenha)
                                            .addComponent(lblUsuFone))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuId)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuNome)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuFone)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuLogin)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuSenha)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuPerfil)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUseCreate)
                    .addComponent(btnUsuPesquisar)
                    .addComponent(btnAlterar)
                    .addComponent(btnDeletar))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        setBounds(0, 0, 512, 364);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuNomeActionPerformed

    private void btnUsuPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuPesquisarActionPerformed
        consultar();
    }//GEN-LAST:event_btnUsuPesquisarActionPerformed

    private void btnUseCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseCreateActionPerformed
        adicionar();
    }//GEN-LAST:event_btnUseCreateActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        deletar();
    }//GEN-LAST:event_btnDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnUseCreate;
    private javax.swing.JButton btnUsuPesquisar;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblUsuFone;
    private javax.swing.JLabel lblUsuId;
    private javax.swing.JLabel lblUsuLogin;
    private javax.swing.JLabel lblUsuNome;
    private javax.swing.JLabel lblUsuPerfil;
    private javax.swing.JLabel lblUsuSenha;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JPasswordField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
