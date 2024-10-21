package dao.daoManager;

import java.util.List;

import dao.entity.Machine;

public interface MachineDAO {
	
	public  void add(Machine e);
	
public  Machine edit(Machine e);
	
	public  void delete(int id); 
	
	public List<Machine> findAll();
	
	public Machine findById(int id);
	
	public Machine findByCodeNom(String code);
	
	public  List<Machine> findListMachineByCodeDepot(String codeDepot);

}
