/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.v_barang;

/**
 *
 * @author asus
 */
public interface c_barang {
    public void Cari(v_barang vb) throws SQLException;
    public void Tambah(v_barang vb) throws SQLException;
    public void Ubah(v_barang vb) throws SQLException;
    public void Hapus(v_barang vb) throws SQLException;
    public void Keluar(v_barang vb) throws SQLException;
    public void Tampil(v_barang vb) throws SQLException;
    public void Reset(v_barang vb)throws SQLException;
    public void Kliktable(v_barang vb)throws SQLException;
    
}
