/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.koneksi_HP;
import view.v_barang;

/**
 *
 * @author asus
 */
public class model_barang implements controller.c_barang{

    @Override
    public void Cari(v_barang vb) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Tambah(v_barang vb) throws SQLException {
        try {
            Connection con = koneksi_HP.getcon();
            String sql = "INSERT INTO barang VALUES (?,?,?,?,?)";
            PreparedStatement prr = con.prepareStatement(sql);
            prr.setInt(1, Integer.parseInt(vb.txtid.getText()));
            prr.setString(2, vb.txtjenis.getText());
            prr.setString(3, (String) vb.cmbwarna.getSelectedItem());
            prr.setString(4, (String) vb.cmbspek.getSelectedItem());
            prr.setInt(5, Integer.parseInt(vb.txtharga.getText()));
            prr.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
            prr.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal di simpan");
            System.err.println("Error "+ e);
        } finally{
            Tampil(vb);
            Reset(vb);
            vb.setLebarKolom();
        }
    }

    @Override
    public void Ubah(v_barang vb) throws SQLException {
       try {
            Connection con = koneksi_HP.getcon();
          String sql = "UPDATE barang SET JENIS=?, WARNA=?, SPESIFIKASI=?, HARGA=? WHERE ID=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setInt(5, Integer.valueOf(vb.txtid.getText()));
            prepare.setString(1, vb.txtjenis.getText());
            prepare.setString(2, (String) vb.cmbwarna.getSelectedItem());
            prepare.setString(3, (String) vb.cmbspek.getSelectedItem());
            prepare.setInt(4, Integer.valueOf(vb.txtharga.getText()));
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
            } finally{
                Tampil(vb);
                vb.setLebarKolom();
                Reset(vb);
        }
    }

    @Override
    public void Hapus(v_barang vb) throws SQLException {
       try {
            Connection con = koneksi_HP.getcon();
            String sql = "DELETE FROM barang WHERE ID=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setInt(1, Integer.valueOf(vb.txtid.getText()));
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            Tampil(vb);
            vb.setLebarKolom();
            Reset(vb);
        }
    }

    @Override
    public void Keluar(v_barang vb) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Tampil(v_barang vb) throws SQLException {
        vb.tblmodel.getDataVector().removeAllElements();
        vb.tblmodel.fireTableDataChanged();
        
        try {
            Connection con = koneksi_HP.getcon();
            Statement stt = con.createStatement();
            // Query menampilkan semua data pada tabel siswa
            // dengan urutan NIS dari kecil ke besar
            String sql = "SELECT * FROM barang ORDER BY ID ASC";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[8];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                ob[4] = res.getString(5);
                vb.tblmodel.addRow(ob);
            }        
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Reset(v_barang vb) throws SQLException {
        vb.txtjenis.setText("");
        vb.cmbwarna.setSelectedIndex(0);
        vb.cmbspek.setSelectedIndex(0);
        vb.txtharga.setText("");
    }

    @Override
    public void Kliktable(v_barang vb) throws SQLException {
        try {
            int pilih = vb.tblbarang.getSelectedRow();
            if (pilih == -1) {
               return;
            }
            vb.txtid.setText(vb.tblmodel.getValueAt(pilih, 0).toString());
            vb.txtjenis.setText(vb.tblmodel.getValueAt(pilih, 1).toString());
            vb.cmbwarna.setSelectedItem(vb.tblmodel.getValueAt(pilih, 2).toString());
            vb.cmbspek.setSelectedItem(vb.tblmodel.getValueAt(pilih, 3).toString());
            vb.txtharga.setText(vb.tblmodel.getValueAt(pilih, 4).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
