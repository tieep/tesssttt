package BUS;

import DAO.KhoDAO;
import DTO.KhoDTO;
import java.util.ArrayList;

public class KhoBUS {
    private ArrayList<KhoDTO> ds;

    public KhoBUS() {
    }
    
    public ArrayList<KhoDTO> list(ArrayList<String> data_filters){
        KhoDAO tkDAO = new KhoDAO();
        ds = new ArrayList<>();
        ds = tkDAO.list(data_filters);
        return ds;
    }
}
