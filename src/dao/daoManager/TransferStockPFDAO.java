package dao.daoManager;

import java.util.List;

import dao.entity.TransferStockPF;

public interface TransferStockPFDAO {
	
	public  void add(TransferStockPF e);
	
public  TransferStockPF edit(TransferStockPF e);
	
	public  void delete(int id); 
	
	public List<TransferStockPF> findAll();
	
	public TransferStockPF findById(int id);
	
	public TransferStockPF findByCodeTransfert(String codeTransfert);
	
	public void deleteObject(TransferStockPF p);
	public void ViderSession();

}
