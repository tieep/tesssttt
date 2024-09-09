package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKeDTO;
import java.util.ArrayList;


public class ThongKeBus {
    private ArrayList<ThongKeDTO> dsThk;

    public ThongKeBus() {
    }
    
    public ArrayList<ThongKeDTO> listDoanhThu(ArrayList<String> data_filters){
        ThongKeDAO thkDAO = new ThongKeDAO();
        dsThk = new ArrayList<>();
        dsThk = thkDAO.listDoanhThu(data_filters);
        return dsThk;
    }
    
    public ArrayList<ThongKeDTO> listBanChay(ArrayList<String> data_filters){
        ThongKeDAO thkDAO = new ThongKeDAO();
        dsThk = new ArrayList<>();
        dsThk = thkDAO.listBanChay(data_filters);
        return dsThk;
    }
}
