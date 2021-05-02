package experis.ds;

import experis.ds.client.ClientUser;

public interface ICommandExecutor {
    public void execute(ClientUser clientUser, String msg);
}
