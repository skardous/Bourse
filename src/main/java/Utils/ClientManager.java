package Utils;

import model.Client;

import java.util.List;

/**
 * Created by thiergeo on 09/12/13.
 */
public class ClientManager implements ClientManagerRemote {
    @Override
    public long create(Client c) {
        return 0;
    }

    @Override
    public long update(Client c) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public List<Client> listAll() {
        return null;
    }
}
