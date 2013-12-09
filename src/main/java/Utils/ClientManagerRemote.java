package Utils;

import model.Client;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by thiergeo on 09/12/13.
 */

@Remote
public interface ClientManagerRemote {

    public long create(Client c);
    public long update(Client c);
    public void delete(long id);

    public boolean login(String username, String password);

    public List<Client> listAll();
}
